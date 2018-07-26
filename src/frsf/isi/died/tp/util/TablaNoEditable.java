package frsf.isi.died.tp.util;

import javax.swing.table.DefaultTableModel;

public class TablaNoEditable extends DefaultTableModel {

		   @Override 
		   public boolean isCellEditable(int row, int column){
		       return false;
		   }
		    
}
