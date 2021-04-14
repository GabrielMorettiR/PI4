/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class ConexaoBD {
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        String url = "jdbc:derby://localhost:1527/NerdolasDatabase";
        String user = "adm";
        String password = "adm";
        return DriverManager.getConnection(url, user, password);
    }
}
