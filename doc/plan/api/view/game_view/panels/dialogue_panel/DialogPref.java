package ooga.view.game_view.panels.dialogue_panel;

import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public interface DialogPref {

  void setSound();

  void setSpeaker();

  AudioClip getSound();

  Color getTextColor();

  Font getTextFont();

}
