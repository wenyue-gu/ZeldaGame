package ooga.view.game_view.agent.agent2d;

import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentView;
import ooga.view.game_view.game_state.state2d.BoundingBox;

public class Agent2DView extends AgentView {

  //TODO: should remove from hardcoded!

  protected Agent2DController controller;
  private boolean shouldTerminated = false;
  private BoundingBox box;
  private Vector2f halfBounds;

  public Agent2DView(int id, Agent2DDataHolder data, BoundingBox box) {
    super(data.getMoveAction());
    this.id = id;
    vertices = data.getVertices();
    indices = data.getIndices();
    halfBounds = data.getHalfBounds();
    controller = new Agent2DController(id, data, box);
    mesh = new Mesh(vertices, indices, controller.getCurrentAnimatedMaterial());
    object = new GameObject(Vector3f.zeros(), data.getRotation(), data.getScale(), mesh);
    controller.setObject(object);
    controller.setAgentView(this);
  }

  public int getId(){return id;}

  public Vector2f getHalfBounds() {
    return halfBounds;
  }

  public void terminate(){
    controller.setShouldTerminated(true);
  }

  public boolean renderMesh(Renderer2D renderer) {
    Material newFrame = controller.getCurrentAnimatedMaterial();
    if (newFrame!=null) {
      object.getMesh().setMaterial(newFrame);
      renderer.renderMesh(object);
      return true;
    }
    return false;
  }

  public Vector2f getCenterPosition(){return object.getMesh().getCenter();}

  public Vector2f getCenter(){
    float centerX = 0f;
    float centerY = 0f;

    for(Vertex v:vertices){
      centerX+=v.getPosition().getX();
      centerX+=v.getPosition().getY();
    }
    return new Vector2f(centerX/2.0f, centerY/2.0f);
  }

  public String getCurrentDirection(){return controller.getCurrentDirection();}

  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
//    System.out.println(MOVE_ACTION);
//    System.out.println(action);
    if (action.equals(MOVE_ACTION)) controller.move(direction);
  }

  public String getAction(){return controller.getAction();}
}
