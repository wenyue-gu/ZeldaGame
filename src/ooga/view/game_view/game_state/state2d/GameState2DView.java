package ooga.view.game_view.game_state.state2d;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import ooga.view.engine.graphics.Shader;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.Timer;
import ooga.view.engine.utils.cyberpunk2d.GenerateAgentsData;
import ooga.view.game_view.agent.agent2d.Agent2DDataHolder;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.game_state.interfaces.GameStateView;
import ooga.view.game_view.map.map2d.Map2DView;
import org.lwjgl.glfw.GLFW;

public class GameState2DView extends GameStateView {

  final private static double elapsedInterval = 1.0 / 100.0;
  private static final float BACKGROUND_COLOR_R = 22.0f / 255.0f;
  private static final float BACKGROUND_COLOR_G = 23.0f / 255.0f;
  private static final float BACKGROUND_COLOR_B = 25.0f / 255.0f;
  private static final String VERTEX_SHADER_PATH = "/view/shaders/2d/cyberpunkTitleVertex.glsl";
  private static final String FRAGMENT_SHADER_PATH = "/view/shaders/2d/cyberpunkTitleFragment.glsl";
  private static final int WIDTH = 1080;
  private static final int HEIGHT = 1080;
  private Map<Integer, Boolean> isHurt;
  public Renderer2D renderer;
  public Shader shader;
  public BoundingBox box;
  String mapPath = "/view/textures/2d/cyberpunk/map/map.txt";
  private Map2DView map;
  private Map<Integer, Agent2DView> agentMap;
  private Map<Integer, Agent2DView> bulletMap;
  private Window window;
  private double lasTimeUpdated = 0;
  private double currentTimeUpdated = 0;
  private float zLayer = -0.8f;
  private float Z_INC = -0.2f;

  //TODO: remove this part!
  //TODO: loop through a list of agents to update its alive status/ adding new
  //      how much to move :set in Asset2D
  //TODO: COLLISION DETECT:
  //TODO:   0) whether the movement is valid (check before move)
  //        1) whether the attack is made (1. a method to calc the distance between two objects or with wall)
  private Map<Integer, Agent2DDataHolder> agentDataHolderMap;

  public GameState2DView(Map<Integer, Agent2DDataHolder> agentsData) {
    this.agentDataHolderMap = agentsData;
    this.bulletMap = new HashMap<>();
    this.agentMap = new HashMap<>();
    this.isHurt = new HashMap<>();
  }

  public float getCenterPositionX(int id) {
    return agentMap.get(id).getCenterPosition().getX();
  }

  public float getCenterPositionY(int id) {
    return agentMap.get(id).getCenterPosition().getY();
  }

  public void resetHurtStatus(){
    for(int id:isHurt.keySet())
    {
      isHurt.replace(id, false);
    }
  }

  public boolean getHurtStatus(int id){
    return isHurt.get(id);
  }

  @Override
  public void createWindow() throws IOException {

    window = new Window(WIDTH, HEIGHT, "Game");
    shader = new Shader(VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH);
    renderer = new Renderer2D(shader);

    window.setBackgroundColor(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B);
    window.create();
    //window.setFullscreen(true);

    map = new Map2DView(mapPath);
    this.box = new BoundingBox(map, agentMap);

    for (int id : agentDataHolderMap.keySet()) {
      setZDepth(agentDataHolderMap.get(id));
      GenerateAgentsData.loadAnimations(agentDataHolderMap.get(id));
      agentMap.put(id, new Agent2DView(id, agentDataHolderMap.get(id), box));
      agentMap.get(id).createMesh();
    }

    map.createMesh();
    shader.create();
  }

  @Override
  public void updateAgent(int id, String direction, String state) {
  }

  public void updateAgent(int id, String direction, String state, boolean isAttack)
      throws IOException {
    //System.out.println("called");
    currentTimeUpdated = Timer.getTime();
    if (currentTimeUpdated - lasTimeUpdated < elapsedInterval) {
      lasTimeUpdated = currentTimeUpdated;
      return;
    }
    lasTimeUpdated = currentTimeUpdated;
    //System.out.println("run");

    if (state.equals("DEATH")) {
      agentMap.get(id).terminate();
    }
    agentMap.get(id).update(direction, state);

    if (agentDataHolderMap.get(id).getSpawnerDict().containsKey(state)) // will spawn new agents
    {
      //System.out.println("spawning");
      Vector3f parentPosition = new Vector3f(agentMap.get(id).getCenterPosition(), 0f);
      String parentDirection = agentMap.get(id).getCurrentDirection();

      Vector3f newAgentDelta = Vector3f.zeros();
      Agent2DDataHolder newAgentData = positionNewAgent(
          agentDataHolderMap.get(id).getSpawnerDict().get(state),
          parentPosition, parentDirection, false);
      GenerateAgentsData.loadAnimations(newAgentData);
      setZDepth(newAgentData);

      if (newAgentData.isBullet()) {
        int newId = getNextBulletId();
        Agent2DView newAgent = new Agent2DView(newId, newAgentData, box);
        if (box.canMove(false, true, newAgent, newAgentDelta)) {
          bulletMap.put(newId, newAgent);
          bulletMap.get(newId).createMesh();
        }
      } else if (!newAgentData.isBullet()) {
        int newId = getNextAgentId();
        Agent2DView newAgent = new Agent2DView(newId, newAgentData, box);
        if (box.canMove(true, false, newAgent, newAgentDelta))
        {
          agentDataHolderMap.put(newId, newAgentData);
          agentMap.put(newId, newAgent);
          agentMap.get(newId).createMesh();
        }
      }
    }

    if (isAttack && box.isAttackEffective(agentMap.get(id))!=box.getNonId()) {
      isHurt.replace(box.isAttackEffective(agentMap.get(id)), true);
    }

  }

