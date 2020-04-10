package ooga.view.game_view.map.interactive;

import java.io.IOException;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.TextMapReader;

public class Title2DView {
  private int map_x;
  private int map_y;
  private GameObject object;
  private Title2DController controller;

  private Mesh mesh;

  private Vertex[] vertices = new Vertex[] {
      new Vertex(new Vector3f(-0.5f,  0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
      new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)),
      new Vertex(new Vector3f( 0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)),
      new Vertex(new Vector3f( 0.5f,  0.5f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f))
  };

  private int[] indices = new int[] {
      0, 1, 2,
      0, 3, 2
  };

  Vector3f position;

  Vector3f rotation = new Vector3f(0,0,0);

  Vector3f scale;

  private Material material;

  public Title2DView(int window_pos_x, int window_pos_y, int map_x, int map_y, float scale_x, float scale_y, TextMapReader mapReader)
      throws IOException {
    this.map_x = map_x;
    this.map_y = map_y;

    controller = new Title2DController(map_x, map_y, mapReader);

    position = new Vector3f(window_pos_x, window_pos_y, 0);
    scale = new Vector3f(scale_x, scale_y, 1);

    mesh = new Mesh( vertices, indices, controller.getMaterial());

    object = new GameObject(position, rotation, scale, mesh);
  }

  public void createMesh(){mesh.create();}

  public void destroyMesh(){mesh.destroy();}

  public GameObject getGameObject() {return object;}

}
