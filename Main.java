package T2_BDII;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws SQLException{
            MySQL database = new MySQL();
            Scanner input = new Scanner(System.in);
            Vendedor vend = new Vendedor();

            String serverName = "localhost";
            String mydatabase = "T2_BDII";
            String username = "daniel";
            String password = "123456789";
            
            Connection con = database.connect(serverName, mydatabase, username, password);
            
            database.create_tables(con);
            
            while(true){
                System.out.println("Escolha a opção desejada");
                System.out.println("(1) Inserir Dados");
                System.out.println("(2) Realizar Consultas");
                System.out.println("(3) Sair");
                switch(input.nextInt()){
                    case 1:
                        input.nextLine();
                        System.out.println("Entre com os seguintes dados");
                        
                        vend.setId(null);
                        System.out.print("Nome: ");vend.setNome(input.nextLine());
                        System.out.print("Sexo: ");vend.setSexo(input.nextLine());
                        System.out.print("Email: ");vend.setEmail(input.nextLine());
                        System.out.print("CPF: ");vend.setCpf(input.nextLine());
                        System.out.print("Janeiro: ");vend.setJaneiro(input.nextLine());
                        System.out.print("Fevereiro: ");vend.setFevereiro(input.nextLine());
                        System.out.print("Março: ");vend.setMarco(input.nextLine());
                        database.insert(con, vend);
                        break;
                    case 2:
                        input.nextLine();
                        database.show_tables(con);
                        System.out.println("Digite a consulta que deseja realizar:");
                        database.query(con, input.nextLine());
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
}
