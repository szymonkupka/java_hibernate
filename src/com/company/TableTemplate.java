package com.company;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.List;

public class TableTemplate extends AbstractTableModel implements  Serializable {
    String []  COLUMN_NAMES ;
    List<List<Object>> data;

    public TableTemplate( TableData taledata ){
        super();
        this.COLUMN_NAMES = taledata.getLabels();
        this.data = taledata.getData();


    }


    public int getRowCount(){


        return this.data.size();
    }
    public int getColumnCount(){
        return  this.COLUMN_NAMES.length;
    }
    public Object getValueAt(int row, int column){

        return this.data.get(row).get(column);
    }
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}

