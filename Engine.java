import Controller.EngineController;
import Model.EngineModel;
import View.EngineView;

public class Engine {
    public static void main(String[] args) {

        EngineModel Model = new EngineModel();
        EngineView View = new EngineView();
        EngineController Controller = new EngineController(Model, View);

        View.setLocationRelativeTo(null);
        View.setVisible(true);
    }
}
