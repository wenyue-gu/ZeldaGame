package interfaces.view.game_view.game_state;

import interfaces.view.game_view.agent.nonplayable.NonPlayableAgentView;
import interfaces.view.game_view.agent.playable.PlayableAgentView;
import interfaces.view.game_view.map.MapView;
import interfaces.view.game_view.object.interactive.InteractiveObjectView;
import interfaces.view.game_view.object.non_interactive.NonInteractiveObjectView;
import java.util.List;

public interface GameStateView { // will be extending Javafx.Scene

  void update();

  MapView getMapView();

  List<PlayableAgentView> getPlayableAgents();

  List<NonPlayableAgentView> getNonPlayableAgents();

  List<InteractiveObjectView> getInterObjects();

  List<NonInteractiveObjectView> getNonInterObjects();

}
