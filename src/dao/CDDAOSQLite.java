
package dao;

import dao.interfaces.CDDAO;
import dao.interfaces.MusicaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CD;
import model.CDInternacional;
import model.Musica;

/**
 *
 * @author tacioss
 */
public class CDDAOSQLite implements CDDAO{
    private Connection conn;

    public CDDAOSQLite(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrarCD(CD cd) {
        String sql = "INSERT INTO CD(titulo_cd) VALUES (?)";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, cd.getTitulo());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            cd.setCodigo(rs.getInt(1));
            if (cd instanceof CDInternacional) {
                sql = "INSERT INTO CD_internacional(codigo_cd, titulo_original_cd, regiao) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, cd.getCodigo());
                pstmt.setString(2, ((CDInternacional)cd).getTituloOriginal());
                pstmt.setInt(3, ((CDInternacional)cd).getRegiaoCD());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        MusicaDAO dao = new MusicaDAOSQLite(this.conn);
        for (Musica m: cd.getMusicas()) {
            System.out.println(m);
            if (m.getCodigo() == 0) {
                dao.cadastrarMusica(m);
            }
            this.vinculaMusica(cd, m);
        }
        dao.destroy();
    }

    @Override
    public void alterarCD(CD cd) {
        String sql = "UPDATE CD " +
                     "SET titulo_cd = ? " +
                     "WHERE codigo_cd = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cd.getTitulo());
            pstmt.setInt(2, cd.getCodigo());
            pstmt.executeUpdate();
            if (cd instanceof CDInternacional) {
                sql = "UPDATE CD_internacional " +
                      "SET titulo_original_cd = ?, regiao = ? " +
                      "WHERE codigo_cd = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, ((CDInternacional)cd).getTituloOriginal());
                pstmt.setInt(2, ((CDInternacional)cd).getRegiaoCD());
                pstmt.setInt(3, cd.getCodigo());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        MusicaDAO dao = new MusicaDAOSQLite(this.conn);
        ArrayList<Musica> musicas = this.getMusicasCD(cd.getCodigo());
        for (Musica m: cd.getMusicas()) {
            if (m.getCodigo() == 0) {
                dao.cadastrarMusica(m);
            } 
            if (!musicas.contains(m)) {
                this.vinculaMusica(cd, m);
            }
        }
        dao.destroy();
    }

    @Override
    public void deletarCD(int codigoCD) {
        String sql = "DELETE FROM Musicas_CD " +
              "WHERE codigo_cd = ?";
        try {
            CD cd = this.getCD(codigoCD);
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoCD);
            pstmt.executeUpdate();
            sql = "DELETE FROM CD " +
                     "WHERE codigo_cd = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoCD);
            pstmt.executeUpdate();
            
            
            if (cd instanceof CDInternacional) {
                sql = "DELETE FROM CD_internacional " +
                "WHERE codigo_cd = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, codigoCD);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public CD getCD(int codigoCD) {
        String sql = "select cd.codigo_cd, titulo_cd, CD_internacional.codigo_cd as i, " +
                     "titulo_original_cd, regiao from cd " + 
                     "left join CD_internacional on cd.codigo_cd = CD_internacional.codigo_cd " +
                     "where cd.codigo_cd = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoCD);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String titulo = rs.getString("titulo_cd");

            CD cd = null;
            if (rs.getInt("i") != 0) {
                cd = new CDInternacional(codigoCD, titulo, 
                    rs.getString("titulo_original_cd"), rs.getInt("regiao"),
                    this.getMusicasCD(codigoCD));
            } else {
                cd = new CD(codigoCD, titulo, this.getMusicasCD(codigoCD));
            }
            return cd;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CD> todosCDs() {
        String sql = "select cd.codigo_cd, titulo_cd, CD_internacional.codigo_cd as i, " +
                     "titulo_original_cd, regiao from cd " + 
                     "left join CD_internacional on cd.codigo_cd = CD_internacional.codigo_cd ";
        
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CD> cds = new ArrayList<>();
            while(rs.next()) {
                String titulo = rs.getString("titulo_cd");
                int cod = rs.getInt("codigo_cd");

                CD cd;
                if (rs.getInt("i") != 0) {
                    cd = new CDInternacional(rs.getInt("codigo_cd"), titulo, 
                        rs.getString("titulo_original_cd"),
                            rs.getInt("regiao"), this.getMusicasCD(cod));
                } else {
                    cd = new CD(cod, titulo, this.getMusicasCD(cod));
                }
                cds.add(cd);
            }
            return cds;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void vinculaMusica(CD cd, Musica m) {
        String sql = "INSERT INTO Musicas_CD VALUES (?, ?)";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cd.getCodigo());
            pstmt.setInt(2, m.getCodigo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private ArrayList getMusicasCD(int codigoCD) {
        String sql = "SELECT * FROM Musica " +
                     "JOIN Musicas_CD on Musica.codigo_musica = Musicas_CD.codigo_musica " +
                     "where codigo_cd = ?";
        
        MusicaDAO dao = new MusicaDAOSQLite(SQLiteConnectionFactory.getConnection());
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoCD);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Musica> musicas = new ArrayList<>();
            while (rs.next()) {
                int codigo_musica = rs.getInt("codigo_musica");
                Musica musica = new Musica(codigo_musica, 
                rs.getString("titulo_musica"), rs.getInt("duracao"),
                dao.getCompositores(codigo_musica), dao.getInterpretes(codigo_musica));
                musicas.add(musica);
                
            }
            dao.destroy();
            return musicas;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            dao.destroy();
            return null;
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
