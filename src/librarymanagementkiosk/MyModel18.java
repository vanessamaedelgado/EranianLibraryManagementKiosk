/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementkiosk;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Clarisse
 */
public class MyModel18 extends AbstractTableModel {

       
    private String[] columns;
    private Object[][] rows;
    
    public MyModel18(){}
    
    public MyModel18(Object[][] data, String[] columnsName){
        this.columns = columnsName;
        this.rows = data;
   
    }

    public Class getColumnClass(int col){
        // number of column is 6
        if(col == 10) { return Icon.class; }
        else{
            return getValueAt(0, col).getClass();
            
        }
    }
    
    @Override
    public int getRowCount() {
        return this.rows.length;
    }

    @Override
    public int getColumnCount() {
       return this.columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.rows[rowIndex][columnIndex];
    }
    
    public String getColumnName(int col){
        return this.columns[col];
    
    }

}
