package dao;

import dao.interfaces.PessoaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pessoa;

/**
 *
 * @author tacioss
 */
public class PessoaDAOSQLite implements PessoaDAO{
    private Connection conn;
    
    public PessoaDAOSQLite(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrarPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa(nome_pessoa) VALUES (?);";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pessoa.getNome());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void alterarPessoa(Pessoa pessoa) {
        String sql = "UPDATE Pessoa " +
                     "SET nome_pessoa = ? " +
                     "WHERE codigo_pessoa = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pessoa.getNome());
            pstmt.setInt(2, pessoa.getCodigo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deletarPessoa(int codigoPessoa) {
        String sql = "DELETE FROM Pessoa " +
                     "WHERE codigo_pessoa = ?;";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoPessoa);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Pessoa getPessoa(int codigoPessoa) {
        String sql = "SELECT * FROM Pessoa " +
                     "WHERE codigo_pessoa = ?";
 
        try {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigoPessoa);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            Pessoa pessoa = new Pessoa(rs.getInt("codigo_pessoa"), rs.getString("nome_pessoa"));
            return pessoa;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Pessoa> todasPessoas() {
        String sql = "SELECT * FROM Pessoa";
 
        try  {
            Connection conn = this.conn;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Pessoa> pessoas = new ArrayList<>();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa(rs.getInt("codigo_pessoa"), rs.getString("nome_pessoa"));
                pessoas.add(pessoa);
            }
            return pessoas;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}

