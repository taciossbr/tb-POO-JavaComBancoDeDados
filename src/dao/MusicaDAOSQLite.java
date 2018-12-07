/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.interfaces.MusicaDAO;
import dao.interfaces.PessoaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Musica;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
public class MusicaDAOSQLite implements MusicaDAO{
    private Connection conn;
    public MusicaDAOSQLite(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrarMusica(Musica musica){
        String sql = "INSERT INTO Musica(titulo_musica, duracao) VALUES (?, ?);";
        
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, musica.getTitulo());
            pstmt.setInt(2, musica.getDuracao());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            musica.setCodigo(rs.getInt(1));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        PessoaDAO dao = new PessoaDAOSQLite(this.conn);
        for (Pessoa comp: musica.getCompositores()) {
            if (comp.getCodigo() == 0) {
                dao.cadastrarPessoa(comp);
            }
            this.vinculaCompositor(musica, comp);
        }
        for (Pessoa interprete: musica.getInterpretes()) {
            if (interprete.getCodigo() == 0){
                dao.cadastrarPessoa(interprete);
            }
            this.vinculaInterprete(musica, interprete);
        }
        dao.destroy();
    }

    @Override
    public void alterarMusica(Musica musica) {
        String sql = "UPDATE Musica " +
                     "SET titulo_musica = ?, duracao = ? " +
                     "WHERE codigo_musica = ?";
        
        PessoaDAO dao = new PessoaDAOSQLite(this.conn);
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, musica.getTitulo());
            pstmt.setInt(2, musica.getDuracao());
            pstmt.setInt(3, musica.getCodigo());
            pstmt.executeUpdate();
            ArrayList<Pessoa> old_comps = this.getCompositores(musica.getCodigo());
            ArrayList<Pessoa> old_ints = this.getInterpretes(musica.getCodigo());
            for (Pessoa comp: musica.getCompositores()) {
                if (comp.getCodigo() == 0) {
                    dao.cadastrarPessoa(comp);
                }
                if (!old_comps.contains(comp)) {
                    this.vinculaCompositor(musica, comp);
                }
            }
            for (Pessoa interprete: musica.getInterpretes()) {
                if (interprete.getCodigo() == 0){
                    dao.cadastrarPessoa(interprete);
                }
                if (!old_ints.contains(interprete)) {
                    this.vinculaInterprete(musica, interprete);
                }
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        dao.destroy();
    }

    @Override
    public void deletarMusica(int codigoMusica) {
        String sql = "DELETE FROM Musica " +
                     "WHERE codigo_musica = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoMusica);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        sql = "DELETE FROM Pessoa_Compoe_Musica " +
                     "WHERE codigo_musica = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoMusica);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        sql = "DELETE FROM Pessoa_Interpreta_Musica " +
                     "WHERE codigo_musica = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoMusica);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Musica getMusica(int codigoMusica) {
        String sql = "SELECT * FROM Musica " +
                     "WHERE codigo_musica = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoMusica);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Musica musica = new Musica(rs.getInt("codigo_musica"), 
                rs.getString("titulo_musica"), rs.getInt("duracao"),
                this.getCompositores(codigoMusica), this.getInterpretes(codigoMusica));
            return musica;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Musica> todasMusicas() {
        String sql = "SELECT * FROM Musica";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Musica> musicas = new ArrayList<>();
            while (rs.next()) {
                int codigo_musica = rs.getInt("codigo_musica");
                Musica musica = new Musica(codigo_musica, 
                rs.getString("titulo_musica"), rs.getInt("duracao"),
                this.getCompositores(codigo_musica), this.getInterpretes(codigo_musica));
                musicas.add(musica);
            }
            return musicas;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Pessoa> getCompositores(int codigoMusica) {
        String sql = "SELECT * FROM Pessoa_Compoe_Musica " +
                     "WHERE codigo_musica = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoMusica);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Pessoa> compositores = new ArrayList<>();
            
            PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
            while (rs.next()) {
                Pessoa pessoa = dao.getPessoa(rs.getInt("codigo_pessoa"));
                compositores.add(pessoa);
            }
            dao.destroy();
            return compositores;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    @Override
    public ArrayList<Pessoa> getInterpretes(int codigoMusica) {
        String sql = "SELECT * FROM Pessoa_Interpreta_Musica " +
                     "WHERE codigo_musica = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoMusica);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Pessoa> interpretes = new ArrayList<>();
            
            PessoaDAO dao = new PessoaDAOSQLite(SQLiteConnectionFactory.getConnection());
            while (rs.next()) {
                Pessoa pessoa = dao.getPessoa(rs.getInt("codigo_pessoa"));
                if (pessoa != null) {
                    interpretes.add(pessoa);
                }
            }
            dao.destroy();
            return interpretes;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    private void vinculaCompositor(Musica musica, Pessoa comp) {
        String sql = "INSERT INTO Pessoa_Compoe_Musica VALUES (?, ?);";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, comp.getCodigo());
            pstmt.setInt(2, musica.getCodigo());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void vinculaInterprete(Musica musica, Pessoa interprete) {
        String sql = "INSERT INTO Pessoa_Interpreta_Musica VALUES (?, ?);";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, interprete.getCodigo());
            pstmt.setInt(2, musica.getCodigo());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void destroy() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
