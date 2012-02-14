/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.controller;

import javax.swing.JFrame;

import payroll.model.MainModel;
import payroll.model.enums.UserClass;
import payroll.view.MainView;

/**
 *
 * @author matt
 */
public class MainController extends AbstractController {
	UserClass m_UserClass;
	
    public MainController() {
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
    
    
}
