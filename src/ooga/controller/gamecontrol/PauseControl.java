package ooga.controller.gamecontrol;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.data.DataStorerAPI;
import ooga.view.game_menu.PauseMenu;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.util.List;
import java.util.Map;

public class PauseControl {

    private Stage myStage;
    private PauseMenu myPauseMenu;
    private Button myResumeButton;
    private Button myBackButton;
    private Button mySaveButton;

    private List<Button> buttonList;

    private WindowControl myWindowControl;
    private GameState2DView myView;
    private AnimationTimer myTimer;

    private DataStorerAPI myStorer;
    private GameController myGameController;

    public PauseControl(GameController gameController){
        myStage = new Stage();
        myPauseMenu = new PauseMenu();
        myGameController = gameController;
        myStage.setScene(myPauseMenu.getMenuView());
        setUpButton();
    }

    public void showMenu(){
        //myTimer.stop();
        myStage.show();
    }

    private void setUpButton(){
        myResumeButton = myPauseMenu.getResumeButton();
        myResumeButton.setOnAction(e->resumeGame());
        myBackButton = myPauseMenu.getBackToMenuButton();
        myBackButton.setOnAction(e->backToMenu());
        mySaveButton = myPauseMenu.getSaveGameButton();
        mySaveButton.setOnAction(e->save());

        //buttonList = List.of(myResumeButton, myBackButton, mySaveButton);
    }

    private void resumeGame(){
        myStage.close();
        //myTimer.start();
    }

    private void backToMenu(){
        myGameController.save();
        myStage.close();
        myView.closeWindow();
        myWindowControl.showWindowMenu();
    }

    private void save(){
        myGameController.save();
    }

    public void setWindowControl(WindowControl windowControl) {
        myWindowControl = windowControl;
    }

    public void setView(GameState2DView view) {
        myView = view;
    }

    public void setTimer(AnimationTimer myTimer) {
        this.myTimer = myTimer;
    }

    public void setMode(boolean dark) {
        myPauseMenu.switchMode(dark);
    }

    public void setLanguage(String language){
        myPauseMenu.setLanguage(language);
    }

    public void updateScore(Map<Integer, Integer> sScoreList) {
        myPauseMenu.updateScore(sScoreList);
    }

    public void setColor(Color color) {
        myPauseMenu.changColor(color);
    }
}
