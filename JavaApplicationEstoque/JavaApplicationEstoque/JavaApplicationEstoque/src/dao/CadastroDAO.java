
package dao;

import beans.Cadastro;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {
       private Conexao conexao;
    private Connection conn;
    
    public CadastroDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Cadastro cadastro){
        String sql = "INSERT INTO cadastro (id, name, email, cpf, senha) VALUES (?,?,?,?,?) ";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,cadastro.getId());
            stmt.setString(2, cadastro.getName());
            stmt.setString(3, cadastro.getEmail());
            stmt.setString(4,cadastro.getCpf());
            stmt.setString(5,cadastro.getSenha());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao inserir cadastro: "+ e.getMessage());
        }
    }
    
    public void alterar(Cadastro cadastro){
        String sql = "UPDATE cadastro SET name=?, email=?, cpf=?, senha=? WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cadastro.getName());
            stmt.setString(2, cadastro.getEmail());
            stmt.setString(3, cadastro.getCpf());
            stmt.setString(4, cadastro.getSenha());
            stmt.setInt(5, cadastro.getId());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar livro: "+ e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM cadastro WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir produto: "+ e.getMessage());
        }
    }
    
    public Cadastro getCadastro(int id){
        String sql = "SELECT * FROM cadastro WHERE id =?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Cadastro cadastro = new Cadastro();
            rs.next();
            cadastro.setName(rs.getString("name"));
            cadastro.setEmail(rs.getString("email"));
            cadastro.setCpf(rs.getString("cpf"));
            cadastro.setId(rs.getInt("id"));
            cadastro.setSenha(rs.getString("senha"));
            return cadastro;
        
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
            return null;
        }
        
    }
    public List<Cadastro> getCadastro(){
        String sql = "SELECT * FROM cadastro";
        try{
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          List<Cadastro> listaCadastro = new ArrayList<>();
          while(rs.next()){
              Cadastro p = new Cadastro();
              p.setName(rs.getString("name"));
              p.setEmail(rs.getString("email"));
              p.setSenha(rs.getString("senha"));
              p.setCpf(rs.getString("cpf"));
              p.setId(rs.getInt("id"));
              listaCadastro.add(p);
          }
          return listaCadastro;
        }catch(Exception e){
            return null;
        }
    }
}
