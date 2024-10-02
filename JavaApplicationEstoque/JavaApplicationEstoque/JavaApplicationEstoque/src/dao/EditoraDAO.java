
package dao;
import beans.Editora;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
       private Conexao conexao;
    private Connection conn;
    
    public EditoraDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Editora editora){
        String sql = "INSERT INTO editora (autor_name, book_no, editora_name, editora_no) VALUES (?,?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, editora.getAutor_name());
            stmt.setString(2, editora.getBook_no());
            stmt.setString(3,editora.getEditora_name());
            stmt.setInt(4,editora.getEditora_no());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir produto: "+ e.getMessage());
        }
    }
    
    public void alterar(Editora editora){
        String sql = "UPDATE editora SET editora_name=?, book_no=?, autor_name=? WHERE editora_no = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, editora.getAutor_name());
            stmt.setString(2, editora.getBook_no());
            stmt.setString(3,editora.getEditora_name());
            stmt.setInt(4,editora.getEditora_no());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
        }
    }
    
    public void excluir(int editora_no){
        String sql = "DELETE FROM editora WHERE editora_no = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,editora_no);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir produto: "+ e.getMessage());
        }
    }
    
    public Editora getEditora(int editora_no){
        String sql = "SELECT * FROM editora WHERE editora_no =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, editora_no);
            ResultSet rs = stmt.executeQuery();
            Editora editora = new Editora();
            rs.next();
            editora.setAutor_name(rs.getString("autor_name"));
            editora.setBook_no(rs.getString("book_no"));
            editora.setEditora_name(rs.getString("editora_name"));
            editora.setEditora_no(rs.getInt("editora_no"));
            return editora;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Editora> getEditora(){
        String sql = "SELECT * FROM editora";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Editora> listaEditora = new ArrayList<>();
          while(rs.next()){
              Editora p = new Editora();
              p.setAutor_name(rs.getString("autor_name"));
              p.setBook_no(rs.getString("book_no"));
              p.setEditora_name(rs.getString("editora_name"));
              p.setEditora_no(rs.getInt("editora_no"));
              listaEditora.add(p);
          }
          return listaEditora;
        }catch(Exception e){
            return null;
        }
    }
}
