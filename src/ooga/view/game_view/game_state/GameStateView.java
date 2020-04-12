package ooga.view.game_view.game_state;

import javafx.scene.Node;
import ooga.view.game_view.agent.nonplayable.interfaces.NonPlayableAgentView;
import ooga.view.game_view.agent.playable.interfaces.PlayableAgentView;
import ooga.view.game_view.map.interfaces.MapView;
import ooga.view.game_view.object.interactive.interfaces.InteractiveObjectView;
import ooga.view.game_view.object.non_interactive.interfaces.NonInteractiveObjectView;
import java.util.List;

public interface GameStateView { // will be extending Javafx.Scene

  Node getGameStateView();

  void update();

  MapView getMapView();

  List<PlayableAgentView> getPlayableAgents();

  List<NonPlayableAgentView> getNonPlayableAgents();

  List<InteractiveObjectView> getInterObjects();

  List<NonInteractiveObjectView> getNonInterObjects();

}
