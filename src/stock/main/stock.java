package stock.main;

/**
 *
 * @author Lucky Pratama
 */
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class stock {
    private Statement smt;
    private String sql;
    private ResultSet rs;
    private database db;
    private boolean status;
    
    //Information method
    public void information(String inf){
        JOptionPane.showMessageDialog(null, inf, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Clean component method
    public void clean_component(JTextField code, JTextField product, JTextField uom, JTextField cogs, JTextField selling_price,
            JTextField stock){
    
        code.setText("");
        product.setText("");
        uom.setText("");
        cogs.setText("");
        selling_price.setText("");
        stock.setText("");
    }
    
    // Get status method
    public boolean getStatus(){
        return this.status;
    }
    
    //Method show_data in table
    public void show_data(DefaultTableModel dtm, JTable table_name){
       
        //Create object from database class
        db = new database();
        
        //Get connection
        db.dbConnection();
        
        try{
        //Create statement 
        smt = db.getConn().createStatement();
        
        sql = "select * from stock_data order by product_code";
        
        // Create ResultSet
        rs = smt.executeQuery(sql);
       
        while (rs.next()){
            dtm.addRow(new Object[] {rs.getString("product_code"), rs.getString("product_name"),rs.getString("uom"), rs.getInt("cogs"), rs.getInt("selling_price"),
                        rs.getInt("stock"), rs.getInt("selling_price")*rs.getInt("stock")});
           
        }
        table_name.setModel(dtm);
        rs.close();
        smt.close();
        db.getConn().close();
        }
        catch (Exception e){
            e.printStackTrace();
        }   
    }
    
    // Insert method to insert data into table in our database
    public void insert (String product_code,String product_name, String uom, int cogs, int selling_price, int stock){
        db = new database();
        db.dbConnection();
       
        try{
        smt = db.getConn().createStatement();
        
        sql = "insert into stock_data values ('"+product_code+"', '"+product_name+"', '"+uom+"', '"+cogs+"', '"+selling_price+"', '"+
                                                      stock+"')";
        
        smt.executeUpdate(sql);
        smt.close();
        db.getConn().close();
        status = true;
        information("Successfully saved");
        }
        catch (Exception e){
            status = false;
            information("Insert data failed!!\n"+e);
        }
    }
    
    // Delete record from stock_data table
    public void delete(String product_code){
        db = new database();
        db.dbConnection();
        
        try{  
            sql = "delete from stock_data where product_code=?";
            PreparedStatement delete = db.getConn().prepareStatement(sql);
            delete.setString(1, product_code);
            delete.executeUpdate();
            
            delete.close();
            db.getConn().close();
            status = true;
            
            information("Successfully deleted");
        }
        catch (Exception e){
            status = false;
            information("Delete failed!!\n"+e);
        }    
    }
    
    //Edit stock_data table
    public void edit(String product_code, String product_name, String uom, int cogs, int selling_price, int stock){
        db = new database();
        db.dbConnection();
        
        try{
            sql = "update stock_data set product_name=?, uom=?, cogs=?, selling_price=?, stock=?"
                    +" where product_code=?";
            PreparedStatement update = db.getConn().prepareStatement(sql);
            update.setString(1, product_name);
            update.setString(2, uom);
            update.setInt(3, cogs);
            update.setInt(4, selling_price);
            update.setInt(5, stock);
            update.setString(6, product_code);
            update.executeUpdate();
            
            update.close();
            db.getConn().close();
            status= true;
            
            
            information("Data successfully edited");
            
        }
        catch (Exception e){
            status= false;
            information("Edit data failed!!\n"+e);
        }
    }
    
    //Show data_show method to show data in component (JTextField)
    public String[] data_show (String code){
        rs = null;
        String[] data = null;
        db = new database();
        db.dbConnection();
        
        try{
            smt = db.getConn().createStatement();
            sql = "select * from stock_data where product_code='"+code+"'";
            rs = smt.executeQuery(sql);
            
            ResultSetMetaData meta = rs.getMetaData();
            int column_count = meta.getColumnCount();
            data = new String [column_count];
            
            if (rs.next()){
                for (int i=0; i < column_count; i++){
                    data[i] = rs.getString(i+1);
                    if(rs.wasNull()){
                        data[i]="";
                    }
                }
            }
            else{
                information("Data not available!!");
            
            }
                status = true;
                rs.close();
                smt.close();
                db.getConn().close();
        }
        catch (Exception e){
            status = false;
            information(e.getMessage());           
        }
        return data;
    }

        // Method to clean table
    public void clean_table(DefaultTableModel model){
        int row_count = model.getRowCount()-1;
        int i = row_count;
        
        while(i >= 0 ){
            model.removeRow(i);
            i--;
        }
    }
    
    //Search data table
    public void search(DefaultTableModel model, JTable table, String product){
        clean_table(model); 
        database db = new database();
        db.dbConnection();
        try{
            smt = db.getConn().createStatement();
            sql = "select * from stock_data where product_name like '%"+product+"%'";
            rs = smt.executeQuery(sql);
            
            while (rs.next()){
                model.addRow( new Object[] {rs.getString("product_code"),
                rs.getString("product_name"),
                rs.getString("uom"),
                rs.getInt("cogs"),
                rs.getInt("selling_price"),
                rs.getInt("stock"),
                rs.getInt("cogs")*rs.getInt("selling_price")});
                     }
            table.setModel(model);
            rs.close();
            smt.close();
            db.getConn().close();
        }
        catch(Exception e){
            information(e.getMessage());
        }
    }
}


















