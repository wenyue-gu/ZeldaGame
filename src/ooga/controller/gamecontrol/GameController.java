package ooga.controller.gamecontrol;

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
import java.util.ArrayList;
import java.util.List;

public class GameController {

  private ModelInterface myModel;
  private List<MainPlayerControl> myMainPlayerController = new ArrayList<>(); //user controled player
  private List<NPCControl> myNPCControl = new ArrayList<>();
  private GameStateController myGameStateController; //frontend
  private DataLoaderAPI myDataLoader;
  private boolean dark;

public GameController(ModelInterface model, DataLoaderAPI loader){
    myModel = model;
    myDataLoader = loader;
    myGameStateController = new AbstractGameStateController();
    setGameType("Zelda");
    setNPC();
  }

  public void keyInput(KeyCode code) {
     for(MainPlayerControl mpc:myMainPlayerController) mpc.keyInput(code);
  }


  private void setNPC(){
    for(Object NPC:myModel.getNPCs()){
      NPCControl npcControl = new NPCControl((Movable1D)NPC);
      myNPCControl.add(npcControl);
    }
  }

  private void setGameType(String gameType){
    for(Object player:myModel.getPlayers()) {
      MainPlayerControl curControl = new MainPlayerControl();
      curControl.setControl(gameType);
      curControl.setMyPlayer((Movable1D)player);
      myMainPlayerController.add(curControl);
    }
  }

  public void update(){
    for(NPCControl npc: myNPCControl) npc.update(); // update back-end //user-controlled player doesn't need to be updated, only NPC
    myGameStateController.update(); // update front-end
  }

  public Scene getScene(){
    return myGameStateController.getGameStateView();
    //return myView.getGameView();
  }

  public void setMode(boolean dark){
    this.dark = dark;
  }

}
