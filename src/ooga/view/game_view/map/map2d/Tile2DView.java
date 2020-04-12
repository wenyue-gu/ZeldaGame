package ooga.view.game_view.map.map2d;

import java.io.IOException;
import ooga.view.engine.assets.Asset2D;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.TextMapReader;

public class Tile2DView {
  private int map_x;
  private int map_y;
  private GameObject object;
  private Tile2DController controller;
  private float delta = 17;

  private Mesh mesh;

  private Vertex[] vertices;
  private int[] indices;

  private Material material;

  public Tile2DView(int window_pos_x, int window_pos_y, int map_x, int map_y, float scale_x, float scale_y, TextMapReader mapReader)
      throws IOException {
    this.map_x = map_x;
    this.map_y = map_y;
    this.vertices = Asset2D.getTileVertices();
    this.indices = Asset2D.getTileIndices();

    controller = new Tile2DController(map_x, map_y, mapReader);
    setLocation(map_x, map_y);

    mesh = new Mesh( vertices, indices, controller.getMaterial());
    object = new GameObject(Asset2D.getPosition(), Asset2D.getRotation(), Asset2D.getScale(), mesh);
  }

  private void setLocation(int x, int y){
    Vector3f translator = new Vector3f(y-delta, -x+delta, 0);
    for (Vertex v:vertices){
      v.setPosition(Vector3f.add(v.getPosition(), translator));
    }
  }

  public void createMesh(){mesh.create();}

  public void destroyMesh(){mesh.destroy();}

  public GameObject getGameObject() {return object;}

}
