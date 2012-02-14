/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.controller;

import payroll.model.Model;
import payroll.view.View;

/**
 *
 * @author matt
 */
public abstract class AbstractController implements Controller{
    private View m_view;
    private Model m_model;
    
    @Override
    public void setModel(Model model) { m_model = model; }
    
    @Override
    public Model getModel() { return m_model; }
    
    @Override
    public View getView() { return m_view; }
    
    @Override
    public void setView(View view) { m_view = view; }
}
