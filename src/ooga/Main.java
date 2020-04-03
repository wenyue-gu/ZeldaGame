package ooga;

import controller.WindowControl;
import controller.gamecontrol.GameController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

  private static final String TITLE = "Zelda";

  private GameController myGameControl;
  private WindowControl myWindowControl;
  //private Model myModel;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    //myModel = new Model();
    //myControl = new MainController(myModel);
    myWindowControl = new WindowControl(currentStage);
    currentStage.setScene(myWindowControl.getScene());
    currentStage.setTitle(TITLE);
    currentStage.setWidth(1070);
    currentStage.setHeight(820);
    currentStage.setResizable(true);
    currentStage.show();
  }

}
