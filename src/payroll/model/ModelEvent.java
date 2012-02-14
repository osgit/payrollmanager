/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.model;

import java.awt.event.ActionEvent;
        
/**
 *
 * @author matt
 */
@SuppressWarnings("serial")
public class ModelEvent extends ActionEvent{
    private int m_amount;
    
    public ModelEvent(Object obj, int id, String message, int amount) {
        super(obj, id, message);
        m_amount = amount;
    }
    
    public int getAmount() { return m_amount; }
}
