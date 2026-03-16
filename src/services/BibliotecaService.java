package services;

import entities.Emprestimo;
import entities.Livro;
import entities.Usuario;
import enums.StatusLivro;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaService {
   private Map<Integer, Usuario> usuarios = new HashMap<>();
   private Map<String, Livro> livros = new HashMap<>();

   public void cadastrarUsuario(Usuario usuario) {
       usuarios.put(usuario.getId(), usuario);
   }

   public void cadastrarLivro(Livro livro) {
       livros.put(livro.getIsbn(), livro);
   }
   public void emprestarLivro(int usuarioId, String isbn) {
       Usuario usuario = usuarios.get(usuarioId);
       if (usuario == null) {
           throw new RuntimeException("Usuario não encontrado");
       }
       if (usuario.getEmprestimos().size() >= 3) {
           throw new IllegalStateException("Usuario atingiu o limite de emprestimos");
       }
       Livro livro = livros.get(isbn);
       if (livro == null) {
           throw new RuntimeException("Livro não encontrado");
       }
       if (livro.getStatus() != StatusLivro.DISPONIVEL) {
           throw new IllegalStateException("Livro indisponivel");
       }

       LocalDate hoje = LocalDate.now();
       LocalDate devolucaoPrevista = hoje.plusDays(7);

       Emprestimo emprestimo = new Emprestimo(usuario, livro, hoje, devolucaoPrevista);
       livro.emprestar();
       usuario.addEmprestimo(emprestimo);
   }

   public void devolverLivro(int usuarioId, String isbn) {
       Usuario usuario = usuarios.get(usuarioId);
       if (usuario == null) {
           throw new RuntimeException("Usuario não encontrado");
       }
       for (Emprestimo e : usuario.getEmprestimos()) {
           if (e.getLivro().getIsbn().equals(isbn) && e.getDataDevolucaoReal() == null) {
               e.registrarDevolucao(LocalDate.now());
               e.getLivro().devolver();
               return;

           }
       }
       throw new RuntimeException("Emprestimo nao encontrado");
   }

   public void listarLivrosDisponiveis() {
       for (Livro livro : livros.values()) {
           if (livro.getStatus() == StatusLivro.DISPONIVEL) {
               System.out.println("Titulo: "
               + livro.getTitulo()
               + " | Autor: "
               + livro.getAutor()
               +" | ISBN: "
               +livro.getIsbn());
           }
       }
   }

   public void listarEmprestimoUsuario(int usuarioId) {
       Usuario usuario = usuarios.get(usuarioId);
       if (usuario == null) {
           throw new RuntimeException("Usuario nao encontrado");
       }
       for (Emprestimo e : usuario.getEmprestimos()) {
           System.out.println("Livro: "
           + e.getLivro().getTitulo()
           + " | Emprestado em: "
           + e.getDataEmprestimo()
           +" | Devolver até: "
           + e.getDataDevolucaoPrevista());

           if (LocalDate.now().isAfter(e.getDataDevolucaoPrevista()) && e.getDataDevolucaoReal() == null) {
               System.out.println(" LIVRO ATRASADO!!!");
           }
       }
   }

}
