package application;


import entities.Livro;
import entities.Usuario;
import services.BibliotecaService;

import java.util.Scanner;

public class Progam {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BibliotecaService biblioteca = new BibliotecaService();

        int opcao;

        do {
            System.out.println("\n===== SISTEMA BIBLIOTECA =====");
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Cadastrar Livro");
            System.out.println("3 - Emprestar Livro");
            System.out.println("4 - Devolver Livro");
            System.out.println("5 - Listar Livros Disponiveis");
            System.out.println("6 - Listar Emprestimos do usuario");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("ID do usuario: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Nome: ");
                    String nome = sc.nextLine();

                    System.out.println("Email: ");
                    String email = sc.nextLine();

                    Usuario usuario = new Usuario(id, nome, email);

                    biblioteca.cadastrarUsuario(usuario);

                    System.out.println("Usuario cadastado com sucesso!");
                    break;

                case 2:
                    System.out.print("Titulo do livro: ");
                    String titulo = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    Livro livro = new Livro(titulo, autor, isbn);

                    biblioteca.cadastrarLivro(livro);

                    System.out.println("Livro cadastrado com sucesso!!");
                    break;

                case 3:
                    System.out.print("ID do usuario: ");
                    int usuarioId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("ISBN do livro:");
                    String isbnEmprestimo = sc.nextLine();

                    biblioteca.emprestarLivro(usuarioId, isbnEmprestimo);

                    System.out.println("Livro emprestado com sucesso!!");
                    break;

                case 4:
                    System.out.println("ID do usuario");
                    int usuarioDev = sc.nextInt();
                    sc.nextLine();

                    System.out.print("ISBN do livro");
                    String isbnDev = sc.nextLine();

                    biblioteca.devolverLivro(usuarioDev, isbnDev);

                    System.out.println("Livro devolvido com sucesso");
                    break;

                case 5:
                    biblioteca.listarLivrosDisponiveis();
                    break;

                case 6:
                    System.out.println("Digite o ID do usuario:");
                    int idUsuario = sc.nextInt();
                    biblioteca.listarEmprestimoUsuario(idUsuario);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção invalidaa");
            }

        }while (opcao != 0);

        sc.close();

    }
}
