/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mupomat.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author SrKy
 */
public class MySqlBazaPodataka {

    public static Connection getConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        Properties properties = PropertiesSingleton.getProperties();
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://" + properties.getProperty("server", "localhost") + "/"
                + properties.getProperty("imebaze", "mupbaza") + "?useUnicode=true&characterEncoding=UTF-8&"
                + "user=" + properties.getProperty("korisnik", "edunova") + "&password=" + properties.getProperty("lozinka", "edunova"));
    }
}
