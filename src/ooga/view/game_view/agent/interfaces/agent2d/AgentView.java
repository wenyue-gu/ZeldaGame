package ooga.view.game_view.agent.interfaces.agent2d;

import ooga.view.engine.assets.Asset2D;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.objects.GameObject;

abstract public class AgentView {

  protected Vertex[] vertices;
  protected int[] indices;
  protected Mesh mesh;
  protected GameObject object;
  protected AgentController controller;

  public AgentView(){
    vertices = Asset2D.getAgentVertices();
    indices = Asset2D.getAgentIndices();
    //mesh = new Mesh( vertices, indices, controller.getMaterial());
    //object = new GameObject(Asset2D.getPosition(), Asset2D.getRotation(), Asset2D.getScale(), mesh);
  }

  abstract public void update(String direction, String action);

  abstract public void createMesh();

  abstract public void destroyMesh();

  abstract public void renderMesh(Renderer2D renderer);

}
