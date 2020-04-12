package ooga.view.engine.graphics;

import java.io.IOException;
import ooga.view.engine.utils.ImageLoader;
import ooga.view.engine.utils.Timer;
import org.lwjglx.Sys;

//not sure if this gonna works, if not, it sucks
//change the material in the mesh of the game object each frame

public class Animation2D {

  private final static double HARDSET_TIME = 1.0/18.0;
  private Material animatedFrames[];

  private int framePointer;
  private int frameAmount;

  private double elapsedTime;
  private double currentTime;
  private double lastTime;
  private double fps;

  public Animation2D(int cnt, int fps, String dir) {

    resetAnimation();
    this.fps = 1.0 / fps;
    this.frameAmount = cnt;
    dir = dir.replace("\\", "/");

    this.animatedFrames = new Material[cnt];
    for(int i=0; i<cnt;i++){
      String spritePath = String.format("%s/%s_.png", dir, i);
      System.out.println(ImageLoader.getImageHeight(spritePath));
      System.out.println(ImageLoader.getImageWidth(spritePath));
      this.animatedFrames[i] = new Material(spritePath);
      this.animatedFrames[i].createTexture();
    }
  }

  public Animation2D(int cnt) {
    resetAnimation();
    this.fps = 1.0 / fps;
    this.frameAmount = cnt;
    this.animatedFrames = new Material[cnt];
  }

  public void resetAnimation(){
    this.framePointer = 0;
    this.elapsedTime = 0;
    this.currentTime = 0;
    this.lastTime = Timer.getTime();

  }

  public static Animation2D combineAnimations(Animation2D animation_1, Animation2D animation_2){

    int totalFrames = animation_1.getFrameAmount()+animation_2.getFrameAmount();
    Animation2D combined = new Animation2D(totalFrames);

    int idx = 0;
    for (int i=0; i<animation_1.getFrameAmount(); i++){
      combined.setAnimatedFrames(idx++, animation_1.getAnimatedFrames(i));
    }

    for (int i=0; i<animation_2.getFrameAmount(); i++){
      combined.setAnimatedFrames(idx++, animation_2.getAnimatedFrames(i));
    }

    return combined;
  }

  public void setAnimatedFrames(int idx, Material frame){
    this.animatedFrames[idx] = frame;
  }

  public Material getAnimatedFrames(int idx){
    return this.animatedFrames[idx];
  }

  public int getFrameAmount(){return frameAmount;}

  /*
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
  */
  public Material getCurrentFrame(){
    System.out.println(framePointer);
    System.out.println(animatedFrames.length);
    System.out.println(currentTime);
    System.out.println(elapsedTime);
    System.out.println(lastTime);
    System.out.println(currentTime - lastTime);
    System.out.println(fps);
    System.out.println();

    this.currentTime = Timer.getTime();
    this.elapsedTime += currentTime - lastTime;

    if (elapsedTime >= fps || elapsedTime >= HARDSET_TIME){
      elapsedTime = 0;
      framePointer++;
      this.lastTime = currentTime;
    }

    if (framePointer >= animatedFrames.length)
    {
      framePointer = 0;
      return null;
    }


    return animatedFrames[framePointer];
  }
}