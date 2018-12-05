package dao.interfaces;

import java.util.ArrayList;
import model.Musica;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
public interface MusicaDAO {
    public void cadastrarMusica(Musica musica);
    public void alterarMusica(Musica musica);
    public void deletarMusica(int codigoMusica);
    public Musica getMusica(int codigoMusica);
    public ArrayList<Musica> todasMusicas();
    public ArrayList<Pessoa> getCompositores(int codigoMusica);
    public ArrayList<Pessoa> getInterpretes(int codigoMusica);
    public void destroy();
}
