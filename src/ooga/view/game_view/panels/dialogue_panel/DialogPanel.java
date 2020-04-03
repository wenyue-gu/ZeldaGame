package ooga.view.game_view.panels.dialogue_panel;

import javafx.scene.layout.Pane;

public interface DialogPanel {
  Pane getDialogPanel();

  void update(String text, DialogPref pref);

}
