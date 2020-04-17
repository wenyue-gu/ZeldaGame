package ooga.controller.gamecontrol;

import javafx.animation.AnimationTimer;
import ooga.controller.WindowControl;
import ooga.controller.gamecontrol.NPC.MainNPCControl;
import ooga.controller.gamecontrol.player.MainPlayerControl;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ooga.data.DataLoaderAPI;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.MovingState;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movables.Movable1D;
import ooga.view.game_view.game_state.state2d.GameState2DView;
import org.lwjgl.glfw.GLFW;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameController {

  private ModelInterface myModel;
  private List<MainPlayerControl> myMainPlayerController = new ArrayList<>(); //user controled player
  private List<MainNPCControl> myNPCControl = new ArrayList<>();
  private PauseControl myPauseControl;
  private DataLoaderAPI myDataLoader;
  private boolean dark;
  private GameState2DView myGameView;
  private AnimationTimer myTimer;

public GameController(ModelInterface model, DataLoaderAPI loader){
    myModel = model;
    myDataLoader = loader;
    myPauseControl = new PauseControl();
    setUpPlayerandNPC();
  }

//  public void keyInput(KeyCode code) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//     for(MainPlayerControl mpc:myMainPlayerController) mpc.keyInput(code);
//  }

  public void startTimer(){
    myTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        update();
      }
    };
    myTimer.start();
    myPauseControl.setTimer(myTimer);
  }

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
    if(myGameView.isKeyDown(GLFW.GLFW_KEY_P)) myPauseControl.showMenu();
    myGameView.updateWindow();
  }

  public void setMode(boolean dark){
    this.dark = dark;
  }

  public void setView(GameState2DView view){
    myGameView = view;
    myPauseControl.setView(view);
    for(MainPlayerControl mpc:myMainPlayerController) mpc.setView(view);
  }

//  public void keyReleased() {
//    for(MainPlayerControl mpc:myMainPlayerController) mpc.keyReleased();
//  }

  public void setWindowControl(WindowControl windowControl) {
    myPauseControl.setWindowControl(windowControl);
  }
}
