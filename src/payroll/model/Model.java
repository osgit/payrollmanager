/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.model;

/**
 *
 * @author matt
 */
public interface Model {
    void notifyChanged(ModelEvent e);
}
