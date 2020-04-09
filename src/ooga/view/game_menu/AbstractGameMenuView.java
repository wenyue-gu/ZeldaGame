package ooga.view.game_menu;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AbstractGameMenuView implements GameMenuView {
    private PrettyButtons myNewButton;
    private PrettyButtons myExitButton;
    private PrettyButtons toggle;
    private VBox vBox;
    private Scene myScene;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

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

    @Override
    public Button getBackgroundButton(){
        return toggle;
    }

    @Override
    public void switchMode(boolean dark){
        if(dark) vBox.setBackground(darkMode);
        else vBox.setBackground(lightMode);
        myNewButton.switchMode(dark);
        myExitButton.switchMode(dark);
        toggle.switchMode(dark);
    }

    private void setUpButton(){
        myNewButton = new PrettyButtons("New Game");
        myExitButton = new PrettyButtons("Exit");
        toggle = new PrettyButtons("Change Background") ;
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.getChildren().addAll(myNewButton, myExitButton, toggle);
    }
}
