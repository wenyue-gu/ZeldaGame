package ooga.controller.gamecontrol;

import ooga.controller.gamecontrol.NPC.MainNPCControl;
import ooga.controller.gamecontrol.player.MainPlayerControl;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ooga.data.DataLoaderAPI;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.MovingState;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movables.Movable1D;
import ooga.view.game_view.game_state.AbstractGameStateController;
import ooga.view.game_view.game_state.GameStateController;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameController {

  private ModelInterface myModel;
  private List<MainPlayerControl> myMainPlayerController = new ArrayList<>(); //user controled player
  private List<MainNPCControl> myNPCControl = new ArrayList<>();
  private GameStateController myGameStateController; //frontend
  private PauseControl myPauseControl;
  private DataLoaderAPI myDataLoader;
  private boolean dark;

public GameController(ModelInterface model, DataLoaderAPI loader){
    myModel = model;
    myDataLoader = loader;
    myGameStateController = new AbstractGameStateController();
    setUpPlayerandNPC();
  }

//  public void keyInput(KeyCode code) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//     for(MainPlayerControl mpc:myMainPlayerController) mpc.keyInput(code);
//  }

  private void setUpPlayerandNPC(){
    //setGameType(myDataLoader.getGameType());
    setGameType(1);
    for(MainPlayerControl mpc:myMainPlayerController){
      mpc.setID();
      //mpc.setKeyCodeMap(myDataLoader.loadKeyCode(mpc.getID(), "KeyCode"));
    }
  }

  private void setGameType(int gameType){
    for(Object player:myModel.getPlayers()) {
      MainPlayerControl curControl = new MainPlayerControl();
      curControl.setControl(gameType);
      curControl.setMyPlayer((Movable1D)player);
      myMainPlayerController.add(curControl);
    }

    for(Object NPC:myModel.getNPCs()){
      MainNPCControl npcControl = new MainNPCControl();
      npcControl.setControl(gameType);
      npcControl.setMyNPC((Movable1D)NPC);
      myNPCControl.add(npcControl);
    }
  }

  public void update(){
    //TODO: check this
    for(MainNPCControl npc: myNPCControl) npc.update(); // update back-end
    for(MainPlayerControl mpc: myMainPlayerController) mpc.updateKey();
    myGameStateController.update(); // update front-end
  }

//  public Scene getScene(){
//    return myGameStateController.getGameStateView();
//    //return myView.getGameView();
//  }

  public void setMode(boolean dark){
    this.dark = dark;
  }

  public void keyReleased() {
    for(MainPlayerControl mpc:myMainPlayerController) mpc.keyReleased();
  }
}
