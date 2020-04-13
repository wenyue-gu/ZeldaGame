package ooga.view.game_menu;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public interface GameMenuView extends MenuView{ // will be extending Scene

  Button getNewGameButton();

  Button getExitGameButton();

  Button getBackgroundButton();

  Button getLoadButton();


}
