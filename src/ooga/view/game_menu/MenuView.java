package ooga.view.game_menu;

import javafx.scene.Scene;

public interface MenuView {
    Scene getMenuView();

    void switchMode(boolean dark);

    void setLanguage(String language);
}
