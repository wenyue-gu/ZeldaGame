package interfaces.view.game_view.animation;

import javafx.animation.Animation;

public interface StateAnimation {

  void setSubject(); // unimplemented data type for argument

  Animation getStateAnimation(); // returns whatever that can move
}
