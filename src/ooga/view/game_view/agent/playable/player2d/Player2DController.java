package ooga.view.game_view.agent.playable.player2d;

import java.io.IOException;
import ooga.view.engine.assets.Asset2D;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.game_view.agent.interfaces.agent2d.AgentController;
import ooga.view.game_view.animation.dict2d.AgentAnimationDict;

public class Player2DController extends AgentController {
  private String INITIAL_DIRECTION = "E";
  private String INITIAL_ACTION = "IDLE";

  public Player2DController() throws IOException {
    super();
    agentAnimationDict = new AgentAnimationDict();
    direction = INITIAL_DIRECTION;
    action = INITIAL_ACTION;
    this.setCurrentAnimation(direction, action);
  }

  @Override
  public void move(String direction, Mesh mesh) {
    for (int i=0;i<mesh.getVertices().length;i++){
      mesh.setVerticesPosition(i, Vector3f.add(mesh.getVertices()[i].getPosition(), Asset2D.convertDirectionalSpeed(direction)));
    }
  }

}
