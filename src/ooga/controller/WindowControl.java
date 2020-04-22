package ooga.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.gamecontrol.GameController;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.DataStorerAPI;
import ooga.game.GameType;
import ooga.game.GameZelda2D;
import ooga.model.Model;
import ooga.model.interfaces.ModelInterface;
import ooga.view.game_menu.GameMenuView;
import ooga.view.game_menu.GameMenu;
import ooga.view.game_menu.SelectMenuView;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class WindowControl {

  public static final int CURRENT_PLAYER_ID = 1;

  private Button myStartButton;
  private Button myExitButton;
  private Button myLoadButton;
  private Button myChangeBackgroundButton;
  private Button myGameButton1;
  private Button myGameButton2;
  private Button myGameButton3;
  private Button mySettingButton;
  private Button myUserButton;

  private ColorPicker myColorPicker;

  private String myUserName = "";
  private boolean isLogIn = false;

  private boolean isColored = false;
  private Color color = Color.WHITE;

  private ComboBox myLanguagePicker;

  private LogInControl myLogIn;
  private SettingControl mySettingControl;
  private GameController myGameController;

  private Stage myStage;
  private Stage secondStage;
  private SelectMenuView mySelectView;
  private GameMenu myMenuView;
  private DataLoaderAPI myDataLoader;
  private DataStorerAPI myDataStorer;

  private int myPlayerHP=0;

  private boolean dark = false;
  private String language = "English";

  private GameState2DView myGameView;

  public WindowControl(Stage currentStage) throws DataLoadingException {
    myStage = currentStage;
    myMenuView = new GameMenuView();
    mySelectView = new SelectMenuView();
    myLogIn = new LogInControl(this);
    mySettingControl = new SettingControl(this);

    setMenuScene();
    initializeButtons();
  }

  public void setColor(Color color){
    isColored = true;
    this.color = color;
    myMenuView.changColor(color);
    mySelectView.changColor(color);
    myLogIn.changColor(color);
    mySettingControl.changColor(color);
  }

  public WindowControl(Stage currentStage, DataStorerAPI datastorer) throws DataLoadingException {
    this(currentStage);
    setDataLoader(datastorer.getDataLoader());
    myDataStorer = datastorer;
  }

  public void setDataLoader(DataLoaderAPI Loader){
    myDataLoader = Loader;
  }

  private void setMenuScene(){
    myStage.setScene(myMenuView.getMenuView());
  }

  public void showWindowMenu(){myStage.show();}

  public void setUser(String userName){
    myUserName = userName;
  }

  private void initializeButtons(){
    myStartButton = myMenuView.getNewGameButton();
    //myStartButton.setOnAction(e->startGame(myStage));
    myStartButton.setOnAction(e->selectGameMenu());
    myExitButton = myMenuView.getExitGameButton();
    myExitButton.setOnAction(e->myStage.close());
    myChangeBackgroundButton = myMenuView.getBackgroundButton();
    myChangeBackgroundButton.setOnAction(e->switchMode());
    myLoadButton = myMenuView.getLoadButton();
    myLoadButton.setOnAction(e-> {
      try {
        loadlist();
      } catch (DataLoadingException ex) {
        System.out.println("WINDOW CONTROL LOAD LAST PLAYED");
      }
    });
    myUserButton = myMenuView.getUserButton();
    myUserButton.setOnAction(e->showProfile());

    myGameButton1 = mySelectView.getGame1();
    myGameButton1.setOnAction(e-> {
      try {
        startGame1();
      } catch (DataLoadingException ex) {
        System.out.println("WINDOW CONTROL STARTGAME1");
      }
    });
    myGameButton2 = mySelectView.getGame2();
    myGameButton2.setOnAction(e-> {
      try {
        startGame2();
      } catch (Exception ex) {
        System.out.println("WINDOW CONTROL STARTGAME2");
      }
    });
    myGameButton3 = mySelectView.getGame3();
    myGameButton3.setOnAction(e->startGame3());
    mySettingButton = mySelectView.getSetting();
    mySettingButton.setOnAction(e->changeSettings());

    myLanguagePicker = myMenuView.getLanguagePicker();
    myLanguagePicker.setOnAction(e -> setLanguage(myLanguagePicker.getValue().toString()));

    myColorPicker = myMenuView.getMyColorPicker();
    System.out.println(myColorPicker);
    myColorPicker.setOnAction(e->setColor(myColorPicker.getValue()));

  }

  private void changeSettings() {
    mySettingControl.showSetting();
  }

  private void showProfile() {
    if(!isLogIn){
      myLogIn.showLogIn();
    }
  }

  private void selectGameMenu(){
    myDataStorer.resetPlayerInfo();
    secondStage = new Stage();
    secondStage.setScene(mySelectView.getMenuView());
    secondStage.show();
  }

  private void setLanguage(String language){
    this.language = language;
    myMenuView.setLanguage(language);
    mySelectView.setLanguage(language);
    myLogIn.setLanguage(language);
    mySettingControl.setLanguage(language);
  }

  private void startGame1() throws DataLoadingException {
//    System.out.println("111");
//    //TODO: set up data and stuff for game one, then call startGame?
//    myGameController = new GameController(myDataLoader);
//    myGameController.setMode(dark);
//    try {
//
//      myGameView = new GameState2DView(myGameController.getPlayerSize());
//      myGameController.setView(myGameView);
//      myGameView.createWindow();
//      AnimationTimer timer = new AnimationTimer() {
//      @Override
//      public void handle(long now) {
//        myGameController.update();
//      }
//    };
//    timer.start();
//
//    secondStage.close();
//    //myStage.close();
//    }
//    catch(Exception e){
//      System.out.println("GameState2DViewError");
//    }
  }

  private void startGame2() throws DataLoadingException, IOException {
    myDataLoader.setGameAndPlayer(GameType.ZELDA.getIndex(), CURRENT_PLAYER_ID);
    GameZelda2D zelda2D = new GameZelda2D();
    setUpController();
    zelda2D.start();
    while (zelda2D.getView() == null){
      System.out.println(zelda2D.getView());
    }
    System.out.println(zelda2D.getView());
    myGameController.setView(zelda2D.getView());
    myGameController.startTimer();
    //myStage.close();
    secondStage.close();
  }

  private void setUpController() throws DataLoadingException {
    myGameController = new GameController(myDataStorer);
    if(!isColored)myGameController.setMode(dark);
    else myGameController.setColor(color);
    myGameController.setLanguage(language);
    myGameController.setWindowControl(this);
    if(myPlayerHP>0) myGameController.setInitLife(myPlayerHP);
  }


  private void startGame3(){
    secondStage.close();
  }

  public void showWindow(String title, int height, int width, boolean isResizable) {
    myStage.setTitle(title);
    myStage.setWidth(width);
    myStage.setHeight(height);
    myStage.setResizable(isResizable);
    myStage.show();
  }


  private void switchMode(){
    dark = !dark;
    isColored = false;
    myMenuView.switchMode(dark);
    mySelectView.switchMode(dark);
    myLogIn.switchMode(dark);
    mySettingControl.switchMode(dark);
  }

  private void loadlist() throws DataLoadingException {
    setUpController();
    myGameController.pause();

  }

  public void setLife(int i) {
    myPlayerHP = i;
  }
}
