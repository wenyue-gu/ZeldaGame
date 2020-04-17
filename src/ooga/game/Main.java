package ooga.game;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.data.DataLoader;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
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
  public void start(Stage currentStage) throws DataLoadingException {
    myDataLoader = new DataLoader();
    myModel = new Model(myDataLoader,"Zelda");
    myWindowControl = new WindowControl(currentStage);
    myWindowControl.setModel(myModel);
    myWindowControl.setDataLoader(myDataLoader);
    currentStage.setTitle(TITLE);
    currentStage.setWidth(1070);
    currentStage.setHeight(820);
    currentStage.setResizable(true);
    currentStage.show();
  }

}
