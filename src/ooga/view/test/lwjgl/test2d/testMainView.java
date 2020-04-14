package ooga.view.test.lwjgl.test2d;


import java.io.IOException;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Shader;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.game_state.state2d.GameState2DView;
import ooga.view.game_view.map.map2d.Map2DView;
import org.lwjgl.glfw.GLFW;

public class testMainView implements Runnable {

  public Thread game;
  public GameState2DView view;

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
   view = new GameState2DView(1);
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

  public static void main(String[] args) {
    new testMainView().start();
  }
}