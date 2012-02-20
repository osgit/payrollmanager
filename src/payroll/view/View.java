package payroll.view;

import payroll.controller.Controller;
import payroll.model.Model;

/**
 * @author matt */
public interface View {
    Controller getController();

    void setController(Controller controller);

    Model getModel();

    void setModel(Model model);
}
