package ooga.game;

import java.io.IOException;
import ooga.view.game_view.game_state.state2d.GameState2DView;
import org.lwjgl.glfw.GLFW;

public class GameZelda2D implements Runnable {

  private Thread game;
  private GameState2DView view;

  public GameZelda2D(GameState2DView view){
    this.view = view;
  }

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    //view = new GameState2DView(1);
    view.createWindow();
  }

  public void run() {
    try {
      init();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (!view.shouldWindowClose()) {
      update();
      render();
    }
    close();
  }

  private void update() {
    view.updateWindow();
    view.updateMap(); //empty method
    view.renderNPCs(); // empty method
    if (view.isKeyDown(GLFW.GLFW_KEY_S)){
      view.updatePlayer(0,"E","SPRINT");}
  }

  private void render() {
    view.renderAll();
    // can also render separately, renderWindow() is mandatory
  }

  private void close() {
    view.closeWindow();
  }

  public GameState2DView getView() {
    return view;
  }
}
