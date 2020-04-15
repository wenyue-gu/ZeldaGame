package ooga.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ooga.controller.gamecontrol.GameController;
import ooga.data.DataLoaderAPI;
import ooga.model.Model;
import ooga.model.interfaces.ModelInterface;
import ooga.view.game_menu.GameMenuView;
import ooga.view.game_menu.GameMenu;
import ooga.view.game_menu.SelectMenuView;
import ooga.view.game_view.game_state.state2d.GameState2DView;


public class WindowControl {

  private Button myStartButton;
  private Button myExitButton;
  private Button myLoadButton;
  private Button myChangeBackgroundButton;
  private Button myGameButton1;
  private Button myGameButton2;
  private Button myGameButton3;

  private ComboBox myLanguagePicker;

  private GameController myGameController;
  private Stage myStage;
  private Stage secondStage;
  private SelectMenuView mySelectView;
  private GameMenu myMenuView;
  private ModelInterface myModel;
  private DataLoaderAPI myDataLoader;
  private boolean dark = false;

  private GameState2DView myGameView;

  public WindowControl(Stage currentStage){
    myStage = currentStage;

    myMenuView = new GameMenuView();
    mySelectView = new SelectMenuView();

    setMenuScene();
    initializeButtons();
  }

  public void setModel(Model model){
    myModel = model;
  }

  public void setDataLoader(DataLoaderAPI Loader){
    myDataLoader = Loader;
  }

  private void setMenuScene(){
    myStage.setScene(myMenuView.getMenuView());
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
    myLoadButton.setOnAction(e->loadlist());

    myGameButton1 = mySelectView.getGame1();
    myGameButton1.setOnAction(e->startGame1());
    myGameButton2 = mySelectView.getGame2();
    myGameButton2.setOnAction(e->startGame2());
    myGameButton3 = mySelectView.getGame3();
    myGameButton3.setOnAction(e->startGame3());
    myLanguagePicker = myMenuView.getLanguagePicker();
    myLanguagePicker.setOnAction(e -> setLanguage(myLanguagePicker.getValue().toString()));

  }

  private void selectGameMenu(){
    secondStage = new Stage();
    secondStage.setScene(mySelectView.getMenuView());
    secondStage.show();
  }

  private void setLanguage(String language){
    myMenuView.setLanguage(language);
    mySelectView.setLanguage(language);
  }

  private void startGame1(){
    System.out.println("111");
    //TODO: set up data and stuff for game one, then call startGame?
    myGameController = new GameController(myModel, myDataLoader);
    myGameController.setMode(dark);
    try {
      myGameView = new GameState2DView(myModel.getPlayers().size());
      System.out.println(myModel.getPlayers().size());
      myGameController.setView(myGameView);
      myGameView.createWindow();
      secondStage.close();
      myStage.close();
    }
    catch(Exception e){
      System.out.println("GameState2DViewError");
    }
  }

  private void startGame2(){
    secondStage.close();
  }

  private void startGame3(){
    secondStage.close();
  }


  private void switchMode(){
    dark = !dark;
    myMenuView.switchMode(dark);
    mySelectView.switchMode(dark);
  }

  private void loadlist(){
//    Stage newstage = new Stage();
//    ObservableList<String> savedGame = FXCollections.observableArrayList();
//    savedGame.add("Game 1");
//    ListView listView = new ListView();
//    listView.setItems( savedGame);
//
//    VBox box = new VBox();
//    box.getChildren().add(listView);
//    newstage.setScene(new Scene(box, 200, 250));
//    newstage.show();

  }
}
