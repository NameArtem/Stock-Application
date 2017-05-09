package stock.main;

/**
 *
 * @author Lucky Pratama
 */

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class export_xl {
    private JTable table;
    private String table_title;
    int table_position = 0;
    private String file;
    int total_column= 0;
    
    public void setSource(JTable table_name){
        this.table= table_name;
    }
    
    public void setReport_Title(String report_title){
        this.table_title = report_title;
    }
    
    public void setPosition(int position){
        this.table_position = position;
    }
    
    public void setFile(String file){
        this.file = file;
    }
    
    public void export_excel(){
        stock s = new stock();
        try{
            FileOutputStream fileOut = null;
            
            //Craete workbook
            Workbook wb = new HSSFWorkbook();
            
            //Create Sheet 
            Sheet sheet = wb.createSheet("Stock Data");
            
            CellStyle style;
            style = wb.createCellStyle();
            style.setAlignment(CellStyle.VERTICAL_CENTER);
            
            Row titleRow = sheet.createRow(1);
            Cell titleCell = titleRow.createCell(table_position);
            titleCell.setCellValue(table_title);
            titleCell.setCellStyle(style);
            
            //Set column title
            Row row = sheet.createRow(1);
            for (int i =0; i < table.getColumnCount();i++){
                row.createCell(i).setCellValue(table.getColumnName(i));
            }
            
            int r= 2;
            int c = 2;
            
            int row_count = table.getRowCount()-1;
            for (r = 0; r <= row_count; r++){
                row = sheet.createRow(c);
                for (int i=0; i < table.getColumnCount(); i++){
                    row.createCell(i).setCellValue(table.getValueAt(r,i).toString());
                }
                c  = c +1;
            }
            
            //Set Column width
            for (int i = 0; i < table.getColumnCount(); i ++){
                sheet.autoSizeColumn(i);
            }
            sheet.setColumnWidth(0,4000);
            String tempfile = file;
            File filetemp = new File(tempfile);
            
            if (filetemp.exists()){
                filetemp.delete();
            }
            
            fileOut = new FileOutputStream(tempfile);
            wb.write(fileOut);
            try{
                Desktop.getDesktop().open(new File(file));            
            }
            catch(Exception e){
                s.information(e.getMessage());
            }
            
        }
               
        catch (Exception e){
            Logger.getLogger(export_xl.class.getName()).log(Level.SEVERE,null, e);
        
        }
    }
    
}




























