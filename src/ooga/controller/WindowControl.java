package ooga.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.Model;
import ooga.controller.gamecontrol.GameController;
import ooga.model.interfaces.ModelInterface;
import ooga.view.game_menu.AbstractGameMenuView;
import ooga.view.game_menu.GameMenuView;


public class WindowControl {

  //private MenuView myMenuView;
  private Button myStartButton;
  private Button myExitButton;
  private GameController myGameController;
  private Stage myStage;
  private GameMenuView myMenuView;
  private ModelInterface myModel;

  public WindowControl(Stage currentStage){
    myStage = currentStage;
    myMenuView = new AbstractGameMenuView();
    setMenuScene();
    myStartButton = myMenuView.getNewGameButton();
    myStartButton.setOnAction(e->startGame(currentStage));
    myExitButton = myMenuView.getExitGameButton();
    myExitButton.setOnAction(e->currentStage.close());
  }

  public void setModel(Model model){
    myModel = model;
  }

  private void setMenuScene(){
    myStage.setScene(myMenuView.getMenuView().getScene());
  }

  public Button getMyStartButton() {
    return myStartButton;
  }

  public Button getMyExitButton() {
    return myExitButton;
  }

  private void startGame(Stage currentStage) {
    myGameController = new GameController(myModel);
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
}
