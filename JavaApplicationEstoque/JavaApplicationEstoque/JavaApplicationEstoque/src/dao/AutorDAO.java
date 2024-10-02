
package dao;

import beans.Autor;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Conexao conexao;
    private Connection conn;
    
    public AutorDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Autor autor){
        String sql = "INSERT INTO autor (gen_no, autor_name, autor_no) VALUES (?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, autor.getGen_no());
            stmt.setString(2, autor.getAutor_name());
            stmt.setInt(3,autor.getAutor_no());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir produto: "+ e.getMessage());
        }
    }
    
    public void alterar(Autor autor){
        String sql = "UPDATE autor SET gen_no=?, autor_name=? WHERE autor_no = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, autor.getGen_no());
            stmt.setString(2, autor.getAutor_name());
            stmt.setInt(3,autor.getAutor_no());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
        }
    }
    
    public void excluir(int autor_no){
        String sql = "DELETE FROM autor WHERE autor_no = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,autor_no);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir produto: "+ e.getMessage());
        }
    }
    
    public Autor getAutor(int autor_no){
        String sql = "SELECT * FROM autor WHERE autor_no =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, autor_no);
            ResultSet rs = stmt.executeQuery();
            Autor autor = new Autor();
            rs.next();
            autor.setAutor_no(rs.getInt("autor_no"));
            autor.setAutor_name(rs.getString("autor_name"));
            autor.setGen_no(rs.getString("gen_no"));
            return autor;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Autor> getAutor(){
        String sql = "SELECT * FROM autor";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Autor> listaAutor = new ArrayList<>();
          while(rs.next()){
              Autor p = new Autor();
              p.setAutor_name(rs.getString("autor_name"));
              p.setAutor_no(rs.getInt("autor_no"));
              p.setGen_no(rs.getString("gen_no"));
              listaAutor.add(p);
          }
          return listaAutor;
        }catch(Exception e){
            return null;
        }
    }
}
