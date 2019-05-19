package T2_BDII;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        MySQL database = new MySQL();
        
        String serverName = "localhost";
        String mydatabase = "T2_BDII";
        String username = "daniel";
        String password = "123456789";
        
        Connection con = database.connect(serverName, mydatabase, username, password);
        new Menu(database, con, serverName, mydatabase, username, password);

//        String serverName = "remotemysql.com";
//        String mydatabase = "vsOSJjA3bF";
//        String username = "vsOSJjA3bF";
//        String password = "Z4qGUTiTxK";          
        
    }
}
