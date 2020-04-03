package ooga.view.game_view.game_state;

import ooga.view.game_view.agent.nonplayable.NonPlayableAgentView;
import ooga.view.game_view.agent.playable.PlayableAgentView;
import ooga.view.game_view.map.MapView;
import ooga.view.game_view.object.interactive.InteractiveObjectView;
import ooga.view.game_view.object.non_interactive.NonInteractiveObjectView;
import java.util.List;

public interface GameStateView { // will be extending Javafx.Scene

  void update();

  MapView getMapView();

  List<PlayableAgentView> getPlayableAgents();

  List<NonPlayableAgentView> getNonPlayableAgents();

  List<InteractiveObjectView> getInterObjects();

  List<NonInteractiveObjectView> getNonInterObjects();

}
