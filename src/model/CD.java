package model;

/**
 *
 * @author tacioss
 */
public class CD {
    private int codigo;
    private String titulo;

    public CD(String titulo) {
        this.titulo = titulo;
    }
    
    public CD(int codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    } 
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
