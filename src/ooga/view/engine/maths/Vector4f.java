package ooga.view.engine.maths;

public class Vector4f {
  public static final int SIZE = 4;
  private float[] elements = new float[SIZE];

  public static Vector4f ones() {
    Vector4f result = new Vector4f();

    for (int i = 0; i < SIZE; i++) {
      result.set(i, 1);
    }

    return result;
  }

  public float get(int index){
    return elements[index];
  }

  public void set(int index, float value){
    elements[index] = value;
  }

  public static Vector4f multiply(Matrix4f matrix, Vector4f vector){

    Vector4f result = Vector4f.ones();

    for (int i=0; i<SIZE; i++){
      float x = 0.0f;
      for (int j=0; j<SIZE; j++){
        x += matrix.get(i,j)*vector.get(i);
      }
      result.set(i, x);
    }

    return result;
  }

  public static Vector4f increaseDim(Vector3f vector3f){
    Vector4f result = Vector4f.ones();

    result.set(0, vector3f.getX());
    result.set(1, vector3f.getY());
    result.set(2, vector3f.getZ());

    return result;
  }

  public static Vector3f reduceDim(Vector4f vector4f){
    return new Vector3f(vector4f.get(0), vector4f.get(1), vector4f.get(2));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    for (int i=0; i<SIZE; i++){
      result = prime * result + Float.floatToIntBits(elements[i]);
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;

    Vector4f other = (Vector4f) obj;
    for (int i=0; i<SIZE; i++){
      if (Float.floatToIntBits(elements[i]) != Float.floatToIntBits(other.get(i)))
        return false;
    }
    return true;
  }

}
