package ooga;

import controller.MainControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

  private static final String TITLE = "Zelda";

  private MainControl myControl;

  public static void main(String[] args) {
    launch(args);
  }

    @Override
  public void start(Stage currentStage) {
    myControl = new MainControl();
    //TODO: get from data?
    myControl.setGameType("Zelda");
    Scene myScene = null;//myControl.getScene(?)

    myScene.setOnKeyPressed(e -> myControl.keyInput(e.getCode()));
    currentStage.setScene(myScene);
    currentStage.setTitle(TITLE);

    //TODO: change numbers to data from file
    currentStage.setWidth(1070);
    currentStage.setHeight(820);
    currentStage.setResizable(true);
    currentStage.show();
  }






}
