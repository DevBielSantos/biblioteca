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
}
