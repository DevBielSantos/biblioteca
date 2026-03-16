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

   public void cadastarLivro(Livro livro) {
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
           throw new RuntimeException("Livro indisponivel");
       }

       LocalDate hoje = LocalDate.now();
       LocalDate devolucaoPrevista = hoje.plusDays(7);

       Emprestimo emprestimo = new Emprestimo(usuario, livro, hoje, devolucaoPrevista);
   }
}
