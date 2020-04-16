package ooga.view.game_view.game_state.state2d;

import ooga.view.engine.maths.Vector2f;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.map.map2d.Map2DView;

public class BoundingBox  {
  private Map2DView mapView;
  private Agent2DView playerView;
  private static final double MELEE_BOT_HEIGHT = 0.2;
  private static final double MELEE_BOT_WIDTH = 0.1;

  public BoundingBox(Map2DView map, Agent2DView player){
    this.mapView = map;
    this.playerView = player;
  }

  public Vector2f getNextPosition(String direction){
    return null;
  }



}
