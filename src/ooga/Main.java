package ooga;

import ooga.controller.WindowControl;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.data.DataLoaderAPI;
import ooga.model.Model;


public class Main extends Application {

  private static final String TITLE = "Zelda";

  private WindowControl myWindowControl;
  private Model myModel;
  private DataLoaderAPI myDataLoader;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    myDataLoader = new DataLoader();
    myModel = new Model(myDataLoader);
    myWindowControl = new WindowControl(currentStage);
    myWindowControl.setModel(myModel);
    currentStage.setTitle(TITLE);
    currentStage.setWidth(1070);
    currentStage.setHeight(820);
    currentStage.setResizable(true);
    currentStage.show();
  }

}
