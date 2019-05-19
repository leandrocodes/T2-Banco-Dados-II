/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T2_BDII;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Menu {
    JFrame f=new JFrame("Menu de Opções");
    JButton insert_data = new JButton ("Inserir Dados");
    JButton query = new JButton ("Realizar Consultas");
    JButton delete = new JButton ("Deletar Tabelas");
    JButton quit = new JButton ("Sair");  
	    
    Menu(){
        Container c= f.getContentPane();
        c.setLayout(new GridLayout(4,1));
        c.add(insert_data);
        c.add(query);
        c.add(delete);
        c.add(quit);
        f.setSize(200,150);
        f.setBounds(150,200,200,150);
        f.setVisible(true);      	
    }    
    
    public static void menu(MySQL database, String serverName, String mydatabase,
            String username, String password){
        
        Scanner input = new Scanner(System.in);
        
        try{
            Connection con = database.connect(serverName, mydatabase, username, password);
            database.create_tables(con);
            
            while(true){
                System.out.println("Escolha a opção desejada");
                System.out.println("(1) Inserir Dados");
                System.out.println("(2) Realizar Consultas");
                System.out.println("(3) Deletar Tabelas");
                System.out.println("(4) Sair");
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
                        input.nextLine();
                        database.show_tables(con);
                        System.out.println("Entre com a tabela que você deseja deletar");
                        database.delete_table(con, input.nextLine());
                        break;
                    case 4:
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
            System.exit(0);
        }
        catch(NullPointerException err){
            System.out.println(err);
            System.exit(0);
        }        
    }
    
    public static void main(String[] args) {
        new Menu();
    }
}
