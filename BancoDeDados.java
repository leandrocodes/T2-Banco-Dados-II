package T2_BDII;

//import com.mysql.jdbc.DatabaseMetaData;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BancoDeDados {
    private static String status;

    public Connection connect(String serverName, String mydatabase, String username, String password) throws SQLException{
        Connection connection = null;
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase;
            connection = DriverManager.getConnection(url, username, password);
            
            if(connection != null){
                status = "STATUS ----> Conectado com sucesso";
            }else{
                status = "STATUS ----> Não foi possivel realizar conexão";
            }
            System.out.println(status);
        }
        catch (ClassNotFoundException err){
            System.out.println("O driver expecificado não foi encontrado");
            System.out.println(err+"\n");
        }
        catch (SQLException err){
            System.out.println("Não foi possivel conectar ao Banco de Dados");
            System.out.println(err+"\n");
        }
        return connection;
    }
    
    public void create_table(Connection connector, String table){
        Statement stnt;
        try{
            stnt = (Statement) connector.createStatement();
            stnt.execute(table);
            System.out.println("Tabela criada com sucesso!\n");
            stnt.close();
        }
        catch(SQLException err){
            System.out.println("Erro ao criar tabela!");
            System.out.println(err+"\n");
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,err);
        }
    }
    
    public void delete_table(Connection connector, String table){
        Statement stnt;
        try{
            stnt = (Statement) connector.createStatement();
            stnt.execute("DROP TABLE "+table);
            System.out.println("Tabela deletada com sucesso!\n");
            stnt.close();
        }
        catch(SQLException err){
            System.out.println("Erro ao deletar tabela!");
            System.out.println(err+"\n");
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,err);
        }
    }    
    
    public void insert(Connection connector, Vendedor vend) throws SQLException{
        
        String sql = "insert into "
                + "VENDEDOR(IDVENDEDOR,NOME,SEXO,EMAIL,CPF,JANEIRO,FEVEREIRO,MARCO)"
                + " values(?,?,?,?,?,?,?,?)";
//        String.format("%s %s", "oi","dani");
        PreparedStatement stmt = connector.prepareStatement(sql);
    
        //inserir dados
        stmt.setString(1, vend.id);
        stmt.setString(2, vend.nome);
        stmt.setString(3, vend.sexo);
        stmt.setString(4, vend.email);
        stmt.setString(5, vend.cpf);
        stmt.setString(6, vend.janeiro);
        stmt.setString(7, vend.fevereiro);     
        stmt.setString(8, vend.marco);  
        
        //executar
        stmt.execute();
        stmt.close();
        System.out.println("\nDados gravados com sucesso!\n");
    }
    
    public void query(Connection connector, String query) throws SQLException{
        PreparedStatement stmt = connector.prepareStatement(query);
        //EXECUTA UM SELECT
        
        ResultSet rs = stmt.executeQuery();
        System.out.println();
        while (rs.next()){
            String id = rs.getString("IDVENDEDOR");
            String nome = rs.getString("NOME");
            String sexo = rs.getString("SEXO");
            String email = rs.getString("EMAIL");
            String cpf = rs.getString("CPF");
            String janeiro = rs.getString("JANEIRO");  
            String fevereiro = rs.getString("FEVEREIRO");
            String marco = rs.getString("MARCO");   
            System.out.println(
                    "ID: "+id+"\n"
                    + "Nome: "+nome+"\n"
                    +"Sexo: "+sexo+"\n"
                    +"Email: " +email+"\n"
                    +"CPF: "+cpf+"\n"
                    +"Janeiro: "+janeiro+"\n"
                    +"Fevereiro: "+fevereiro+"\n"
                    +"Março: "+marco);
            System.out.println();
        }
        stmt.close();
    }

    public void show_tables(Connection connector) throws SQLException{
        DatabaseMetaData meta = connector.getMetaData();
        ResultSet rs1 = meta.getTables(null, null, null,new String[] {"TABLE"});
//        ResultSet rs2 = meta.getTables(null, null,"%", null);
        System.out.println("Tabelas existentes no banco de dados");
        while (rs1.next()){
            System.out.println(rs1.getString("TABLE_NAME"));
        }
//        System.out.println("Another way of Listing Tables");
//        while(rs2.next()){
//            System.out.println(rs2.getString(3));                
//        }
    }
    
    public void close_connection(Connection connector) throws SQLException{
        connector.close();
    }
}
