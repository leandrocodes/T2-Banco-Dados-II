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
                status = "STATUS ----> conectado com sucesso";
            }else{
                status = "STATUS ----> não foi possivel realizar conexão";
            }
            
            System.out.println(status);
        }
        catch (ClassNotFoundException e){
            System.out.println("O driver expecificado não foi encontrado");
        }
        catch (SQLException e){
            System.out.println("Não foi possivel conectar ao Banco de Dados");
        }
        return connection;
    }
    
    public void create_table(Connection connector, String name) throws SQLException{
        Statement stnt;
        try{
        stnt = (Statement) connector.createStatement();
        String criaTabela = "    CREATE TABLE "+name+"(\n" +
                                "   IDVENDEDOR INT PRIMARY KEY AUTO_INCREMENT, \n" +
                                "   NOME VARCHAR(30) NOT NULL, \n" +
                                "   SEXO ENUM('M','F') NOT NULL, \n" +
                                "   EMAIL VARCHAR(50) UNIQUE, \n"+
                                "   CPF VARCHAR(15) UNIQUE,\n" +
                                "   JANEIRO FLOAT(10,2) NOT NULL,\n" +
                                "   FEVEREIRO FLOAT(10,2) NOT NULL,\n" +
                                "   MARCO FLOAT(10,2) NOT NULL\n" +
                                ");";
        stnt.execute(criaTabela);
        stnt.close();
        }
        catch (SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
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
        System.out.println("One way of Listing Tables");
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
