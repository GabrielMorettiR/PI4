package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ConexaoBD {
    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        String url = "jdbc:derby://localhost:1527/NerdolasDatabase";
        String user = "adm";
        String password = "adm";
        return DriverManager.getConnection(url, user, password);
    }
}
