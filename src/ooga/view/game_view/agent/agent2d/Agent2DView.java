package ooga.view.game_view.agent.agent2d;

import java.io.IOException;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentView;

public class Agent2DView extends AgentView {

  //TODO: should remove from hardcoded!
  final static String MOVE_ACTION = "SPRINT";

  public Agent2DView() throws IOException {
    super();
    vertices = Asset2D.getAgentVertices();
    indices = Asset2D.getAgentIndices();
    controller = new Agent2DController();
    mesh = new Mesh( vertices, indices, controller.getMaterial());
    object = new GameObject(Asset2D.getPlayerPosition(), Asset2D.getPlayerRotation(), Asset2D.getPlayerScale(), mesh);

  }

  public Vector2f getCenter(){
    float centerX = 0f;
    float centerY = 0f;

    for(Vertex v:vertices){
      centerX+=v.getPosition().getX();
      centerX+=v.getPosition().getY();
    }
    return new Vector2f(centerX/2.0f, centerY/2.0f);
  }

  @Override
  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
    //printVertices();
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
