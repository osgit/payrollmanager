package payroll.model.elements;

public class Employee {
	String m_Name;
	int m_ID;
	int m_SSN;
	double m_PayRate;
	
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
