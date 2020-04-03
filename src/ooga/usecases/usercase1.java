package ooga.usecases;

import ooga.controller.WindowControl;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.data.DataLoaderAPI;
import ooga.model.Model;


public class usercase1 extends Application {

  private static final String TITLE = "User Example 1";

  private WindowControl myWindowControl;
  private Model myModel;
  private DataLoaderAPI myDataLoader;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    myDataLoader = new ooga.DataLoader();
    myModel = new Model(myDataLoader);
    myWindowControl = new WindowControl(currentStage);
    myWindowControl.setModel(myModel);

    currentStage.setTitle(TITLE);
    currentStage.setWidth(1080);
    currentStage.setHeight(820);
    currentStage.setResizable(true);
    currentStage.show();

    //This user example shows a basic assembly of all components in the application.

  }

}
