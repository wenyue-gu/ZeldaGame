package ooga.view.game_view.game_state;

import java.util.List;
import ooga.view.game_view.agent.nonplayable.NonPlayableAgentView;
import ooga.view.game_view.agent.playable.PlayableAgentView;
import ooga.view.game_view.map.MapView;
import ooga.view.game_view.object.interactive.InteractiveObjectView;
import ooga.view.game_view.object.non_interactive.NonInteractiveObjectView;

public class AbstractGameView implements GameStateView {

  @Override
  public void update() {

  }

  @Override
  public MapView getMapView() {
    return null;
  }

  @Override
  public List<PlayableAgentView> getPlayableAgents() {
    return null;
  }

  @Override
  public List<NonPlayableAgentView> getNonPlayableAgents() {
    return null;
  }

  @Override
  public List<InteractiveObjectView> getInterObjects() {
    return null;
  }

  @Override
  public List<NonInteractiveObjectView> getNonInterObjects() {
    return null;
  }
}
