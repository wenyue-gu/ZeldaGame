package ooga.view.game_view.agent.interfaces;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.objects.GameObject;

abstract public class AgentView {

  protected Vertex[] vertices;
  protected int[] indices;
  protected Mesh mesh;
  protected GameObject object;
  protected AgentController controller;

  public AgentView(){}

  abstract public void update(String direction, String action);

  abstract public void createMesh();

  abstract public void destroyMesh();

  abstract public void renderMesh(Renderer2D renderer);

  public Vertex[] getVertices(){return vertices;}

}
