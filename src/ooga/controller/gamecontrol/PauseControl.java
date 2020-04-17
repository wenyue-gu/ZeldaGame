package ooga.controller.gamecontrol;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.view.game_menu.PauseMenu;
import ooga.view.game_view.game_state.state2d.GameState2DView;

public class PauseControl {

    private Stage myStage;
    private PauseMenu myPauseMenu;
    private Button myResumeButton;
    private Button myBackButton;
    private Button mySaveButton;
    private WindowControl myWindowControl;
    private GameState2DView myView;
    private AnimationTimer myTimer;

    public PauseControl(){
        myStage = new Stage();
        myPauseMenu = new PauseMenu();
        myStage.setScene(myPauseMenu.getMenuView());
        setUpButton();
    }

    public void showMenu(){
        myTimer.stop();
        myStage.show();
    }

    private void setUpButton(){
        myResumeButton = myPauseMenu.getResumeButton();
        myResumeButton.setOnAction(e->resumeGame());
        myBackButton = myPauseMenu.getBackToMenuButton();
        myBackButton.setOnAction(e->backToMenu());
        mySaveButton = myPauseMenu.getSaveGameButton();
        mySaveButton.setOnAction(e->save());
    }

    private void resumeGame(){
        myStage.close();
        myTimer.start();
    }

    private void backToMenu(){
        myStage.close();
        myView.closeWindow();
        myWindowControl.showWindowMenu();
    }

    private void save(){

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
}
