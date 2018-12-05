
package dao;

import dao.interfaces.CDDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CD;
import model.CDInternacional;

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
 
        try (Connection conn = this.conn) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cd.getTitulo());
            pstmt.executeUpdate();
            if (cd instanceof CDInternacional) {
                sql = "INSERT INTO CD_internacional(codigo_cd, titulo_original_cd, regiao_cd) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, cd.getCodigo());
                pstmt.setString(2, ((CDInternacional)cd).getTituloOriginal());
                pstmt.setInt(3, ((CDInternacional)cd).getRegiaoCD());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void alterarCD(CD cd) {
        String sql = "UPDATE CD" +
                     "SET titulo_cd = ?";
 
        try (Connection conn = this.conn) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cd.getTitulo());
            pstmt.executeUpdate();
            if (cd instanceof CDInternacional) {
                sql = "UPDATE CD_internacional" +
                      "SET titulo_original_cd = ?, regiao_cd = ?" +
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
    }

    @Override
    public void deletarCD(int codigoCD) {
        String sql = "DELETE FROM CD" +
                     "WHERE codigo_cd = ?";
 
        try (Connection conn = this.conn) {
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoCD);
            pstmt.executeUpdate();
            CD cd = this.getCD(codigoCD);
            if (cd instanceof CDInternacional) {
                sql = "DELETE FROM CD_internacional" +
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
                     "titulo_original_cd, regiao_cd from cd" + 
                     "left join CD_internacional on cd.codigo_cd = CD_internacional.codigo_cd" +
                     "where cd.codigo_cd = ?";
 
        try (Connection conn = this.conn;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigoCD);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String titulo = rs.getString("titulo_cd");

            CD cd = null;
            if (rs.getInt("i") != 0) {
                cd = new CDInternacional(codigoCD, titulo, 
                    rs.getString("titulo_original_cd"), rs.getInt("regiao_cd"));
            } else {
                cd = new CD(codigoCD, titulo);
            }
            return cd;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<CD> todosCDs() {
        String sql = "select cd.codigo_cd, titulo_cd, CD_internacional.codigo_cd as i, " +
                     "titulo_original_cd, regiao_cd from cd" + 
                     "left join CD_internacional on cd.codigo_cd = CD_internacional.codigo_cd";
 
        try (Connection conn = this.conn;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CD> cds = new ArrayList<>();
            while(rs.next()) {
                String titulo = rs.getString("titulo_cd");

                CD cd;
                if (rs.getInt("i") != 0) {
                    cd = new CDInternacional(rs.getInt("codigo_cd"), titulo, 
                        rs.getString("titulo_original_cd"), rs.getInt("regiao_cd"));
                } else {
                    cd = new CD(rs.getInt("codigo_cd"), titulo);
                }
                cds.add(cd);
            }
            return cds;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
}
