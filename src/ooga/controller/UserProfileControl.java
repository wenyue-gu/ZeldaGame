package ooga.controller;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.view.game_menu.LogInView;
import ooga.view.game_menu.UserProfileView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class UserProfileControl {

    private Stage myStage;
    private UserProfileView myView;
    private WindowControl myControl;

    private Button LogInButton;
    private Button SignUpButton;

    private Color mycolor = Color.WHITE;
    private boolean dark = false;
    private String language = "English";
    private boolean isColor = false;
    private String userName;

    public UserProfileControl(WindowControl windowControl){
        myStage = new Stage();
        myControl = windowControl;
    }



    public void setUserNameAndShow(String s){
        userName = s;
        myView = new UserProfileView(s);
        myView.switchMode(dark);
        if(isColor) myView.changColor(mycolor);
        myView.setLanguage(language);
        myStage.setScene(myView.getMenuView());
        myStage.show();
    }

    public void switchMode(boolean dark) {
        this.dark = dark;
        isColor = false;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void changColor(Color color) {
        mycolor = color;
        isColor = true;
    }

    public void writeScore(int score){
        Properties prop = new Properties();
        try {
            InputStream in = new FileInputStream("resources/user_"+userName+".properties");//getClass().getResourceAsStream("/resources/xyzz.properties");
            prop.load(in);
        }
        catch(Exception e){
            System.out.println("user profile load error in profile control");
        }

        if(score>myView.getHighest()){
            myView.setHighest(score);
            prop.setProperty("High", score+"");
        }
        myView.setLast(score);
        prop.setProperty("Last", score+"");

        try {
            FileOutputStream fos = new FileOutputStream("resources/user_"+userName+".properties");
            prop.store(fos, "");
            fos.flush();
            fos.close();
        }
        catch(Exception ex){
            System.out.println("save score error in profile control");
        }

    }
}
