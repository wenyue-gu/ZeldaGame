package ooga.view.engine.graphics;

import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;

public class Vertex {

  private Vector3f position, normal;
  private Vector2f textureCoord;

  public Vertex(Vector3f position, Vector3f normal, Vector2f textureCoord) {
    this.position = position;
    this.normal = normal;
    this.textureCoord = textureCoord;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f newPos) {
    position = newPos;
  }

  public Vector3f getNormal() {
    return normal;
  }

  public Vector2f getTextureCoord() {
    return textureCoord;
  }
}