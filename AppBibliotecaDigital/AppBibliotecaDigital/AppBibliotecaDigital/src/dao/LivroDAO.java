
package dao;
import beans.Livro;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Conexao conexao;
    private Connection conn;
    
    public LivroDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Livro livro){
        String sql = "INSERT INTO livro (book_title , gen_name, editora_name, autor_name) VALUES (?,?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            //stmt.setInt(1, livro.getBook_no());
            stmt.setString(1, livro.getBook_title());
            stmt.setString(2, livro.getGen_name());
            stmt.setString(3, livro.getEditora_name());
            stmt.setString(4, livro.getAutor_name());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir Livro: "+ e.getMessage());
        }
    }
    
   public void alterar(Livro livro){
    try{
        String query = "SELECT autor_name, gen_name, book_title, editora_name FROM livro WHERE book_no = ?";
        PreparedStatement selectStmt = this.conn.prepareStatement(query);
        selectStmt.setInt(1, livro.getBook_no());
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {
            String autorName = livro.getAutor_name().isEmpty() ? rs.getString("autor_name") : livro.getAutor_name();
            String genName = livro.getGen_name().isEmpty() ? rs.getString("gen_name") : livro.getGen_name();
            String bookTitle = livro.getBook_title().isEmpty() ? rs.getString("book_title") : livro.getBook_title();
            String editoraName = livro.getEditora_name().isEmpty() ? rs.getString("editora_name") : livro.getEditora_name();

            String sql = "UPDATE livro SET autor_name=?, gen_name=?, book_title=?, editora_name=? WHERE book_no = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, autorName);
            stmt.setString(2, genName);
            stmt.setString(3, bookTitle);
            stmt.setString(4, editoraName);
            stmt.setInt(5, livro.getBook_no());

            stmt.execute();
        }
    } catch (Exception e) {
        System.out.println("Erro ao atualizar livro: " + e.getMessage());
    }
}

    
    public void excluir(int book_no){
        String sql = "DELETE FROM livro WHERE book_no = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,book_no);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir produto: "+ e.getMessage());
        }
    }
    
    public Livro getLivro(int book_no){
        String sql = "SELECT * FROM livro WHERE book_no =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,book_no);
            ResultSet rs = stmt.executeQuery();
           Livro livro = new Livro();
            rs.next();
            livro.setAutor_name(rs.getString("autor_name"));
            livro.setBook_title(rs.getString("book_title"));
            livro.setBook_no(rs.getInt("book_no"));
            livro.setEditora_name(rs.getString("editora_name"));
            livro.setGen_name(rs.getString("gen_name"));
            return livro;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Livro> getLivro(){
        String sql = "SELECT * FROM livro";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Livro> listaLivro = new ArrayList<>();
          while(rs.next()){
              Livro l = new Livro();
              l.setAutor_name(rs.getString("autor_name"));
              l.setBook_no(rs.getInt("book_no"));
              l.setBook_title(rs.getString("book_title"));
              l.setGen_name(rs.getString("gen_name"));
              l.setEditora_name(rs.getString("editora_name"));
              listaLivro.add(l);
          }
          return listaLivro;
        }catch(Exception e){
            return null;
        }
}
}
