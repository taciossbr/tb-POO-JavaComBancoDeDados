/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb.poo;

import dao.CDDAOSQLite;
import dao.MusicaDAOSQLite;
import dao.PessoaDAOSQLite;
import dao.SQLiteConnectionFactory;
import dao.interfaces.CDDAO;
import dao.interfaces.MusicaDAO;
import dao.interfaces.PessoaDAO;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;
import model.CD;
import model.CDInternacional;
import model.Musica;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
class MenuCD {
    
    
    static void init() {
        Printer.cprintln("--------------------------------------", "31");
        Printer.cprint("----------", "31");
        Printer.cprint("   Cadastrar CD   ", "32");
        Printer.cprintln("----------", "31");
        Printer.cprintln("--------------------------------------", "31");
        
        
        int op = 0;
        do {
            op = menu();
            switch (op) {
                case 1:
                    cadastrarCD();
                    break;
                case 2:
                    consultarCD();
                    break;
                case 3:
                    consultarTodosCDs();
                    break;
                case 4:
                    alterarCD();
                    break;
                case 5:
                    deletarCD();
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

    private static void cadastrarCD() {
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        
        out.print("\n\n\nTitulo: ");
        String titulo = sc.nextLine();
        out.print("Internacional ? [Y/n]: ");
        String in = sc.nextLine();
        int regiao = 0;
        String titulo_original = null;
        if (in.equals("Y")) {
            out.print("Regiao: ");
            regiao = sc.nextInt();
            out.print("Título Original: ");
            sc.nextLine();
            titulo_original = sc.nextLine();
        }
        
        
        ArrayList<Musica> musicas = new ArrayList<>();
        int cod = 0;
        do {
            MenuMusica.imprimirTabelaTodasMusicas();
            out.println("Digite o código da musica[0 para encerrar]: ");
            cod = sc.nextInt();
            Musica musica = dao.getMusica(cod);
            if (cod == 0) {
                break;
            }
            if (musica == null) {
                out.println("Música não cadastrada \n:(");
            } else if (musicas.contains(musica)) {
                out.println("Música ja adicionado \n:)");
            } else {
                musicas.add(musica);
            }
            
        } while(cod != 0);
        
        dao.destroy();
        
        CDDAO cddao = new CDDAOSQLite(SQLiteConnectionFactory.getConnection());
        CD cd;
        if (in.equals("Y")) {
            cd = new CDInternacional(titulo_original, titulo, regiao, musicas);
        } else {
            cd = new CD(titulo, musicas);
        }
        
        cddao.cadastrarCD(cd);
        
        
        out.println("CD adicionada com sucesso\n:)");
        
        
        out.println();
        out.println();
        cddao.destroy();
    }

    private static void consultarCD() {
        CDDAO dao = new CDDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        CD cd = dao.getCD(cod);
        if (cd == null) {
            out.println("CD não cadastrada\n:(");
        } else {
            out.println("Titulo: " + cd.getTitulo());
            if (cd instanceof CDInternacional) {
                out.println("Titulo Original: " + ((CDInternacional)cd).getTituloOriginal());
                out.println("Regiao: " + ((CDInternacional)cd).getRegiaoCD());
            }
            Printer.cprintln("Músicas", "32");
            MenuMusica.imprimirTabela(cd.getMusicas());
            //*/
        }
                
        
        out.println();
        out.println();
        dao.destroy();
    }

    private static void consultarTodosCDs() {
        imprimirTabelaTodosCDs();
    }

    public static void imprimirTabelaTodosCDs() {
        CDDAO dao = new CDDAOSQLite(SQLiteConnectionFactory.getConnection());
        ArrayList<CD> cds = dao.todosCDs();
        
        imprimirTabela(cds);
        
        out.println();
        out.println();
        dao.destroy();
    }

    public static void imprimirTabela(ArrayList<CD> cds) {
        Printer.linha(80, "31");
        out.println();
        for (CD cd: cds) {
            int cod = cd.getCodigo();
            String titulo = cd.getTitulo();
            String regiao = "NULL";
            String org = "NULL";
            if (cd instanceof CDInternacional) {
                regiao = Integer.toString(((CDInternacional)cd).getRegiaoCD());
                org = ((CDInternacional)cd).getTituloOriginal();
            }
            Printer.cprint("| ", "31");
            Printer.spaces(4 - Integer.toString(cod).length());
            Printer.cprint(cod, "32");
            Printer.cprint(" | ", "31");
            Printer.cprint(titulo, "32");
            Printer.spaces(29 - titulo.length());
            Printer.cprint(" | ", "31");
            Printer.cprint(org, "32");
            Printer.spaces(29 - org.length());
            Printer.cprint(" | ", "31");
            Printer.cprint(regiao, "32");
            Printer.spaces(5 - regiao.length());
            Printer.cprintln(" |", "31");
            Printer.linha(80, "31");
            Printer.println("");
        }
        out.println();
    }
    
    private static void alterarCD() {
        CDDAO dao = new CDDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        CD cd = dao.getCD(cod);
        if (cd == null) {
            out.println("CD não cadastrada\n\n:-(");
        } else {
            out.print("Titulo: ");
            String nome = sc.next();
            cd.setTitulo(nome);
            if (cd instanceof CDInternacional) {
                sc.nextLine();
                out.print("Titulo Original: ");
                String org = sc.nextLine();
                ((CDInternacional) cd).setTituloOriginal(org);
                out.print("Região: ");
                int regiao = sc.nextInt();
                ((CDInternacional) cd).setRegiaoCD(regiao);
                
            }
            
            dao.alterarCD(cd);
                    

            out.println("Música alterada com successo\n\n:)");
            out.println("COD: " + cd.getCodigo());
        }
        
        
        out.println();
        out.println();
        dao.destroy();
    }
    
    private static void deletarCD() {
        CDDAO dao = new CDDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        CD cd = dao.getCD(cod);
        if (cd == null) {
            out.println("CD não cadastrada\n:(");
        } else {
            out.println("Titulo: " + cd.getTitulo());
            out.print("\nDeseja realmente deletar esta cd? [Y/n]:");
            String op = sc.next();
            if (op.equals("Y")) {
                dao.deletarCD(cod);
                out.println("CD deletada com sucesso\n:)");
            }
        }
                
        
        out.println();
        out.println();
        dao.destroy();
    }

    
}