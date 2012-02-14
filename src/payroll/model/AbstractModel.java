/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author matt
 */
public abstract class AbstractModel implements Model{
    private ArrayList<ModelListener> m_listeners = new ArrayList<ModelListener>(5);
    
    @SuppressWarnings("unchecked")
	@Override
    public void notifyChanged(ModelEvent event) {
        ArrayList<ModelListener> list = ((ArrayList<ModelListener>)m_listeners.clone());
        Iterator<ModelListener> it = list.iterator();
        while(it.hasNext()) {
            ModelListener ml = (ModelListener)it.next();
            ml.modelChanged(event);
        }
    }
    
    public void addModelListener(ModelListener l) {
        m_listeners.add(l);
    }
    
    public void removeModelListener(ModelListener l) {
        m_listeners.remove(l);
    }
}
