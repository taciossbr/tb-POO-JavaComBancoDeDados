
package tb.poo;

import dao.PessoaDAOSQLite;
import dao.SQLiteConnectionFactory;
import dao.interfaces.PessoaDAO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
class MenuPessoa {

    static void init() {
        Printer.cprintln("--------------------------------------", "31");
        Printer.cprint("--------", "31");
        Printer.cprint("   Cadastrar Pessoa   ", "32");
        Printer.cprintln("--------", "31");
        Printer.cprintln("--------------------------------------", "31");
        
        
        int op = 0;
        do {
            op = menu();
            switch (op) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    consultarPessoa();
                    break;
                case 3:
                    consultarTodasPessoas();
                    break;
                case 4:
                    alterarPessoa();
                    break;
                case 5:
                    deletarPessoa();
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

    private static void cadastrarPessoa() {
        PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nNome: ");
        String nome = sc.nextLine();
        Pessoa pessoa = new Pessoa(nome);
        dao.cadastrarPessoa(pessoa);
        
        out.println("Pessoa cadastrada com sucesso!");
        out.println("COD: " + pessoa.getCodigo());
        out.println();
        out.println();
        
    }

    private static void consultarPessoa() {
        PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        Pessoa pessoa = dao.getPessoa(cod);
        if (pessoa == null) {
            out.println("Pessoa não cadastrada\n:(");
        } else {
            out.println("Nome: " + pessoa.getNome());
        }
                
        
        out.println();
        out.println();
    }
    
    private static void alterarPessoa() {
        PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        Pessoa pessoa = dao.getPessoa(cod);
        out.println(pessoa);
        if (pessoa == null) {
            out.println("Pessoa não cadastrada\n\n:-(");
        } else {
            out.print("Nome: ");
            // sc.next();
            String nome = sc.next();
            pessoa.setNome(nome);
            out.println(pessoa);
            dao.alterarPessoa(pessoa);
        }
        
        
        out.println("Pessoa alterada com sucesso!");
        out.println("COD: " + pessoa.getCodigo());
        out.println();
        out.println();
    }

    private static void consultarTodasPessoas() {
        PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        ArrayList<Pessoa> pessoas = dao.todasPessoas();
        Printer.linha(80, "31");
        out.println();
        for (Pessoa p: pessoas) {
            int cod = p.getCodigo();
            String nome = p.getNome();
            Printer.cprint("| ", "31");
            Printer.spaces(5 - Integer.toString(cod).length());
            Printer.cprint(p.getCodigo(), "32");
            Printer.cprint(" | ", "31");
            Printer.cprint(nome, "32");
            Printer.spaces(68 - nome.length());
            Printer.cprintln(" |", "31");
            
            Printer.linha(80, "31");
            Printer.println("");
        }
        
        out.println();
        out.println();
    }
    
    private static void deletarPessoa() {
        PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        Scanner sc = new Scanner(System.in);
        out.print("\n\n\nCOD: ");
        int cod = sc.nextInt();
        Pessoa pessoa = dao.getPessoa(cod);
        if (pessoa == null) {
            out.println("Pessoa não cadastrada\n:(");
        } else {
            out.println("Nome: " + pessoa.getNome());
            out.print("\nDeseja realmente deletar esta pessoa? [Y/n]:");
            String op = sc.next();
            if (op.equals("Y")) {
                dao.deletarPessoa(cod);
                out.println("Pessoa deletada com sucesso\n:)");
            }
        }
                
        
        out.println();
        out.println();
    }
    
}
