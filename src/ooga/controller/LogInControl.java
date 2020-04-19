package ooga.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.view.game_menu.LogInView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class LogInControl {

    private Stage myStage;
    private LogInView myView;
    private WindowControl myControl;

    private Button LogInButton;
    private Button SignUpButton;

    public LogInControl(WindowControl windowControl){
        myStage = new Stage();
        myView = new LogInView();
        myControl = windowControl;
        myStage.setScene(myView.getMenuView());
        setUpButtons();
    }

    private void setUpButtons() {
        LogInButton = myView.getLogInButton();
        LogInButton.setOnAction(e->checkLogIn());

        SignUpButton = myView.getSignUpButton();
        SignUpButton.setOnAction(e->signUp());
    }

    private void signUp() {
        var resources = ResourceBundle.getBundle("user");
        String UserInput = myView.getNameInput();
        if(resources.containsKey(UserInput)){
            myView.signUpFail();
            return;
        }

        try {
            Properties prop = new Properties();
            InputStream in = new FileInputStream("resources/user.properties");//getClass().getResourceAsStream("/resources/xyzz.properties");
            prop.load(in);
            prop.setProperty(UserInput, myView.getPasswordInput());
            FileOutputStream fos = new FileOutputStream("resources/user.properties");
            prop.store(fos, "test");
            fos.flush();
            fos.close();
            System.out.println(">");
        }
        catch(Exception e){
            System.out.println("Something went wrong...");
        }

        //TODO: create user profile properties

    }

    private void checkLogIn() {
        var resources = ResourceBundle.getBundle("user");
        String UserInput = myView.getNameInput();
        if(!resources.containsKey(UserInput)){
            myView.UserDNE();
            return;
        }
        String PassWord = myView.getPasswordInput();
        if(!resources.getString(UserInput).equals(PassWord)){
            myView.logInFail();
            return;
        }
        myControl.setUser(UserInput);
        //TODO: OPEN USER PROFILE AND NOTIFY WINDOW CONTROL OF USER
        //TODO: myControl.findUserProfile();
        myStage.close();
    }

    public void showLogIn(){
        myStage.show();
    }

    public void switchMode(boolean dark) {
        myView.switchMode(dark);
    }

    public void setLanguage(String language) {
        myView.setLanguage(language);
    }
}
