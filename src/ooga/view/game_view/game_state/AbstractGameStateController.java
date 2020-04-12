package ooga.view.game_view.game_state;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class AbstractGameStateController implements GameStateController {

  @Override
  public Scene getGameStateView() {
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    return new Scene(vBox);
  }

  @Override
  public void update() {

  }
}
