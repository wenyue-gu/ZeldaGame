package controller;

import javafx.scene.input.KeyCode;
import ooga.model.ZeldaCharacter;

import java.util.*;

public class PlayerController implements ZeldaController{
  private ZeldaCharacter myPlayer;
//  private PlayerView myPlayerView;

//  private Map<KeyCode, String> keyMap= new HashMap<>();

  public PlayerController(ZeldaCharacter player){
    myPlayer = player;
//    myPlayerView = new playerView(player);
  }
  @Override
  public void keyInput(KeyCode key){
    switch(key) {
      case W: myPlayer.moveInY(5); break;
    }
  }


  }