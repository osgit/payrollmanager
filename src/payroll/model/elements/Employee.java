package payroll.model.elements;

public class Employee {
	public String m_Name;
	public int m_ID;
	public int m_SSN;
	public double m_PayRate;
	
	public Employee() {
		/* initialize */
	}
	
	public String getName() {
		return m_Name;
	}
	
	public int getID() {
		return m_ID;
	}
	
	public int getSSN() {
		return m_SSN;
	}
	
	public double getPayRate() {
		return m_PayRate;
	}

}
