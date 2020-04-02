package ooga;

import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.model.ZeldaCharacter;


public class Main extends Application {

  private static final String TITLE = "Zelda";

  private MainController myControl;
  //private ZeldaCharacter myPlayer;

  public static void main(String[] args) {
    launch(args);
  }

    @Override
  public void start(Stage currentStage) {
    //myPlayer = new ZeldaCharacter();
    myControl = new MainController();
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
