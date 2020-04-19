package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.ResourceBundle;

public class GameMenuView implements GameMenu {
    private PrettyButtons myNewButton;
    private PrettyButtons myExitButton;
    private PrettyButtons myMode;
    private PrettyButtons myLoad;
    private PrettyButtons myUser;
    private List<PrettyButtons> myButtonList;
    private VBox vBox;
    private HBox hBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage;
    private PrettyBox myLanguagePicker;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public GameMenuView(){
        myLanguage = "English";
        setUpButton();
        setUpHBox();
        setUpVBox();
        setColor();
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
        return myMode;
    }

    @Override
    public Button getLoadButton() {
        return myLoad;
    }

    @Override
    public Button getUserButton() {
        return myUser;
    }

    @Override
    public ComboBox getLanguagePicker() {
        return myLanguagePicker;
    }

    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
    }

    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for (PrettyButtons button : myButtonList) button.changeLanguage(myLanguage);
    }

    private void setColor(){
        vBox.setBackground(dark?darkMode:lightMode);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);
        myLanguagePicker.switchMode(dark);
    }

    private void setUpButton(){
        myNewButton = new PrettyButtons("New", myLanguage);
        myExitButton = new PrettyButtons("Exit", myLanguage);
        myMode = new PrettyButtons("Background", myLanguage) ;
        myLoad = new PrettyButtons("Load", myLanguage);
        myUser = new PrettyButtons("User", myLanguage);
        myButtonList = List.of(myNewButton, myExitButton, myMode, myLoad, myUser);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.getChildren().add(hBox);
        vBox.getChildren().addAll(myButtonList);
    }

    private void setUpHBox(){
        setUpLanguageMenu();
        hBox = new HBox(10);
        hBox.getChildren().addAll(myLanguagePicker);
        //hBox.setAlignment(Pos.BASELINE_RIGHT);
        hBox.setTranslateX(850);
        hBox.setTranslateY(20);
    }

    private void setUpLanguageMenu(){
        var resource = ResourceBundle.getBundle("menu");
        myLanguagePicker = new PrettyBox("Language");
        myLanguagePicker.setSize(200, 30);
    }


}
