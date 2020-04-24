package ooga.controller;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.view.game_menu.LogInView;
import ooga.view.game_menu.SettingView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingControl {

    private Stage myStage;
    private SettingView myView;
    private WindowControl myControl;

    private Button ConfirmButton;

    public SettingControl(WindowControl windowControl){
        myStage = new Stage();
        myView = new SettingView();
        myControl = windowControl;
        myStage.setScene(myView.getMenuView());
        setUpButtons();
    }

    private void setUpButtons() {
        ConfirmButton = myView.getConfirm();
        ConfirmButton.setOnAction(e->confirm());

    }

    private void confirm() {
        String UserInput = myView.getHPInput();
        try {
            int i = Integer.parseInt(UserInput);
            if(i>0){
                myControl.setLife(i);
                myView.setHP(i);
                myStage.close();
            }
            else{
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            myView.NotInt();
        }
    }


    public void switchMode(boolean dark) {
        myView.switchMode(dark);
    }

    public void setLanguage(String language) {
        myView.setLanguage(language);
    }

    public void showSetting() {
        myView.clearInput();
        myStage.show();
    }

    public void changColor(Color color) {
        myView.changColor(color);
    }
}
