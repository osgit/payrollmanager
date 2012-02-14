package payroll.model;

import java.util.ArrayList;
import java.util.Map;

import payroll.model.elements.PaySlip;
import payroll.model.enums.Months;

public class AdminModel extends AbstractModel{
	ArrayList<PaySlip> m_PayRoll;
	Map<Months, ArrayList<String>> m_History;
	
	public AdminModel() {
		/* initialize the model */
	}
}
