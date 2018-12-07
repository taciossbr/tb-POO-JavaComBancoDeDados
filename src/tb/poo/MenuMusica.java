/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.poo;

import dao.MusicaDAOSQLite;
import dao.PessoaDAOSQLite;
import dao.SQLiteConnectionFactory;
import dao.interfaces.MusicaDAO;
import dao.interfaces.PessoaDAO;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;
import model.Musica;
import model.Pessoa;
import static tb.poo.MenuPessoa.imprimirTabela;

/**
 *
 * @author tacioss
 */
class MenuMusica {

    static void init() {
        Printer.cprintln("--------------------------------------", "31");
        Printer.cprint("--------", "31");
        Printer.cprint("   Cadastrar Musica   ", "32");
        Printer.cprintln("--------", "31");
        Printer.cprintln("--------------------------------------", "31");
        
        
        int op = 0;
        do {
            op = menu();
            switch (op) {
                case 1:
                    cadastrarMusica();
                    break;
                case 2:
                    consultarMusica();
                    break;
                case 3:
                    consultarTodasMusicas();
                    break;
                case 4:
                    alterarMusica();
                    break;
                case 5:
                    deletarMusica();
                    break; 
                case 0: // Sair
                    break;
            }
        } while (op != 0);
    }

    private static int menu() {
        out.println("1- Cadastrar");
        out.println("2- Consultar");
        out.println("3- Consultar todos");
        out.println("4- Alterar");
        out.println("5- Deletar");
        out.println("\n0- Voltar para o menu principal");
        
        int op = 0;
        Scanner sc = new Scanner(System.in);
        op = sc.nextInt();
        return op;
    }

    private static void cadastrarMusica() {
        PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nTitulo: ");
        String nome = sc.nextLine();
        out.print("Duração: ");
        int duracao = sc.nextInt();
        
        ArrayList<Pessoa> compositores = new ArrayList<>();
        int cod = 0;
        do {
            MenuPessoa.imprimirTabelaTodasPessoas();
            out.println("Digite o código do compositor[0 para encerrar]: ");
            cod = sc.nextInt();
            Pessoa pessoa = dao.getPessoa(cod);
            if (cod == 0) {
                break;
            }
            if (pessoa == null) {
                out.println("Pessoa não cadastrada \n:(");
            } else if (compositores.contains(pessoa)) {
                out.println("Compositor ja adicionado \n:)");
            } else {
                compositores.add(pessoa);
            }
            
        } while(cod != 0);
        
        ArrayList<Pessoa> interpretes = new ArrayList<>();
        cod = 0;
        do {
            MenuPessoa.imprimirTabelaTodasPessoas();
            out.println("Digite o código do interprete[0 para encerrar]: ");
            cod = sc.nextInt();
            Pessoa pessoa = dao.getPessoa(cod);
            if (cod == 0) {
                break;
            }
            if (pessoa == null) {
                out.println("Pessoa não cadastrada \n:(");
            } else if (interpretes.contains(pessoa)) {
                out.println("Interprete ja adicionado \n:)");
            } else {
                interpretes.add(pessoa);
            }
            
        } while(cod != 0);
        
        dao.destroy();
        
        MusicaDAO mdao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Musica musica = new Musica(nome, duracao, compositores, interpretes);
        mdao.cadastrarMusica(musica);
        
        out.println("Música adicionada com sucesso\n:)");
        
        
        out.println();
        out.println();
        mdao.destroy();
    }

    private static void consultarMusica() {
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        Musica musica = dao.getMusica(cod);
        if (musica == null) {
            out.println("Música não cadastrada\n:(");
        } else {
            out.println("Titulo: " + musica.getTitulo());
            out.println("Duração: " + musica.getDuracao());
            /*/
            out.println(musica.getCompositores());
            out.println(musica.getInterpretes());
            //*/
            Printer.cprintln("Compositores", "32");
            MenuPessoa.imprimirTabela(musica.getCompositores());
            Printer.cprintln("Interpretes", "32");
            MenuPessoa.imprimirTabela(musica.getInterpretes());
            //*/
        }
                
        
        out.println();
        out.println();
        dao.destroy();
    }

    private static void consultarTodasMusicas() {
        imprimirTabelaTodasMusicas();
    }

    private static void imprimirTabelaTodasMusicas() {
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        ArrayList<Musica> musicas = dao.todasMusicas();
        
        imprimirTabela(musicas);
        
        out.println();
        out.println();
        dao.destroy();
    }

    private static void imprimirTabela(ArrayList<Musica> musicas) {
        Printer.linha(80, "31");
        out.println();
        for (Musica m: musicas) {
            int cod = m.getCodigo();
            String titulo = m.getTitulo();
            int duracao = m.getDuracao();
            Printer.cprint("| ", "31");
            Printer.spaces(5 - Integer.toString(cod).length());
            Printer.cprint(cod, "32");
            Printer.cprint(" | ", "31");
            Printer.cprint(titulo, "32");
            Printer.spaces(60 - titulo.length());
            Printer.cprint(" | ", "31");
            Printer.cprint(duracao, "32");
            Printer.spaces(5 - Integer.toString(duracao).length());
            Printer.cprintln(" |", "31");
            Printer.linha(80, "31");
            Printer.println("");
        }
        out.println();
    }

    private static void alterarMusica() {
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        Musica musica = dao.getMusica(cod);
        if (musica == null) {
            out.println("Música não cadastrada\n\n:-(");
        } else {
            out.print("Titulo: ");
            String nome = sc.next();
            musica.setTitulo(nome);
            out.print("Duração: ");
            int dur = sc.nextInt();
            musica.setDuracao(dur);
            dao.alterarMusica(musica);
            out.println("Música alterada com successo\n\n:)");
            out.println("COD: " + musica.getCodigo());
        }
        
        
        out.println();
        out.println();
        dao.destroy();
    }
    
    private static void deletarMusica() {
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        Musica musica = dao.getMusica(cod);
        if (musica == null) {
            out.println("Música não cadastrada\n:(");
        } else {
            out.println("Titulo: " + musica.getTitulo());
            out.print("\nDeseja realmente deletar esta musica? [Y/n]:");
            String op = sc.next();
            if (op.equals("Y")) {
                dao.deletarMusica(cod);
                out.println("Música deletada com sucesso\n:)");
            }
        }
                
        
        out.println();
        out.println();
        dao.destroy();
    }
    
    
}
