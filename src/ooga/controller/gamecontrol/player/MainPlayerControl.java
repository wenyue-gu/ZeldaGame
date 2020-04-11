//package ooga.controller.gamecontrol.player;
//
//import ooga.controller.gamecontrol.PlayerControlInterface;
//import javafx.scene.input.KeyCode;
//import ooga.model.interfaces.movables.Movable1D;
//
//public class MainPlayerControl implements PlayerControlInterface {
//  private PlayerControlInterface myPlayerControl;
//  private PlayerControlFactory myPlayerControlFactory;
//
//  public MainPlayerControl(){
//    myPlayerControlFactory = new PlayerControlFactory();
//  }
//
//  public void setControl(String gameType){
//    myPlayerControl= myPlayerControlFactory.selectControl(gameType);
//  }
//
//  @Override
//  public void keyInput(KeyCode key){
//    myPlayerControl.keyInput(key);
//  }
//
//  @Override
//  public void setMyPlayer(Movable1D player) {
//    myPlayerControl.setMyPlayer(player);
//  }
//
//  @Override
//  public void update(){
//
//  }
//
//}