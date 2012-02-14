package payroll.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import payroll.controller.MainController;
import payroll.model.EmployeeTableModel;
import payroll.model.MainModel;
import payroll.model.ModelEvent;
import payroll.model.enums.UserClass;

@SuppressWarnings("serial")
public class MainView extends JFrameView implements TableModelListener {
	// Constants 
	
	JTabbedPane m_tabs;
	Handler m_handler = new Handler();
	
	public MainView(MainModel model, MainController controller) {
		super(model, controller);
		m_tabs = new JTabbedPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(m_tabs);
	}
	
	// Event handling code 
	public void modelChanged(ModelEvent event) {
		// Model changed events go here
	}
	
	 // Inner classes for Event Handling 
	class Handler implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			if("bonus".equals(e.getActionCommand())) {
				// Launch bonus dialog (controller API)
			} else if("finalize".equals(e.getActionCommand())) {
				// finalize payroll (controller API)
			} else if ("registerCancel".equals(e.getActionCommand())) {
				// cancel register entries
			} else if ("registerSubmit".equals(e.getActionCommand())) {
				// submit register entries
			}
		}
	}
	
	public void setView(UserClass userClass) {
		System.out.println("Checking user class.");
		if (userClass.equals(UserClass.ADMIN))
		{
			System.out.println("Building admin view.");
			
			/* Create the history tab */
			JPanel historyTab = new JPanel();
			
			/*  create the history tree component */
			JTree tree;
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Past Pay Periods");
			createNodes(root);
			tree = new JTree(root);
			tree.setRootVisible(false);
			/* set width for the tree */
			
			/* Create the employee list table*/
			JTable historyTable = new JTable(new EmployeeTableModel((MainController)getController()));
			historyTable.getModel().addTableModelListener(this);
			
			/* Combine all the history elements and add the tab */
			historyTab.setLayout(new BoxLayout(historyTab, BoxLayout.X_AXIS));
			historyTab.add(new JScrollPane(tree));
			historyTab.add(new JScrollPane(historyTable));
			m_tabs.add("Past Pay Periods", historyTab);
			
			/* Create the calculator tab */
			JPanel calculatorTab = new JPanel();
			
			/* create the list content */
			JTable calculatorTable = new JTable(new EmployeeTableModel((MainController)getController()));
			calculatorTable.getModel().addTableModelListener(this);
			
			JPanel calculatorTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JButton bonusButton = new JButton("Add Bonuses");
			bonusButton.setMnemonic(KeyEvent.VK_B);
			bonusButton.setActionCommand("bonus");
			bonusButton.addActionListener(m_handler);
			JButton finalizeButton = new JButton("Finalize Payroll");
			finalizeButton.setMnemonic(KeyEvent.VK_P);
			finalizeButton.setActionCommand("finalize");
			finalizeButton.addActionListener(m_handler);
			calculatorTop.add(bonusButton);
			calculatorTop.add(finalizeButton);
			
			calculatorTab.setLayout(new BoxLayout(calculatorTab, BoxLayout.Y_AXIS));
			calculatorTab.add(calculatorTop);
			calculatorTab.add(new JScrollPane(calculatorTable));
			m_tabs.add("Process Recent Pay Period", calculatorTab);
			
			System.out.println("Done building admin View.");
			
			/* Redraw the UI */
			this.repaint();
		} else {
			System.out.println("Building user view.");
			
			/* create register tab */
			JPanel registerTab = new JPanel(false);
			JPanel registerPanel = new JPanel(false);
			registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.X_AXIS));
			// TODO: Center the registerPanel in the window (X and Y)
			
			/* create left side */
			JPanel leftPanel = new JPanel(false);
			leftPanel.setLayout(new GridLayout(0,1));
			leftPanel.add(new JLabel("Enter hours for: ", JLabel.RIGHT));
			leftPanel.add(new JLabel("Pay Period: ", JLabel.RIGHT));
			leftPanel.add(new JLabel("Regular Hours: ", JLabel.RIGHT));
			leftPanel.add(new JLabel("Overtime Hours: ", JLabel.RIGHT));
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setMnemonic(KeyEvent.VK_C);
			cancelButton.setActionCommand("registerCancel");
			cancelButton.addActionListener(m_handler);
			leftPanel.add(cancelButton, JButton.RIGHT);
			
			
			/* create right side */ 
			JPanel rightPanel = new JPanel(false);
			rightPanel.setLayout(new GridLayout(0,1));
			JTextField regularHours = new JTextField("");
			JTextField overtimeHours = new JTextField("");
			rightPanel.add(new JLabel(" User")); /* Implement controller method to get user's name */
			rightPanel.add(new JLabel(" MM/DD/YYYY - MM/DD/YYY")); /* Implement controller method to get pay period */
			rightPanel.add(regularHours);
			rightPanel.add(overtimeHours);
			JButton submitButton = new JButton("submit");
			submitButton.setMnemonic(KeyEvent.VK_S);
			submitButton.setActionCommand("registerSubmit");
			submitButton.addActionListener(m_handler);
			rightPanel.add(submitButton, JButton.RIGHT);
			
			registerPanel.add(leftPanel);
			registerPanel.add(rightPanel);
			
			
			/* Create pay slip tab */
			JPanel payslipTab = new JPanel(false);
			payslipTab.setLayout(new BorderLayout());
			JPanel payslipPanel = new JPanel();
			payslipPanel.setLayout(new BorderLayout());
			
			/* create the controls across the top */
			JPanel payslipTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
			payslipTop.add(new JLabel("Select Pay Period: "));
			String[] sampleComboBoxItems = {"1/1/2012", "1/8/2012", "1/15/2012", "1/22/2012"};
			JComboBox selectPeriodCombo = new JComboBox(sampleComboBoxItems); /* Use custom ComboBoxModel */
			payslipTop.add(selectPeriodCombo);
			
			/* Create the box that will display the pay slip */
			JTextArea payslipArea = new JTextArea("Pay slip info will go here");
			/* TODO: write code for the controller to provide pay slip data from the model */
			
			payslipPanel.add(payslipTop, BorderLayout.PAGE_START);
			payslipPanel.add(new JScrollPane(payslipArea), BorderLayout.CENTER);
			
			
			registerTab.add(registerPanel);
			payslipTab.add(payslipPanel, BorderLayout.CENTER);
			m_tabs.add("Enter Hours", registerTab);
			m_tabs.add("View Pay Slip", payslipTab);
		}
	}
	
	private void createNodes(DefaultMutableTreeNode root) {
		
		/* Need a method in AdminController to get a list of months and their pay periods.
		   For each month create a DefaultMutableTreeNode and root.add() it then for each 
		   pay period within that month create another DefaultMutableTreeNode and then 
		   add that to the month node.
		   
		   Alternatively we could extend TreeModel similarly to the way EmployeeTableModel extends
		   AbstractTableModel.  I haven't looked into it too deeply but it might facilitate handling
		   click events from within the tree.
		*/
	}

	@SuppressWarnings("unused")
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel)e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		
		/* All above information might not be needed.
		 * Here is where we update the database each time the admin
		 * alters information in the table.  If I understand correctly
		 * the field in the table already has the new information.  If
		 * we want to pop up a confirmation box for confirm editing then
		 * that might need to be implemented in the EmployeeTableModel code. */
	}
	

		
	public static void main(String[] args) {new MainController(); }
}
