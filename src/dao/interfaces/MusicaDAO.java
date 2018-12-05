package dao.interfaces;

import java.util.ArrayList;
import model.Musica;

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
}
