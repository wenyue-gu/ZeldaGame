package ooga.view.test.lwjgl.test2d;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ooga.view.engine.utils.cyberpunk2d.GenerateAgentsData;
import ooga.view.game_view.agent.agent2d.Agent2DDataHolder;
import ooga.view.game_view.game_state.state2d.GameState2DView;
import org.lwjgl.glfw.GLFW;

public class testMainView implements Runnable {

  public Thread game;
  public GameState2DView view;

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    Map<Integer, Agent2DDataHolder> dataHolderMap = new HashMap<>();
    //dataHolderMap.put(0, GenerateAgentsData.createSoldier(-1f, 0f));
    dataHolderMap.put(0, GenerateAgentsData.createSoldier(-1f, -1f));
    dataHolderMap.put(1, GenerateAgentsData.createSoldier(-1f, -0.5f));
    dataHolderMap.put(2, GenerateAgentsData.createEngineer(-1f, 0f));
    dataHolderMap.put(3, GenerateAgentsData.createMeleeBot(0f, -0.5f));
    view = new GameState2DView(dataHolderMap);
   view.createWindow();
  }

  public void run() {
    try {
      init();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (!view.shouldWindowClose()) {
      try {
        update();
      } catch (IOException e) {
        System.out.println("Exception in game scene update");
      }
      try {
        render();
      } catch (IOException e) {
        System.out.println("Exception in game scene rendering");
      }
    }
    close();
  }

  private void update() throws IOException {
    view.updateWindow();
    view.updateMap(); //empty method
    view.updateBullets();
    if (view.isKeyDown(GLFW.GLFW_KEY_A)){
      view.updateAgent(2,"E","ATTACK", false);}
    if (view.isKeyDown(GLFW.GLFW_KEY_S)){
      view.updateAgent(2,"E","SUMMON_BIGBOY", true);}
    if (view.isKeyDown(GLFW.GLFW_KEY_W)){
      view.updateAgent(2,"E","WALK", false);}
    if (view.isKeyDown(GLFW.GLFW_KEY_Q)){
      view.updateAgent(2,"E","DEATH", true);}
    }

  private void render() throws IOException {
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