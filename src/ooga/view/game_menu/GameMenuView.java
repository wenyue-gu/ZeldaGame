package ooga.view.game_menu;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public interface GameMenuView extends MenuView{ // will be extending Scene

  Button getNewGameButton();

  Button getExitGameButton();

  Button getBackgroundButton();

  Button getLoadButton();


  ComboBox getLanguagePicker();
}
