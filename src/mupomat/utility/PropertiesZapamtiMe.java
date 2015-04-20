/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mupomat.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author SrKy
 */
public class PropertiesZapamtiMe {
    private static Properties properties;
    
    public static Properties getProperties() throws IOException{
        
        if(properties==null){
        properties = new Properties();
        properties.load(new FileInputStream("zapamtime.properties"));
        }
        
        
        return properties;
    }
}
