package ooga.view.engine.graphics;

import java.io.IOException;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.Timer;

//not sure if this gonna works, if not, it sucks
//change the material in the mesh of the game object each frame

public class Animation2D {

  private Material animatedFrames[];

  private int framePointer;

  private double elapsedTime;
  private double currentTime;
  private double lastTime;
  private double fps;

  public Animation2D(int cnt, int fps, String dir) throws IOException {

    this.framePointer = 0;
    this.elapsedTime = 0;
    this.currentTime = 0;
    this.lastTime = Timer.getTime();
    this.fps = 1.0 / fps;

    this.animatedFrames = new Material[cnt];
    for(int i=0; i<cnt;i++){
      String spritePath = String.format("%s/%s.png", dir, i);
      this.animatedFrames[i] = new Material(spritePath);
      this.animatedFrames[i].createTexture();
    }
  }

  public void bind(){
    this.currentTime = Timer.getTime();
    this.elapsedTime += currentTime - lastTime;

    if (elapsedTime >= fps){
      elapsedTime = 0;
      framePointer++;
    }

    if (framePointer >= animatedFrames.length) framePointer = 0;

    this.lastTime = currentTime;

    animatedFrames[framePointer].bind();
  }
}
