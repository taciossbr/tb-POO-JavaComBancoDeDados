package model;

/**
 *
 * @author tacioss
 */
public class CDInternacional extends CD{
    private String titulo_original;
    private int regiao;

    public CDInternacional(String titulo_original, String titulo, int regiao) {
        super(titulo);
        this.titulo_original = titulo_original;
        this.regiao = regiao;
    }
    
    public CDInternacional(int codigo, String titulo, String titulo_original, int regiao) {
        super(codigo, titulo);
        this.titulo_original = titulo_original;
        this.regiao = regiao;
    }

    public String getTituloOriginal() {
        return titulo_original;
    }

    public void setTituloOriginal(String titulo_original) {
        this.titulo_original = titulo_original;
    }

    public int getRegiaoCD() {
        return regiao;
    }

    public void setRegiaoCD(int regiao) {
        this.regiao = regiao;
    }
    
    

    
    
    
}
