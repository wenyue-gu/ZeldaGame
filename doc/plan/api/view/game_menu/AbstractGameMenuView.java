package ooga.view.game_menu;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class AbstractGameMenuView implements GameMenuView {
    @Override
    public Node getMenuView() {
        return null;
    }

    @Override
    public Button getNewGameButton() {
        return null;
    }

    @Override
    public Button getExitGameButton() {
        return null;
    }
}
