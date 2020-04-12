package ooga.view.engine.assets;

import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;

public class Asset2D {

  public static Vertex[] getTileVertices(){

    return new Vertex[]{
        new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)),
        new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f))
    };

  }

  public static int[] getTileIndices(){
    return new int[] {
        0, 1, 2,
        0, 3, 2
    };
  }

  public static Vertex[] getAgentVertices(){
    return new Vertex[]{
        new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f), new Vector2f(0.0f, 0.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), new Vector2f(0.0f, 1.0f)),
        new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f), new Vector2f(1.0f, 1.0f)),
        new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector2f(1.0f, 0.0f))
    };
  }

  public static int[] getAgentIndices(){
    return new int[] {
        0, 1, 2,
        0, 3, 2
    };
  }

  public static Vector3f getPosition(){
    return new Vector3f(0, 0, 5f);
  }

  public static Vector3f getRotation(){
    return new Vector3f(0,0,0);
  }

  public static Vector3f getScale(){
    return new Vector3f(0.05f, 0.05f, 0.05f);
  }



}
