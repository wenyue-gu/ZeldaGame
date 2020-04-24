package ooga.controller.gamecontrol;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.data.DataStorerAPI;
import ooga.view.game_menu.FinishScreen;
import ooga.view.game_menu.PauseMenu;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.util.List;
import java.util.Map;

public class FinishControl {

    private Stage myStage;
    private FinishScreen myFinishScreen;
    private Button myResumeButton;
    private Button myBackButton;
    private Button mySaveButton;

    private List<Button> buttonList;

    private WindowControl myWindowControl;

    private DataStorerAPI myStorer;
    private GameController myGameController;

    public FinishControl(GameController gameController){
        myGameController = gameController;
        reset();
        myStage = new Stage();
        myFinishScreen = new FinishScreen();
        myStage.setScene(myFinishScreen.getMenuView());
        setUpButton();
    }

    private void reset() {
        myGameController.reset();
    }

    public void showMenu(boolean win, int id, int score){
        myFinishScreen.setWin(win, id, score);
        save();
        myStage.show();
    }

    private void setUpButton(){
        myBackButton = myFinishScreen.getBackToMenuButton();
        myBackButton.setOnAction(e->backToMenu());

        //buttonList = List.of(myResumeButton, myBackButton, mySaveButton);
    }

    private void resumeGame(){
        myStage.close();
        //myTimer.start();
    }

    private void backToMenu(){
        myGameController.save();
        myStage.close();
        myWindowControl.showWindowMenu();
    }

    private void save(){
        myGameController.save();
    }

    public void setWindowControl(WindowControl windowControl) {
        myWindowControl = windowControl;
    }

    public void setMode(boolean dark) {
        myFinishScreen.switchMode(dark);
    }

    public void setLanguage(String language){
        myFinishScreen.setLanguage(language);
    }

    public void setColor(Color color) {
        myFinishScreen.changColor(color);
    }
}
