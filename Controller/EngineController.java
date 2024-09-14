package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.EngineModel;
import View.EngineView;

public class EngineController {
    
    private EngineModel model;
    private EngineView view;

    public EngineController(EngineModel model, EngineView view){

        this.view = view;
        this.model = model;

        this.view.SendButtonListener(new SendButtonListener());
    }

    class SendButtonListener implements ActionListener{
              
        public void actionPerformed(ActionEvent e) {
            
            String cowCode = "";
            cowCode = view.getCowCode();
            model.executeFromCode(cowCode);
            // view.setResultMassage(model.getResultMassage());
            view.showPopup(view, model.getResultMassage());
            view.resetInputs();
        }
    }
}
