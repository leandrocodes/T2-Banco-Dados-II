package T2_BDII;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        MySQL database = new MySQL();
        Scanner input = new Scanner(System.in);

        String serverName = "localhost";
        String mydatabase = "T2_BDIII";
        String username = "daniel";
        String password = "123456789";
        
        try{
            Connection con = database.connect(serverName, mydatabase, username, password);
            database.create_tables(con);
            
            while(true){
                System.out.println("Escolha a opção desejada");
                System.out.println("(1) Inserir Dados");
                System.out.println("(2) Realizar Consultas");
                System.out.println("(3) Sair");
                switch (input.nextInt()) {
                    case 1:
                        input.nextLine();
                        switch (Insert.insert_into()) {
                            case 1:
                                database.insert_vend(con, Insert.insert_vendedor());
                                break;
                            case 2:
                                database.insert_tel(con, Insert.insert_telefone());
                                break;
                            case 3:
                                database.insert_end(con, Insert.insert_endereco());
                                break;
                            default:
                                break;
                        }
                        break;

                    case 2:
                        input.nextLine();
                        database.show_tables(con);
                        System.out.println("Digite a tabela na qual você que deseja realizar uma consulta:");
                        String table = input.nextLine();
                        System.out.println("Entre com a query");
                        String query = input.nextLine();
                        database.query(con, table, query);
                        break;
                    case 3:
                        input.close();
                        database.close_connection(con);
                        System.exit(0);
                    default:
                        break;
                }
            }
        }
        catch(SQLException err){
            System.out.println(err);
            System.exit(-1);
        }
        catch(NullPointerException err){
            System.out.println(err);
            System.exit(-1);
        }
    }
}
