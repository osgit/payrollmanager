package payroll.model;

/**
 * @author Olalekan */
public class PayrollException extends Exception {
    public PayrollException() {
    }

    public PayrollException(String msg) {
        super(msg);
    }

    public PayrollException(Exception e) {
        super(e);
    }

    public PayrollException(String msg, Exception e) {
        super(msg, e);
    }
}
