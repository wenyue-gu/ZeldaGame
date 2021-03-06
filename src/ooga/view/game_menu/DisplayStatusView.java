package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ooga.view.game_menu.pretty.PrettyButtons;

import java.util.*;

public class DisplayStatusView implements MenuView {

    private List<PrettyButtons> myButtonList = new ArrayList<>();

    private VBox vBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage;

    private Label scorelist;
    private Label life;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public DisplayStatusView(){
        myLanguage = "English";
        scorelist = new Label();
        scorelist.setFont(Font.font("Ariel", 18));
        life = new Label();
        life.setFont(Font.font("Ariel", 18));
        setUpVBox();
        myScene = new Scene(vBox, 800, 800);
    }

    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for(PrettyButtons button:myButtonList) button.changeLanguage(myLanguage);
    }

    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
    }

    public void setColor(){
        vBox.setBackground(dark?darkMode: lightMode);
        scorelist.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        life.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scorelist, life);
    }

    public void updateScore(Map<Integer, Integer> list){
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" score: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        scorelist.setText(s);
    }

    public void updateLife(Map<Integer, Integer> list) {
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" score: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        life.setText(s);

    }
}
