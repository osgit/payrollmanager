package payroll.model.enums;

public enum Months {
	JANUARY(1, "Jan."),
	FEBRURARY(2, "Feb"),
	MARCH(3, "Mar."),
	APRIL(4, "Apr."),
	MAY(5, "May"),
	JUNE(6, "June"),
	JULY(7, "July"),
	AUGUST(8, "Aug."),
	SEPTEMBER(9, "Sept."),
	OCTOBER(10, "Oct."),
	NOVEMBER(11, "Nov."),
	DECEMDBER(12, "Dec.");

	private int m_Number;
	private String m_Label;
	
	private Months(int number, String label) {
		m_Number = number;
		m_Label = label;
	}
	
	public int getNumber() {
		return m_Number;
	}
	
	public String getLabel() {
		return m_Label;
	}
}
