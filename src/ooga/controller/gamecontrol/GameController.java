package ooga.controller.gamecontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import ooga.controller.WindowControl;
import ooga.controller.gamecontrol.NPC.MainNPCControl;
import ooga.controller.gamecontrol.player.MainPlayerControl;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.DataStorer;
import ooga.data.DataStorerAPI;
import ooga.game.GameZelda2DSingle;
import ooga.model.Model;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movables.Movable1D;
import org.lwjgl.glfw.GLFW;

public class GameController {

  private ModelInterface myModel;
  private List<MainPlayerControl> myMainPlayerController = new ArrayList<>(); //user controled player
  private List<MainNPCControl> myNPCControl = new ArrayList<>();
  private PauseControl myPauseControl;
  private WindowControl myWindowControl;
  private DataLoaderAPI myDataLoader;
  private DataStorerAPI myDataStorer;
  private boolean dark;
  private String language;
  private GameZelda2DSingle myGameView;
  private AnimationTimer myTimer;

  private int score = 100;

  public GameController(DataStorerAPI storer) throws DataLoadingException {
    myModel = new Model(storer.getDataLoader());
    myDataLoader = storer.getDataLoader();
    myDataStorer = storer;
    myPauseControl = new PauseControl(this);
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
    System.out.println(myDataLoader.getGameType());
    setGameType(myDataLoader.getGameType());
    for (MainPlayerControl mpc : myMainPlayerController) {
      mpc.setID();
      //mpc.setKeyCodeMap(myDataLoader.loadKeyCode(mpc.getID(), "KeyCode"));
        try {
            mpc.setNewKeyMap(myDataLoader.loadKeyCode(mpc.getID()));
        }
        catch(Exception e){
            System.out.println("load key error");
        }
    }
  }

  private void setGameType(int gameType) {
    for (Object player : myModel.getPlayers().values()) {
      MainPlayerControl curControl = new MainPlayerControl();
      curControl.setControl(gameType);
      curControl.setMyPlayer((Movable1D) player);
      myMainPlayerController.add(curControl);
    }

    for (Object NPC : myModel.getNPCs().values()) {
      MainNPCControl npcControl = new MainNPCControl();
      npcControl.setControl(gameType);
      npcControl.setMyNPC((Movable1D) NPC);
      myNPCControl.add(npcControl);
    }
  }

  public void update() {
    for (MainNPCControl npc : myNPCControl) {
      npc.update();
    }
    for (MainPlayerControl mpc : myMainPlayerController) {
      mpc.updateKey();
      if(mpc.checkScore(score)) finishGame(mpc);
    }
    if(myGameView.getView().isKeyDown(GLFW.GLFW_KEY_P))pause();
  }

  public void pause(){
    myPauseControl.updateScore(getSScoreList());
    myPauseControl.showMenu();
  }

  private void finishGame(MainPlayerControl mpc) {
    //TODO: finish game and print id and score
  }

  public void setMode(boolean dark) {
    this.dark = dark;
    myPauseControl.setMode(dark);
  }

  public void setLanguage(String language){
    this.language = language;
    myPauseControl.setLanguage(language);
  }

  public void setView(GameZelda2DSingle view) {
    myGameView = view;
    myPauseControl.setView(view.getView());
    for (MainPlayerControl mpc : myMainPlayerController) {
      mpc.setView(view);
    }
  }

  public int getPlayerSize() {
      return myMainPlayerController.size();
  }
  public void setWindowControl(WindowControl windowControl) {
    myWindowControl = windowControl;
    myPauseControl.setWindowControl(windowControl);
  }

  public void keyReleased() {
    for (MainPlayerControl mpc : myMainPlayerController) {
      mpc.keyReleased();
    }
  }

  public void save() {
    for(MainPlayerControl mpc:myMainPlayerController){
      ((DataStorer)myDataStorer).storeCharacter(mpc.getID(), (ZeldaCharacter)mpc.getPlayer());
    }
    myDataStorer.writeAllDataIntoDisk();
    myWindowControl.saveUser((int)((ZeldaPlayer)myMainPlayerController.get(0).getPlayer()).getScore());
    System.out.println("game controller - save method called");
  }

  public void setInitLife(int i) {
    for(MainPlayerControl mpc:myMainPlayerController){
      ((ZeldaPlayer)mpc.getPlayer()).setHP(i);
    }
  }

  private Map<Integer, Integer> getSScoreList(){
    Map<Integer,Integer> ret = new HashMap<>();
    for(MainPlayerControl mpc:myMainPlayerController){
      int id = mpc.getID();
      int score = (int)((ZeldaPlayer)mpc.getPlayer()).getScore();
      ret.put(id,score);
    }
    return ret;
  }

  public void setColor(Color color) {
    myPauseControl.setColor(color);
  }
}
