package model;

import java.util.ArrayList;

/**
 *
 * @author tacioss
 */
public class CDInternacional extends CD{
    private String titulo_original;
    private int regiao;

    public CDInternacional(String titulo_original, String titulo, int regiao, ArrayList musicas) {
        super(titulo, musicas);
        this.titulo_original = titulo_original;
        this.regiao = regiao;
    }
    
    public CDInternacional(int codigo, String titulo, String titulo_original, int regiao, ArrayList musicas) {
        super(codigo, titulo, musicas);
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

    @Override
    public String toString() {
        return "CDInternacional{" + "titulo_original=" + titulo_original + ", regiao=" + regiao + '}';
    }
    
    

    
    
    
}
