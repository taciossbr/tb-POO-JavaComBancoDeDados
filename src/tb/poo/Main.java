/**
 * TB - POO 2o Semestre 2018
 * @author Tacio dos Santos Souza
 * @author Adenilson Elias da Silva
 */

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

public class Main {

    public static void main(String[] args) {
        Printer.cprintln("--------------------------------------", "31");
        Printer.cprint("--------------", "31");
        Printer.cprint("   MENU   ", "32");
        Printer.cprintln("--------------", "31");
        Printer.cprintln("--------------------------------------\n", "31");
        
        
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
                case 3:
                    menuCD();
                    break;
                case 0: // Sair
            }
        } while (op != 0);
        
        
        msgFinal();
        
    }
    
    

    private static int menu() {
        
        out.println("1- Pessoas");
        out.println("2- Musicas");
        out.println("3- CDs");
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

    private static void menuCD() {
        MenuCD.init();
    }
    
    private static void msgFinal() {
        String estrela =    "                               $\n" +
                            "                              :$$\n" +
                            "                         seeee$$$Neeee\n" +
                            "                           R$$$F$$$$F\n" +
                            "                             $$$$$$\n" +
                            "                            @$$P*$$B\n" +
                            "                           z$#\"  $#$b\n";

        String arv =        "                           \" d   'N \"\n" +
                            "                            @\"     ?r\n" +
                            "                          xF .       \"N\n" +
                            "                       .$> P54.R       `$\n" +
                            "                     $*   '*\"$$$  uoP***~\n" +
                            "                      #Noo \"?$N\"   #oL\n" +
                            "                         f       o$#$$\"e.\n" +
                            "                        $  @b    hoR$$r ^\"$$b\n" +
                            "                     .M   ?B$E   *.B$$       .R\n" +
                            "                   .*     *\\ *.4*R         ..*\n" +
                            "                oo#     ooL    d#R.     P##~\n" +
                            "                $c    .\"\"P#$  @   P     k\n" +
                            "                  R$r ''?L$$  P  \"r     'N\n" +
                            "                    ^$ \"$$$` $.....JL     \"N.\n" +
                            "                  .$\\           * P5\"LR      $..\n" +
                            "               ..* 4*R     xr    'PFN$$   .k    \"*****.\n" +
                            "            od#\"   d#*.  \"*$$P~   \"?$*\" '#$$$\"       u\"\n" +
                            "         e\"\"      f   M   @F\"$  ec       x$\"$.     :\"\n" +
                            "         M        >  \"d       $$$$?$           .$$F`\n" +
                            "          \"P..  .$.....$L $$.4$$. \"   @#3$$   $E.\n" +
                            "             '**..  *   R..$$ `R$*k.  fdM$$>     *..\n" +
                            "               J\"       *k$$$~  \"*$**o$o$$P        '*oo.\n" +
                            "              P           #        \"$$$#*o          >  '####*oooo\n" +
                            "           .e\"            :e$$e.  F3  ^\"$P  :$$s :e@$ee        s\"\n" +
                            "         $P` M7>    $P$$k \"$\"?$3 @\"#N      CxN$$> .$$$       .P\n" +
                            "      M$~   J\\##   44N>$$  .d$.$d   @&      `$$$  F  .8..$$$*\n" +
                            "  .***     :   JM   *d$$*.$$.P  M  .P5     M          **.\n" +
                            "  \"oo      J  .dP    ud$$od#   $oooooo$  oo$oo           ###ou\n" +
                            "     \"####$beeeee$.'$eeP#~        \"\"      $$$.    e$$$o       #heeee\n" +
                            "        :\"    \" z$r ^            o$N     '\"  \"   4$z>$$             \"\"\"#$$$\n" +
                            "       .~      F$4$B       r    F @#$.       ..   $8$$P M7                $\n" +
                            "     .*  $     8 $$B     .J$..  hP$$$F     .'PB$       J~##             .d~\n" +
                            "   .P  *$$$*    \"*\"       $$$    #**~      hdM$$>     <   JM.......*****\n" +
                            " .P     $#*k       .o#>  P\" \"k   ..         '$$P      d  .JP'h\n" +
                            "\"\"\"hr ^        xe\"\"  >          \"\"c           ee    @beeeee$.)\n" +
                            "      \"\"\"t$$$$F\"      M        $`   R          > \"$r     \"     \"c\n";
        
        String tronco =     "                              oooooooooo\n" +
                            "                              z        z\n" +
                            "                              z.,ze.$$$z\n";
        
        Printer.cprint(estrela, "33");
        Printer.cprint(arv, "32");
        Printer.cprint(tronco, "31");
        // Printer.cprint(tronco, "31;41");
        
        out.println("\n\n\n");
        
        String msg =    "           $$     $$                                                     \n" +
                        "           $$     $$\n" +
                        "           $$b   d$$\n" +
                        "           $$$b d$$$\n" +
                        "          ,$$$$$$$$$\n" +
                        "     __   d$$ $$$ $$\n" +
                        "    d$$b  $$' `Y' $$     d$$$b $$$$$$'`$$$$$$'  $$  $$\n" +
                        "   d$'dP d$P      Y$b   t$__$$ $$  $$  $$  $$   $$  $$\n" +
                        "   $$   d$P       `Y$b  d$\"\"'  $$  $$  $$  $$   $$  $$\n" +
                        "   Y$$$$$P         `$$bd$$b..,d$$  4$.,$$  $$.,d$$bd$$ d$)\n" +
                        "    `4$P'           `Y$P'\"Y$$$$P'  `$$$P'  `$$$P'`\"\"$$$$P\n" +
                        "                                                   d$$P\n" +
                        "                                                  d$$$\n" +
                        "                                                 $$ $$\n" +
                        "                                                 $$ $$\n" +
                        "    d$$b                                         `$W$'\n" +
                        "  d$P\"\"Y$b    d$$b                                 ~\n" +
                        " $$'    $P  ,d$\"\"$$.\n" +
                        "d$' $b dY'  d$'  $$b                   $$                        ,d$$$b\n" +
                        "$$   Y$P'   $$  ,d$P                   $$                       ,$$'~`$$\n" +
                        "$$          $$ d$Y'        $$         H$$$$H                    $$'\n" +
                        "$$          $$$$b.                .    $$                       $$b\n" +
                        "$$         d$$' $$  $$$$$  $$    d$b   $$  d$b d$b,$b   ,d$$b    $$b\n" +
                        "Y$b      ,$P$$  $$  $$ $$  $$   d$^Yb ,$$ ,$$$$$\"$$$$$  d$'`$b    `$$b\n" +
                        " Y$b   ,d$P'$$  Y$bd$P $$bd$$b_$$'  $)$$$.$Y $$' $$ $$. $$.,$$b     `$$.\n" +
                        "  `$$$$$P'  $$   \"$$'  \"$$P'`Y$P  d$P `$$$$' $$  $$ `$$$$\"$$$'\"$$$)  $$)\n" +
                        "                                                                    d$P'\n" +
                        "                                                                 _.d$P'\n" +
                        "                                  d$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$P'\n" +
                        "                                 $$P";
        
        Printer.cprintln(msg, "37;41");
        
    }

    
}
