package ooga.view.game_view.game_state.interfaces;

import java.io.IOException;

abstract public class GameStateView {

  public GameStateView(){}

  abstract public void createWindow() throws IOException;

  abstract public void updatePlayer(int index, String direction, String state);

  abstract public void updateNPC(int index, String direction, String state);

  abstract public void updateMap();

  abstract public void updateWindow();

  public void renderAll(){
    renderMap();
    renderPlayers();
    renderNPCs();
    renderWindow();
  }

  abstract public void renderPlayers();

  abstract public void renderNPCs();

  abstract public void renderMap();

  abstract public void renderWindow();

  abstract public void closeWindow();

  abstract public boolean shouldWindowClose();

  abstract public boolean isKeyDown(int key);

}
