/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.view;

import javax.swing.JFrame;
import payroll.controller.Controller;
import payroll.model.AbstractModel;
import payroll.model.Model;
import payroll.model.ModelListener;

/**
 *
 * @author matt
 */

@SuppressWarnings("serial")
public abstract class JFrameView extends JFrame implements View, ModelListener {
    private Model m_model;
    private Controller m_controller;
    
    public JFrameView(Model model, Controller controller) {
        m_model = model;
        m_controller = controller;
    }
    
    public void registerWithModel() {
        ((AbstractModel)m_model).addModelListener(this);
    }
    
    @Override
    public Controller getController() { return m_controller; }
    
    @Override
    public void setController(Controller controller) {m_controller = controller; }
    
    @Override
    public Model getModel() { return m_model; }
    
    @Override
    public void setModel(Model model) { m_model = model; }
}
