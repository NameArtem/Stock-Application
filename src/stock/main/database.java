package stock.main;

/**
 *
 * @author Lucky Pratama
 */

import java.sql.*;
public class database {
    private Connection conn;
    private boolean cn_status;
      
    //Setter method for connection
    public void dbConnection(){
        
        try{
            //Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Open a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock","root","");
            
            cn_status = true;
        }
        catch (Exception e){
            e.printStackTrace();
            
            cn_status = false;
        }
        
    }
    
    //Getter method for connection
    public Connection getConn(){
        return this.conn;
    }
    
    //Getter method for connection
    public boolean getStatus(){
        return this.cn_status;
    }
    

    
}
