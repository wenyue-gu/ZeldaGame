package ooga.view.game_menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class LogInView {

    private Scene myScene;
    private VBox vBox;
    private TextField nameInput;
    private TextField passwordInput;
    private Label userName;
    private Label userPassWord;
    private PrettyButtons LogInButton;
    private PrettyButtons SignUpButton;
    private Label Message;

    private String myLanguage = "English";

    public LogInView(){
        setUpLabel();
        setUpField();
        setUpButton();
        setUpVBox();
        myScene = new Scene(vBox, 600,600);
    }

    private void setUpButton() {
        LogInButton = new PrettyButtons("LogIn", myLanguage);
        SignUpButton = new PrettyButtons("Signup", myLanguage);
    }

    public String getNameInput(){
        return nameInput.getText();
    }

    public String getPasswordInput(){
        return passwordInput.getText();
    }

    public Button getLogInButton(){
        return LogInButton;
    }

    public Button getSignUpButton(){
        return SignUpButton;
    }

    public void setLanguage(String language){
        myLanguage = language;
        LogInButton.changeLanguage(myLanguage);
        SignUpButton.changeLanguage(myLanguage);
    }

    public void UserDNE(){
        Message.setText("User does not exist.");
    }

    public void logInFail(){
        Message.setText("Wrong password or user name.");
    }

    public void signUpFail(){
        Message.setText("User already exists.");
    }

    private void setUpVBox() {
        vBox = new VBox(10);
        vBox.setMaxWidth(300);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(userName, nameInput, userPassWord, passwordInput, LogInButton, SignUpButton, Message);
    }

    private void setUpField() {
        nameInput = new TextField();
        nameInput.setMaxWidth(300);
        passwordInput = new TextField();
        passwordInput.setMaxWidth(300);
    }

    private void setUpLabel() {
        userName = new Label("User Name: ");
        userName.setFont(Font.font("Ariel", 18));
        userPassWord = new Label("User Password: ");
        userPassWord.setFont(Font.font("Ariel", 18));
        Message = new Label("");
        Message.setFont(Font.font("Ariel", 18));
    }


    public Scene getView() {
        return myScene;
    }
}
