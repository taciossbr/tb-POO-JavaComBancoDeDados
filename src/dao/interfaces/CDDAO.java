
package dao.interfaces;

import java.util.ArrayList;
import model.CD;

/**
 *
 * @author tacioss
 */
public interface CDDAO {
    public void cadastrarCD(CD cd);
    public void alterarCD(CD cd);
    public void deletarCD(int codigoCD);
    public CD getCD(int codigoCD);
    public ArrayList<CD> todosCDs();
    public void destroy();
}