  public void updateBullets() {
//    System.out.println(bulletMap.keySet().size());
    for (int key : bulletMap.keySet()) {
      //check if hit the agent or wall
      Agent2DView bullet = bulletMap.get(key);
      //System.out.println(String.format("BEFORE:key is %d",key));
      //Test.printVector2f(bullet.getCenterPosition());
      bullet.update(bullet.getCurrentDirection(), "MOVE");
      // System.out.println(String.format("AFTER:key is %d",key));
      //Test.printVector2f(bullet.getCenterPosition());
      //if(!box.canMove(false, true, bullet, ))
    }
  }

  private Agent2DDataHolder positionNewAgent(Agent2DDataHolder data, Vector3f parentPosition,
      String parentDirection, boolean isOrigin) {
    float MOVEMENT_DELTA = isOrigin ? 0f : 100f;
    Agent2DDataHolder newAgentData = new Agent2DDataHolder(data);
    if (newAgentData.getInitialDirection().equals(GenerateAgentsData.getDirectionPlaceholder())) {
      newAgentData.setInitialDirection(parentDirection);
    }

    //System.out.println("dead");
    //Test.printVector3f(parentPosition);
    //Test.printVector3f(Asset2D.convertDirectionalSpeed(newAgentData.getInitialDirection(), MOVEMENT_DELTA));

    newAgentData.setPosition(Vector3f.add(parentPosition,
        Asset2D.convertDirectionalSpeed(newAgentData.getInitialDirection(), MOVEMENT_DELTA)));

    //Test.printVector3f(parentPosition);
    //Test.printVector3f(newAgentData.getPosition());

    setZDepth(newAgentData);

    //System.out.println(newAgentData.isBullet());
    //System.out.println("hah");
    return newAgentData;
  }

  private void setZDepth(Agent2DDataHolder data) {
    data.getPosition().setZ(zLayer);
    zLayer += Z_INC;
  }

  private int getNextAgentId() {
    int idx = 0;
    for (int key : agentDataHolderMap.keySet()) {
      idx = Math.max(key, idx);
    }
    return idx + 1;
  }

  private int getNextBulletId() {
    int idx = 0;
    for (int key : bulletMap.keySet()) {
      idx = Math.max(key, idx);
    }
    return idx + 1;
  }

  @Override
  public void updateWindow() {
    if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
      window.setFullscreen(!window.isFullscreen());
    }
    window.update();
  }

  @Override
  public void renderAgents() throws IOException { // get rid of dead agents
//    System.out.println("the set size of agents:");
//    System.out.println(agentMap.keySet().size());
    for (int id : new HashSet<>(agentMap.keySet())) {
      //System.out.println(agentma);
//      System.out.println("here is another player");
//      System.out.println(agentMap.get(id).getId());
      if (!agentMap.get(id).renderMesh(renderer)) {
//        System.out.println("the player's render return false");
//        System.out.println(agentMap.get(id).getId());
        //render didn't succeed

        //TODO if the agent is summoner, generate new agents

        Vector3f parentPosition = new Vector3f(agentMap.get(id).getCenterPosition(), 0f);
        String parentDirection = agentMap.get(id).getCurrentDirection();

        Vector3f newAgentDelta = Vector3f.zeros();

        Agent2DDataHolder newAgentData = positionNewAgent(
            agentDataHolderMap.get(id).getSpawnerDict().get(agentMap.get(id).getAction()),
            parentPosition, parentDirection, true);
        GenerateAgentsData.loadAnimations(newAgentData);

        setZDepth(newAgentData);
        int newId = getNextAgentId();
        Agent2DView newAgent = new Agent2DView(newId, newAgentData, box);

        if (box.canMove(true, false, newAgent, newAgentDelta)) {
          agentDataHolderMap.put(newId, newAgentData);
          agentMap.put(newId, newAgent);
          agentMap.get(newId).createMesh();
        }

        deleteAgent(id);
      }
    }
  }

  @Override
  public void renderAll() throws IOException {
    renderMap();
    renderAgents();
    renderBullets();
    renderWindow();
  }

  public void renderBullets() {
    for (int id : bulletMap.keySet()) {
      bulletMap.get(id).renderMesh(renderer);
    }
  }

  @Override
  public void deleteAgent(int id) {
    //System.out.println("delete happened");
    agentMap.get(id).destroyMesh();
    agentMap.remove(id);
  }

  @Override
  public void renderMap() {
    map.renderMesh(renderer);
  }

  @Override
  public void renderWindow() {
    window.swapBuffers();
  }

  @Override
  public void updateMap() {
  }

  @Override
  public void closeWindow() {
    window.destroy();

    for (int key : agentMap.keySet()) {
      agentMap.get(key).destroyMesh();
    }

    map.destroyMesh();
    shader.destroy();
  }

  @Override
  public boolean shouldWindowClose() {
    return window.shouldClose() || Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE);
  }

  @Override
  public boolean isKeyDown(int key) {
    return Input.isKeyDown(key);
  }
}
