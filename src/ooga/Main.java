package ooga;

import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Main extends Application {

  private static final String TITLE = "Zelda";

  private MainController myControl;
  private MainView myMainView;

  public static void main(String[] args) {
    launch(args);
  }

    @Override
  public void start(Stage currentStage) {
    myMainView = new MainView();
    myControl = new MainController();
    Scene myScene = myMainView.getScene();
    myScene.setOnKeyPressed(e -> myControl.handleKeyInput(e.getCode()));
    currentStage.setScene(myScene);
    currentStage.setTitle(TITLE);
    currentStage.setWidth(1070);
    currentStage.setHeight(820);
    currentStage.setResizable(true);
    currentStage.show();
  }






}
