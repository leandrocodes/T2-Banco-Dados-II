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
            status = (connection != null) ? "Conectado com sucesso!" : "Não foi possivel realizar conexão!";
            System.out.println(status);
        }
        catch (ClassNotFoundException err){
            System.out.println("O driver expecificado não foi encontrado!");
            System.out.println(err.getMessage()+"\n");
            System.exit(0);            
        }
        catch (SQLException err){
            System.out.println("Não foi possivel conectar ao Banco de Dados!");
            System.out.println(err.getMessage()+"\n");
            System.exit(0);
        }
        return connection;
    }
    
    public void create_tables(Connection con){
        Statement stnt;
        try{
            stnt = (Statement) con.createStatement();
            stnt.execute("CREATE TABLE IF NOT EXISTS VENDEDOR(\n" +
            "	IDVENDEDOR INT NOT NULL AUTO_INCREMENT,\n" +
            "	NOME VARCHAR(30) NOT NULL,\n" +
            "	SEXO ENUM('M','F') NOT NULL,\n" +
            "	EMAIL VARCHAR(50) UNIQUE,\n" +
            "	CPF VARCHAR(15) UNIQUE,\n" +
            "	JANEIRO FLOAT(10,2) NOT NULL,\n" +
            "	FEVEREIRO FLOAT(10,2) NOT NULL,\n" +
            "	MARCO FLOAT(10,2) NOT NULL,\n" +      
            "   PRIMARY KEY(IDVENDEDOR)\n" +        
            ")");
            
        stnt.execute("CREATE TABLE IF NOT EXISTS TELEFONE(\n" +
            "	IDTELEFONE INT NOT NULL AUTO_INCREMENT,\n" +
            "	TIPO ENUM('COM','RES','CEL'),\n" +
            "	NUMERO VARCHAR(10),\n" +
            "	ID_VENDEDOR INT,\n" +     
            "   PRIMARY KEY(IDTELEFONE),\n" +     
            "	FOREIGN KEY(ID_VENDEDOR) REFERENCES VENDEDOR(IDVENDEDOR)\n" +
            ")");
        
        stnt.execute("CREATE TABLE IF NOT EXISTS ENDERECO(\n" +
            "	IDENDERECO INT NOT NULL AUTO_INCREMENT,\n" +
            "	RUA VARCHAR(30) NOT NULL,\n" +
            "	BAIRRO VARCHAR(30) NOT NULL,\n" +
            "	CIDADE VARCHAR(30) NOT NULL,\n" +
            "	ESTADO CHAR(2) NOT NULL,\n" +
            "	ID_VENDEDOR INT UNIQUE,\n" +      
            "   PRIMARY KEY(IDENDERECO),\n" +      
            "	FOREIGN KEY(ID_VENDEDOR) REFERENCES VENDEDOR(IDVENDEDOR)\n" +
            ")");     
        
            System.out.println("Tabelas criadas com sucesso!\n");
            stnt.close();
        }
        catch(SQLException err){
            System.out.println("Erro ao criar tabela!");
            System.out.println(err.getMessage()+"\n");
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
            System.out.println(err.getMessage()+"\n");
        }
    }    
    
    public void insert_vend(Connection con, Vendedor vend) throws SQLException{
        
        String sql = "insert into "
                + "VENDEDOR(NOME,SEXO,EMAIL,CPF,JANEIRO,FEVEREIRO,MARCO)"
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        stmt.setString(1, vend.getNome());
        stmt.setString(2, vend.getSexo());
        stmt.setString(3, vend.getEmail());
        stmt.setString(4, vend.getCpf());
        stmt.setString(5, vend.getJaneiro());
        stmt.setString(6, vend.getFevereiro());     
        stmt.setString(7, vend.getMarco());  
        
        try{
            stmt.execute();
            System.out.println("Dados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir os dados!");
            System.out.println(err.getMessage()+'\n');
        }
        stmt.close();
    }
    
    public void insert_tel(Connection con, Telefone tel) throws SQLException{
        
        String sql = "insert into "
                + "TELEFONE(TIPO,NUMERO,ID_VENDEDOR)"
                + " values(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        // stmt.setString(1, String.valueOf(tel.getIdtelefone()));
        stmt.setString(1, tel.getTipo());
        stmt.setString(2, tel.getNumero());
        stmt.setString(3, tel.getId_vendedor());
        
        try{
            stmt.execute();
            System.out.println("Dados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir dado!");
            System.out.println(err.getMessage()+'\n');
        }
        stmt.close();
    }
    
    public void insert_end(Connection con, Endereco end) throws SQLException{
        
        String sql = "insert into "
                + "ENDERECO(RUA,BAIRRO,CIDADE,ESTADO,ID_VENDEDOR)"
                + " values(?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        // stmt.setString(1, String.valueOf(end.getIdendereco()));
        stmt.setString(1, end.getRua());
        stmt.setString(2, end.getBairro());
        stmt.setString(3, end.getCidade());
        stmt.setString(4, end.getEstado());
        stmt.setString(5, end.getId_vendedor()); 
        
        try{
            stmt.execute();
            System.out.println("Dados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir dado!");
            System.out.println(err.getMessage()+'\n');
        }
        stmt.close();
    }    
    
    public void query(Connection con, String table, String query) throws SQLException{
        PreparedStatement stmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        
        if(!(table.equals("VENDEDOR") || table.equals("ENDERECO") || table.equals("TELEFONE"))){
            System.out.println("Por favor, entre com uma tabela existente no banco!\n"); return;
        }
        
        try{   
            ResultSet rs = stmt.executeQuery();
            if(table.equals("VENDEDOR")){
                while (rs.next()){
                    long idvendedor = rs.getLong("IDVENDEDOR");
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
                    System.out.println();
                }

            }
            else if(table.equals("TELEFONE")){
                while (rs.next()){
                    long idtelefone = rs.getLong("IDTELEFONE");
                    String tipo = rs.getString("TIPO");
                    String numero = rs.getString("NUMERO");
                    String id_vendedor = rs.getString("ID_VENDEDOR");  
                    System.out.println(
                        "ID: "+idtelefone+"\n"
                        + "Tipo: "+tipo+"\n"
                        +"Número: "+numero+"\n"
                        +"ID do Vendedor: " +id_vendedor);
                    System.out.println();
                }             
            }
            else if(table.equals("ENDERECO")){
                while (rs.next()){
                    long idendereco = rs.getLong("IDENDERECO");
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
                    System.out.println();
                }            
            }        
        }
        catch(SQLException err){
            System.out.println("Por favor, selecione a mesma tabela dita anteriormente!");
            System.out.println(err.getMessage()+"\n");
        }
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
