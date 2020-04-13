package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SelectMenuView implements SelectMenu {
    private PrettyButtons myGameButton1;
    private PrettyButtons myGameButton2;
    private PrettyButtons myGameButton3;
    private VBox vBox;
    private Scene myScene;
    private boolean dark;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public SelectMenuView(){
        setUpButton();
        setUpVBox();
        myScene = new Scene(vBox, 500, 500);
    }

    @Override
    public Button getGame1() {
        return myGameButton1;
    }

    @Override
    public Button getGame2() {
        return myGameButton2;
    }

    @Override
    public Button getGame3() {
        return myGameButton3;
    }

    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
    }

    private void setColor(){
        if(dark) vBox.setBackground(darkMode);
        else vBox.setBackground(lightMode);
        myGameButton1.switchMode(dark);
        myGameButton2.switchMode(dark);
        myGameButton3.switchMode(dark);
    }

    private void setUpButton(){
        myGameButton1 = new PrettyButtons("Type1");
        myGameButton2 = new PrettyButtons("Type2");
        myGameButton3 = new PrettyButtons("Type3") ;
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.getChildren().addAll(myGameButton1, myGameButton2, myGameButton3);
    }
}
