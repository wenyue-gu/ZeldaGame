package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PauseMenu implements MenuView {

    private PrettyButtons ResumeButton;
    private PrettyButtons BackToMenuButton;
    private PrettyButtons SaveGameButton;

    private List<PrettyButtons> myButtonList = new ArrayList<>();

    private VBox vBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public PauseMenu(){
        myLanguage = "English";
        setUpButton();
        setUpVBox();
        myScene = new Scene(vBox, 800, 800);
    }

    public Button getResumeButton(){return ResumeButton;}

    public Button getBackToMenuButton(){return BackToMenuButton;}

    public Button getSaveGameButton(){return SaveGameButton;}

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
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
    }

    public void setColor(){
        if(dark) vBox.setBackground(darkMode);
        else vBox.setBackground(lightMode);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);
    }

    private void setUpButton(){
        ResumeButton = new PrettyButtons("resume", myLanguage);
        BackToMenuButton = new PrettyButtons("menu", myLanguage);
        SaveGameButton = new PrettyButtons("save", myLanguage) ;

        myButtonList = List.of(ResumeButton, BackToMenuButton, SaveGameButton);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(ResumeButton, BackToMenuButton, SaveGameButton);
    }
}
