package tb.poo;

import dao.MusicaDAOSQLite;
import dao.PessoaDAOSQLite;
import dao.SQLiteConnectionFactory;
import dao.interfaces.MusicaDAO;
import dao.interfaces.PessoaDAO;
import model.Musica;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
public class Main {

    public static void main(String[] args) {
        /*
        PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
        // Pessoa pessoa = new Pessoa("Adenilson");
        // dao.cadastrarPessoa(pessoa);
        
        // dao.deletarPessoa(5);
        
        // dao.alterarPessoa(new Pessoa(6, "Adalberto"));
        
        System.out.println("get " + dao.getPessoa(1));
        
        for( Pessoa p : dao.todasPessoas()) {
            System.out.println(p);
        } 
        */
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        
        Musica musica = new Musica("Que bonita sua roupa");
        
    }
    
}
