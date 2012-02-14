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
public interface Controller {
    void setModel(Model model);
    Model getModel();
    View getView();
    void setView(View view);
}
