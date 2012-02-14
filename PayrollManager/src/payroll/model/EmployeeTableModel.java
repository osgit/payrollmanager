package payroll.model;

import javax.swing.table.AbstractTableModel;

import payroll.controller.MainController;

@SuppressWarnings("serial")
public class EmployeeTableModel extends AbstractTableModel {
	private String[] m_Columns = {"Name", "Regular Hours", "Overtime Hours", "Calculated Pay"}; /* Incomplete  this need to be updated */
	Object[][] m_TableData = {{"Billy Bob", new Float(34.6), new Float(0), new Float(345.60)}};  /* initializer for testing */
	
	public EmployeeTableModel(MainController controller){
		/* 
		 * Need a method in the controller to return the table data
		 * so we can populate m_tableData
		 */
	}
	public int getColumnCount() {
		return m_Columns.length;
	}
	
	public int getRowCount() {
		return m_TableData.length;
	}
	
	public String getColumnName(int col) {
		return m_Columns[col];
	}
	
	public Object getValueAt(int row, int col) {
		return m_TableData[row][col];
	}
	
	public void setValueAt(Object value, int row, int col) {
		m_TableData[row][col] = value;
		fireTableCellUpdated(row, col);
	}
	
}
