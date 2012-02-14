package payroll.model.elements;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class PaySlip {
	float m_TotalPay;
	float m_HourlyPay;
	float m_Bonus;
	Hours m_Hours;
	Employee m_Employee;
	Calendar m_PeriodStart;
	Calendar m_PeriodEnd;
	Calendar m_PayDate;
	
	public PaySlip() {
		/* initialize from model */
	}
	
	public float getTotalPay() {
		return m_TotalPay;
	}
	
	public float getHourlyPay() {
		return m_HourlyPay;
	}
	
	public float getBonus() {
		return m_Bonus;
	}
	
	public float getRegularHours() {
		return m_Hours.getRegularHours();
	}
	
	public float getOvertimeHours() {
		return m_Hours.getOvertimeHours();
	}
	
	public String getName() {
		return m_Employee.getName();
	}
	
	public int getID() {
		return m_Employee.getID();
	}
	
	public int getSSN() {
		return m_Employee.getSSN();
	}
	
	public double getPayRate() {
		return m_Employee.getPayRate();
	}
	
	public String getPeriod() {
		SimpleDateFormat date_format = new SimpleDateFormat("mm/dd/yyyy");
		return (date_format.format(m_PeriodStart) + " - " + date_format.format(m_PeriodEnd));
	}

	public String getPayDay() {
		SimpleDateFormat date_format = new SimpleDateFormat("mm/dd/yyyy");
		return (date_format.format(m_PayDate));
	}
	
}
