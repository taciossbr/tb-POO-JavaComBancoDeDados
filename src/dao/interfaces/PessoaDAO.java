package dao.interfaces;

import java.util.ArrayList;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
public interface PessoaDAO {
    public void cadastrarPessoa(Pessoa pessoa);
    public void alterarPessoa(Pessoa pessoa);
    public void deletarPessoa(int codigoPessoa);
    public Pessoa getPessoa(int codigoPessoa);
    public ArrayList<Pessoa> todasPessoas();
}
