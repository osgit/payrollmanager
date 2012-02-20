/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import payroll.model.MainModel;
import payroll.model.PayrollException;
import payroll.model.enums.UserClass;
import payroll.view.MainView;

/**
 *
 * @author matt
 */
public class MainController extends AbstractController {
	UserClass m_UserClass;
	
    public MainController() throws PayrollException {
        setModel(new MainModel());
        setView(new MainView((MainModel)getModel(), this));
        MainView view = (MainView)getView();
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);  /* maximize window */
        
        /* get login */
        AuthenticationController authenticator = new AuthenticationController();  /* Prompts user, authenticates 
        													   and provides user class  */
        m_UserClass = authenticator.getUserClass();
        
        System.out.println("Create the appropriate environment.");
        view.setView(m_UserClass);
    }
    
    public static void handlePayrollException(PayrollException pe) {
            String str = "";
            StackTraceElement[] ste = null;
            Throwable t = pe;
            do {
                str += (t.getMessage() != null ? t.getMessage() + "\n" : "");
                ste = t.getStackTrace();
                for (StackTraceElement s : ste) {
                    str += s.toString() + "\n";
                }
                str += "\n";
            }while ((t = t.getCause()) != null);
            JOptionPane.showMessageDialog(null, str,
                    "Error starting application", JOptionPane.ERROR_MESSAGE);
    }
}
