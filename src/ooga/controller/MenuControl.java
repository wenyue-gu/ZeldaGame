package ooga.controller;

import ooga.controller.gamecontrol.GameController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuControl {

  //private MenuView myMenuView;
  private Button myStartButton;
  private Button myExitButton;
  private GameController myGameController;
  private Stage myStage;

  public MenuControl(Stage currentStage){
    //myMenuView = new MenuView();
    //myStartButton = myMenuView.getNewGameButton();
    //myExitButton = myMenuView.getExitGameButton();
    //Scene myScene = myMenuView.getScene();
  }

  public Button getMyStartButton() {
    return myStartButton;
  }

  public Button getMyExitButton() {
    return myExitButton;
  }

  public Scene getScene(){
    return null;
    //return myMenuView.getMenuView();
  }
}
