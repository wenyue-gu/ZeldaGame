package ooga;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.data.DataLoader;
import ooga.data.DataLoaderAPI;
import ooga.model.Model;

public class GameMain extends Application {
  // TODO: delete place holders after dataloader is implemented
  public static final String TITLE = "Zelda";
  public static final int WIDTH = 1070;
  public static final int HEIGHT = 820;
  public static final boolean RESIZABLE = true;

  private WindowControl myWindowControl;
  private Model myModel;
  private DataLoaderAPI myDataLoader;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) {
    myDataLoader = new DataLoader();
    myModel = new Model(myDataLoader,"Zelda");
    myWindowControl = new WindowControl(currentStage, myModel, myDataLoader);
    myWindowControl.showWindow(TITLE, HEIGHT, WIDTH, RESIZABLE);
    new GameZelda2D().start();
  }
}
