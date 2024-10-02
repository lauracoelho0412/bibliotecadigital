package conexao;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
    
    public Connection getConexao(){
        try{
            //Estabeler conex�o
            Connection conn = DriverManager.getConnection(
                      "jdbc:mysql://localhost:3306/bd_biblioteca", //linha de conex�o
                      "root", //usuario do mysql
                      "" //senha
            );
            return conn; 
        }catch( Exception e){
        //erro na conex�o
        System.out.println("Erro conex�o: " + e.getMessage());
        return null;
        }
    }
    
}
