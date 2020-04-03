package interfaces.view.game_view.panels.dialogue_panel;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public interface DialogPanel {
  Pane getDialogPanel();

  void update(String text, DialogPref pref);

}
