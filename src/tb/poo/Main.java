package tb.poo;

import dao.MusicaDAOSQLite;
import dao.PessoaDAOSQLite;
import dao.SQLiteConnectionFactory;
import dao.interfaces.MusicaDAO;
import dao.interfaces.PessoaDAO;
import java.util.ArrayList;
import model.Musica;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
public class Main {

    public static void main(String[] args) {
        
        PessoaDAO pdao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
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
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        
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
        
        
        
        
    }
    
}
