package ooga.view.game_view.agent.playable.player2d;

import java.io.IOException;
import ooga.view.engine.assets.Asset2D;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.agent2d.AgentView;

public class Player2DView extends AgentView {

  final static String MOVE_ACTION = "SPRINT";

  public Player2DView() throws IOException {
    super();
    controller = new Player2DController();
    mesh = new Mesh( vertices, indices, controller.getMaterial());
    object = new GameObject(Asset2D.getPosition(), Asset2D.getRotation(), Asset2D.getScale(), mesh);

  }

  @Override
  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
    if (action.equals(MOVE_ACTION)) move(direction);
  }

  private void move(String direction){
    for (Vertex v:vertices){
      v.setPosition(Vector3f.add(v.getPosition(), Asset2D.convertDirectionalSpeed(direction)));
    }
  }

  @Override
  public void render(Renderer2D renderer) {
    object.getMesh().setMaterial(controller.getMaterial());
    renderer.renderMesh(object);
  }

}
