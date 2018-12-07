package tb.poo;

import static java.lang.System.out;

import dao.CDDAOSQLite;
import dao.MusicaDAOSQLite;
import dao.PessoaDAOSQLite;
import dao.SQLiteConnectionFactory;
import dao.interfaces.CDDAO;
import dao.interfaces.MusicaDAO;
import dao.interfaces.PessoaDAO;
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
public class Main {

    public static void main(String[] args) {
        Printer.cprintln("--------------------------------------", "31");
        Printer.cprint("--------------", "31");
        Printer.cprint("   MENU   ", "32");
        Printer.cprintln("--------------", "31");
        Printer.cprintln("--------------------------------------", "31");
        
        
        int op = 0;
        do {
            op = menu();
            switch (op) {
                case 1:
                    menuPessoa();
                    break;
                case 2:
                    menuMusica();
                    break;
                case 0: // Sair
            }
        } while (op != 0);
        
    }
    
    

    private static int menu() {
        
        out.println("1- Pessoas");
        out.println("2- Musicas");
        out.println("\n0- SAIR\n\n");
        
        out.print("Op: ");
        int op = 0;
        Scanner sc = new Scanner(System.in);
        op = sc.nextInt();
        return op;
    }

    private static void menuPessoa() {
        MenuPessoa.init();
    }

    private static void menuMusica() {
        MenuMusica.init();
    }

    
}
