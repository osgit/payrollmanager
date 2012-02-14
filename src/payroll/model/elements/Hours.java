package payroll.model.elements;

public class Hours {
	float m_RegularHours;
	float m_OvertimeHours;
	
	public void setRegularHours(float hours) {
		m_RegularHours = hours;
	}
	
	public float getRegularHours() {
		return m_RegularHours;
	}
	
	public void setOvertimeHours(float hours) {
		m_OvertimeHours = hours;
	}
	
	public float getOvertimeHours() {
		return m_OvertimeHours;
	}

}
