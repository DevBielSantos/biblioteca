package entities;

import enums.StatusLivro;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private StatusLivro status;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.status = StatusLivro.DISPONIVEL;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void emprestar() {
        this.status = StatusLivro.EMPRESTADO;
    }

    public void devolver() {
        this.status = StatusLivro.DISPONIVEL;
    }
}
