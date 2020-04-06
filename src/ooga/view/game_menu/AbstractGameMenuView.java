package ooga.view.game_menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AbstractGameMenuView implements GameMenuView {
    private Button myNewButton;
    private Button myExitButton;
    private VBox vBox;
    private Scene myScene;

    public AbstractGameMenuView(){
        setUpButton();
        setUpVBox();
        myScene = new Scene(vBox);
    }

    @Override
    public Scene getMenuView() {

        return myScene;
    }

    @Override
    public Button getNewGameButton() {
        return myNewButton;
    }

    @Override
    public Button getExitGameButton() {
        return myExitButton;
    }

    private void setUpButton(){
        myNewButton = new Button("New Game");
        myExitButton = new Button("Exit");
    }

    private void setUpVBox(){
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(myNewButton, myExitButton);
    }
}
