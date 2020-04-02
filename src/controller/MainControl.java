package controller;

import controller.player.MainPlayerControl;
import javafx.scene.input.KeyCode;

import java.util.*;

public class MainControl{
  private MainPlayerControl myMainPlayerController;
  //private MainView myMainView;

  public MainControl(){
    //myMainView = new MainView();
    myMainPlayerController = new MainPlayerControl();
    initializeMap();
  }

  public void keyInput(KeyCode code) {
    myMainPlayerController.keyInput(code);
  }

  public void setGameType(String gameType){
    myMainPlayerController.setControl(gameType);
  }

  private void initializeMap(){

  }

//  public Scene getScene(){
//
//    Scene myScene = myMainView.getScene();
//    myScene.setOnKeyPressed(e -> keyInput(e.getCode().getName()));
//    return myScene;
//  }
}
