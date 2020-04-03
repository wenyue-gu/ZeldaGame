package interfaces.view.game_menu;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public interface GameMenuView { // will be extending Scene

  Node getMenuView();

  Button getNewGameButton();

  Button getExitGameButton();

}
