package ooga;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.MainController;


public class Main extends Application {

  private static final String TITLE = "Zelda";

  private MainController myControl;
  //private Model myModel;

  public static void main(String[] args) {
    launch(args);
  }

    @Override
  public void start(Stage currentStage) {
    //myModel = new Model();
    //myControl = new MainController(myModel);
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

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        myControl.update();
      }
    };
    timer.start();
  }






}
