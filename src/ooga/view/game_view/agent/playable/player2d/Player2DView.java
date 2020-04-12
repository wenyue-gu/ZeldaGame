package ooga.view.game_view.agent.playable.player2d;

import java.io.IOException;
import ooga.view.engine.assets.Asset2D;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.agent2d.AgentView;

public class Player2DView extends AgentView {

  final static String MOVE_ACTION = "SPRINT";

  public Player2DView() throws IOException {
    super();
    controller = new Player2DController();
    mesh = new Mesh( vertices, indices, controller.getMaterial());
    object = new GameObject(Asset2D.getPlayerPosition(), Asset2D.getPlayerRotation(), Asset2D.getPlayerScale(), mesh);

  }

  @Override
  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
    printVertices();
    if (action.equals(MOVE_ACTION)) controller.move(direction, mesh);
  }

  @Override
  public void createMesh() {
    mesh.create();
  }

  @Override
  public void destroyMesh() {
    mesh.destroy();
  }

  @Override
  public void renderMesh(Renderer2D renderer) {
    object.getMesh().setMaterial(controller.getMaterial());
    renderer.renderMesh(object);
  }


  private void printVertices(){
    for (Vertex v:mesh.getVertices()){
      System.out.println(String.format("(%s, %s, %s)", v.getPosition().getX(), v.getPosition().getY(), v.getPosition().getZ()));
    }
  }
}
