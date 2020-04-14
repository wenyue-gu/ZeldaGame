package ooga.view.game_menu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

import java.util.ResourceBundle;

public class PrettyButtons extends Button
{
    private static final int BUTTON_HEIGHT = 45, BUTTON_WIDTH = 300;
    private boolean dark = false;
    private Background darkMode = new Background(new BackgroundFill(new Color(0.4,0.4,0.4,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(new Color(0.85,0.85,0.85,1), CornerRadii.EMPTY, Insets.EMPTY));
    private DropShadow shadow = new DropShadow();
    private String myWord;
    private String myLanguage;

    public PrettyButtons(String words, String Language) {
        myWord = words;
        myLanguage = Language;
        changeLanguage(Language);
        setPrefHeight(BUTTON_HEIGHT);//45
        setPrefWidth(BUTTON_WIDTH);//190
        setFont(Font.font("Ariel", 20));
        switchMode(dark);
        shadow.setSpread(0.7);
        mouseUpdateListener();
    }

    public void changeLanguage(String language) {
        myLanguage = language;
        var resources = ResourceBundle.getBundle(AbstractGameMenuView.class.getPackageName() + ".menuResource." + language);
        setText(resources.getString(myWord));
    }


    public void switchMode(boolean dark){

        if(dark) setBackground(darkMode);
        else setBackground(lightMode);
        shadow.setColor(dark? Color.CORNFLOWERBLUE:Color.LIGHTBLUE);
        this.dark = dark;
    }

    private void mouseUpdateListener() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(shadow);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }

}
