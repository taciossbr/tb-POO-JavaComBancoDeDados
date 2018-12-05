package tb.poo;

import dao.CDDAOSQLite;
import dao.MusicaDAOSQLite;
import dao.PessoaDAOSQLite;
import dao.SQLiteConnectionFactory;
import dao.interfaces.CDDAO;
import dao.interfaces.MusicaDAO;
import dao.interfaces.PessoaDAO;
import java.util.ArrayList;
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
        
        PessoaDAO pdao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        MusicaDAO mdao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        CDDAO cdao = new CDDAOSQLite(SQLiteConnectionFactory.getConnection());
        /*
        Pessoa pessoa = new Pessoa("Luiz");
        pdao.cadastrarPessoa(pessoa);
        
        pdao.deletarPessoa(5);
        
        // pdao.alterarPessoa(new Pessoa(6, "Adalberto"));
        
        System.out.println("get " + pdao.getPessoa(1));
        
        for( Pessoa p : pdao.todasPessoas()) {
            System.out.println(p);
        } 
        //*/
        
        /*
        
        ArrayList<Pessoa> comps = new ArrayList<>();
        comps.add(pdao.getPessoa(1));
        
        ArrayList<Pessoa> ints = new ArrayList<>();
        ints.add(pdao.getPessoa(1));
        ints.add(pdao.getPessoa(2));
        
        Musica musica = new Musica("Que bonita sua roupa", 3, comps, ints);
        pdao.destroy();
        
        // dao.cadastrarMusica(musica);
        
        // System.out.println("Musica 2:\t" + dao.getMusica(2) + "\n");
        
        //dao.deletarMusica(4);
        
        // dao.alterarMusica(new Musica(2, "terezinha de jesus", 2, comps, ints));;
        
        
        for( Musica m : dao.todasMusicas()) {
            System.out.println(m);
            /*
            System.out.println(m.getCompositores());
            System.out.println(m.getInterpretes()); 
        } 
        //*/
        
        //*/
        ArrayList<Musica> musicas = new ArrayList<>();
        
        // CD cd = new CD("Black", musicas);
        // cdao.cadastrarCD(cd);
        // System.out.println("new: " + cd);
        
        // CD cd = new CDInternacional("Smart", "burro", 8, musicas);
        // cdao.cadastrarCD(cd);
        // System.out.println("new: " + cd);
        
        // musicas.add(mdao.getMusica(2));
        // mdao.destroy();
        // Musica musica = mdao.getMusica(5);
        // mdao.destroy();
        // CD cd = cdao.getCD(9);
        // cd.addMusica(musica);
        // System.out.println(cd.getMusicas());
        
        // cdao.alterarCD(cd);
        
        cdao.deletarCD(7);
        
        /*/
        for( CD c: cdao.todosCDs()) {
            System.out.println(c);
            System.out.println(c.getMusicas());
        } 
        
        //*/
        
        
    }
    
}
