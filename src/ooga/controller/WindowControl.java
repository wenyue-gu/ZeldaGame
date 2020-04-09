package ooga.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.data.DataLoader;
import ooga.controller.gamecontrol.GameController;
import ooga.data.DataLoaderAPI;
import ooga.model.Model;
import ooga.model.interfaces.ModelInterface;
import ooga.view.game_menu.AbstractGameMenuView;
import ooga.view.game_menu.GameMenuView;


public class WindowControl {

  private Button myStartButton;
  private Button myExitButton;
  private GameController myGameController;
  private Stage myStage;
  private GameMenuView myMenuView;
  private ModelInterface myModel;
  private DataLoaderAPI myDataLoader;
  private Button myChangeBackgroundButton;
  private boolean dark = false;

  public WindowControl(Stage currentStage){
    myStage = currentStage;
    myMenuView = new AbstractGameMenuView();
    setMenuScene();
    initializeButtons();
  }

  public void setModel(Model model){
    myModel = model;
  }

  public void setDataLoader(DataLoaderAPI Loader){
    myDataLoader = Loader;
  }

  private void setMenuScene(){
    myStage.setScene(myMenuView.getMenuView());
  }


  private void initializeButtons(){
    myStartButton = myMenuView.getNewGameButton();
    myStartButton.setOnAction(e->startGame(myStage));
    myExitButton = myMenuView.getExitGameButton();
    myExitButton.setOnAction(e->myStage.close());
    myChangeBackgroundButton = myMenuView.getBackgroundButton();
    myChangeBackgroundButton.setOnAction(e->switchMode());
  }


  private void startGame(Stage currentStage) {
    myGameController = new GameController(myModel, myDataLoader);
    myGameController.setMode(dark);
    Scene myScene = myGameController.getScene();

    myScene.setOnKeyPressed(e -> myGameController.keyInput(e.getCode()));
    currentStage.setScene(myScene);
    currentStage.show();
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        myGameController.update();
      }
    };
    timer.start();
  }

  private void switchMode(){
    dark = !dark;
    myMenuView.switchMode(dark);
  }
}
