package T2_BDII;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MySQL {
    private static String status;

    public Connection connect(String serverName, String mydatabase, String username, String password) throws SQLException{
        Connection connection = null;
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase;
            connection = DriverManager.getConnection(url, username, password);
            
            if(connection != null) status = "Conectado com sucesso!";
            else status = "Não foi possivel realizar conexão!";
            
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
    
    public void create_tables(Connection con){
        Statement stnt;
        try{
            stnt = (Statement) con.createStatement();
            stnt.execute("CREATE TABLE IF NOT EXISTS VENDEDOR(\n" +
            "	IDVENDEDOR INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "	NOME VARCHAR(30) NOT NULL,\n" +
            "	SEXO ENUM('M','F') NOT NULL,\n" +
            "	EMAIL VARCHAR(50) UNIQUE,\n" +
            "	CPF VARCHAR(15) UNIQUE,\n" +
            "	JANEIRO FLOAT(10,2) NOT NULL,\n" +
            "	FEVEREIRO FLOAT(10,2) NOT NULL,\n" +
            "	MARCO FLOAT(10,2) NOT NULL\n" +
            ")");
            
        stnt.execute("CREATE TABLE IF NOT EXISTS TELEFONE(\n" +
            "	IDTELEFONE INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "	TIPO ENUM('COM','RES','CEL'),\n" +
            "	NUMERO VARCHAR(10),\n" +
            "	ID_VENDEDOR INT,\n" +
            "	FOREIGN KEY(ID_VENDEDOR) REFERENCES VENDEDOR(IDVENDEDOR)\n" +
            ")");
        
        stnt.execute("CREATE TABLE IF NOT EXISTS ENDERECO(\n" +
            "	IDENDERECO INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "	RUA VARCHAR(30) NOT NULL,\n" +
            "	BAIRRO VARCHAR(30) NOT NULL,\n" +
            "	CIDADE VARCHAR(30) NOT NULL,\n" +
            "	ESTADO CHAR(2) NOT NULL,\n" +
            "	ID_VENDEDOR INT UNIQUE,\n" +
            "	FOREIGN KEY(ID_VENDEDOR) REFERENCES VENDEDOR(IDVENDEDOR)\n" +
            ")");     
        
            System.out.println("Tabelas criadas com sucesso!\n");
            stnt.close();
        }
        catch(SQLException err){
            System.out.println("Erro ao criar tabela!");
            System.out.println(err+"\n");
        }
    }
    
    public void delete_table(Connection con, String table){
        Statement stnt;
        try{
            stnt = (Statement) con.createStatement();
            stnt.execute("DROP TABLE "+table);
            System.out.println("Tabela deletada com sucesso!\n");
            stnt.close();
        }
        catch(SQLException err){
            System.out.println("Erro ao deletar tabela!");
            System.out.println(err+"\n");
        }
    }    
    
    public void insert_vend(Connection con, Vendedor vend) throws SQLException{
        
        String sql = "insert into "
                + "VENDEDOR(IDVENDEDOR,NOME,SEXO,EMAIL,CPF,JANEIRO,FEVEREIRO,MARCO)"
                + " values(?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        stmt.setString(1, vend.idvendedor);
        stmt.setString(2, vend.nome);
        stmt.setString(3, vend.sexo);
        stmt.setString(4, vend.email);
        stmt.setString(5, vend.cpf);
        stmt.setString(6, vend.janeiro);
        stmt.setString(7, vend.fevereiro);     
        stmt.setString(8, vend.marco);  
        
        try{
            stmt.execute();
            System.out.println("\nDados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir dado!");
            System.out.println(err);
            System.out.println();
        }
        stmt.close();
    }
    
    public void insert_tel(Connection con, Telefone tel) throws SQLException{
        
        String sql = "insert into "
                + "TELEFONE(IDTELEFONE,TIPO,NUMERO,ID_VENDEDOR)"
                + " values(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        stmt.setString(1, tel.idtelefone);
        stmt.setString(2, tel.tipo);
        stmt.setString(3, tel.numero);
        stmt.setString(4, tel.id_vendedor);
        
        try{
            stmt.execute();
            System.out.println("\nDados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir dado!");
            System.out.println(err);
            System.out.println();
        }
        stmt.close();
    }
    
    public void insert_end(Connection con, Endereco end) throws SQLException{
        
        String sql = "insert into "
                + "ENDERECO(IDENDERECO,RUA,BAIRRO,CIDADE,ESTADO,ID_VENDEDOR)"
                + " values(?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        stmt.setString(1, end.idendereco);
        stmt.setString(2, end.rua);
        stmt.setString(3, end.bairro);
        stmt.setString(4, end.cidade);
        stmt.setString(5, end.estado);
        stmt.setString(6, end.id_vendedor); 
        
        try{
            stmt.execute();
            System.out.println("\nDados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir dado!");
            System.out.println(err);
            System.out.println();
        }
        stmt.close();
    }    
    
    public void query(Connection con, String table, String query) throws SQLException{
        PreparedStatement stmt = con.prepareStatement(query);
        
        ResultSet rs = stmt.executeQuery();
        System.out.println();
        if(table.equals("VENDEDOR")){
            while (rs.next()){
                String idvendedor = rs.getString("IDVENDEDOR");
                String nome = rs.getString("NOME");
                String sexo = rs.getString("SEXO");
                String email = rs.getString("EMAIL");
                String cpf = rs.getString("CPF");
                String janeiro = rs.getString("JANEIRO");  
                String fevereiro = rs.getString("FEVEREIRO");
                String marco = rs.getString("MARCO");   
                System.out.println(
                    "ID: "+idvendedor+"\n"
                    + "Nome: "+nome+"\n"
                    +"Sexo: "+sexo+"\n"
                    +"Email: " +email+"\n"
                    +"CPF: "+cpf+"\n"
                    +"Janeiro: "+janeiro+"\n"
                    +"Fevereiro: "+fevereiro+"\n"
                    +"Março: "+marco);
            }          
        }
        else if(table.equals("TELEFONE")){
            while (rs.next()){
                String idtelefone = rs.getString("IDTELEFONE");
                String tipo = rs.getString("TIPO");
                String numero = rs.getString("NUMERO");
                String id_vendedor = rs.getString("ID_VENDEDOR");  
                System.out.println(
                    "ID: "+idtelefone+"\n"
                    + "Tipo: "+tipo+"\n"
                    +"Número: "+numero+"\n"
                    +"ID do Vendedor: " +id_vendedor);
            }             
        }
        else if(table.equals("ENDERECO")){
            while (rs.next()){
                String idendereco = rs.getString("IDENDERECO");
                String rua = rs.getString("RUA");
                String bairro = rs.getString("BAIRRO");
                String cidade = rs.getString("CIDADE");
                String estado = rs.getString("ESTADO");
                String id_vendedor = rs.getString("ID_VENDEDOR");  
                System.out.println(
                    "ID: "+idendereco+"\n"
                    + "Rua: "+rua+"\n"
                    +"Bairro: "+bairro+"\n"
                    + "Cidade: "+cidade+"\n"
                    + "Estado: "+estado+"\n"        
                    +"ID do Vendedor: " +id_vendedor);
            }            
        }
        System.out.println();
        stmt.close();
    }

    public void show_tables(Connection con) throws SQLException{
        DatabaseMetaData meta = con.getMetaData();
        ResultSet rs1 = meta.getTables(null, null, null,new String[] {"TABLE"});
        System.out.println("\nTabelas existentes no banco de dados");
        while (rs1.next()){
            System.out.println(rs1.getString("TABLE_NAME"));
        }
        System.out.println();
    }
    
    public void close_connection(Connection con) throws SQLException{
        con.close();
    }
}
