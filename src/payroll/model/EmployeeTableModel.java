package payroll.model;

import javax.swing.table.AbstractTableModel;
import payroll.controller.DataAccess;
import payroll.controller.MainController;

@SuppressWarnings("serial")
public class EmployeeTableModel extends AbstractTableModel {
	// Headers will change once we have a set table stucture for hours.
	private String[] m_Columns = {"ID", "Last Name", "First Name", "Regular Hours", "Overtime Hours", "Calculated Pay"};
	private Object[][] m_TableData;

	
	public EmployeeTableModel(MainController controller)
            throws PayrollException {
		DataAccess da = new DataAccess();
		
		/* TODO
		 **Need to implement this function to take a variable on how to sort the data.
		 * deafult: sort by last_name ASC
		 * 
		 **Need to implement funtion to take a month, or "pay period"
		 * for the tree structure... might implement another function for tree though.
		 */
		m_TableData = da.popEmployeeTable(m_Columns.length); //	populate the table given the number of headers.
		da.close();
	}
	public int getColumnCount() {
		return m_Columns.length;
	}
	
	public int getRowCount() {
		return m_TableData.length ;
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
