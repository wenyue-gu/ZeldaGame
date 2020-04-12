package ooga.view.game_view.agent.playable.player2d;

import java.io.IOException;
import ooga.view.engine.maths.Vector2f;
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
  }

  @Override
  public void move(Vector2f directions) {

  }

}
