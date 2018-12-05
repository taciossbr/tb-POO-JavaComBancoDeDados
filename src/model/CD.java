package model;

import java.util.ArrayList;

/**
 *
 * @author tacioss
 */
public class CD {
    private int codigo;
    private String titulo;
    private ArrayList<Musica> musicas;

    public CD(String titulo, ArrayList musicas) {
        this.titulo = titulo;
        this.musicas = musicas;
    }
    
    public CD(int codigo, String titulo, ArrayList musicas) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.musicas = musicas;
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

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }
    
    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    @Override
    public String toString() {
        return "CD{" + "codigo=" + codigo + ", titulo=" + titulo + ", musicas=" + musicas + '}';
    }
    
    
    
}
