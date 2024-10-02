
package dao;
import beans.Generos;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenerosDAO {
       private Conexao conexao;
    private Connection conn;
    
    public GenerosDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Generos generos){
        String sql = "INSERT INTO generos (gen_no, gen_name) VALUES (?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, generos.getGen_no());
            stmt.setString(2, generos.getGen_name());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir produto: "+ e.getMessage());
        }
    }
    
    public void alterar(Generos generos){
        String sql = "UPDATE generos SET gen_name=? WHERE gen_no = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, generos.getGen_name());
            stmt.setInt(2, generos.getGen_no());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
        }
    }
    
    public void excluir(int gen_no){
        String sql = "DELETE FROM generos WHERE gen_no = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,gen_no);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir produto: "+ e.getMessage());
        }
    }
    
    public Generos getGeneros(int gen_no){
        String sql = "SELECT * FROM generos WHERE gen_no =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, gen_no);
            ResultSet rs = stmt.executeQuery();
            Generos generos = new Generos();
            rs.next();
            generos.setGen_name(rs.getString("gen_name"));
            generos.setGen_no(rs.getInt("gen_no"));
            return generos;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Generos> getGeneros(){
        String sql = "SELECT * FROM generos";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Generos> listaGeneros = new ArrayList<>();
          while(rs.next()){
              Generos p = new Generos();
              p.setGen_name(rs.getString("gen_name"));
              p.setGen_no(rs.getInt("gen_no"));
              listaGeneros.add(p);
          }
          return listaGeneros;
        }catch(Exception e){
            return null;
        }
}
}
