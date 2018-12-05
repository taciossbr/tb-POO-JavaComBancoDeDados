package model;

import java.util.ArrayList;

/**
 *
 * @author tacioss
 */
public class Musica {
    private int codigo;
    private String titulo;
    private int duracao; // em minutos
    private ArrayList<Pessoa> compositores;
    private ArrayList<Pessoa> interpretes;

    public Musica(String titulo, int duracao, ArrayList<Pessoa> compositores, ArrayList<Pessoa> interpretes) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.compositores = compositores;
        this.interpretes = interpretes;
    }

    public Musica(int codigo, String titulo, int duracao, ArrayList<Pessoa> compositores, ArrayList<Pessoa> interpretes) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracao = duracao;
        this.compositores = compositores;
        this.interpretes = interpretes;
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

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public ArrayList<Pessoa> getCompositores() {
        return compositores;
    }

    public ArrayList<Pessoa> getInterpretes() {
        return interpretes;
    }
    
    public void addCompositor(Pessoa compositor) {
        this.compositores.add(compositor);
    }
    
    public void addInterprete(Pessoa interprete) {
        this.interpretes.add(interprete);
    }

    @Override
    public String toString() {
        return "Musica{" + "codigo=" + codigo + ", titulo=" + titulo + ", duracao=" + duracao + ", compositores=" + compositores + ", interpretes=" + interpretes + '}';
    }
    
    
    
}
