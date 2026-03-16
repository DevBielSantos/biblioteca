package services;

import entities.Livro;
import entities.Usuario;

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
}
