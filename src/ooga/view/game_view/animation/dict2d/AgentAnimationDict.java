package ooga.view.game_view.animation.dict2d;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import ooga.view.engine.graphics.Animation2D;
import ooga.view.engine.utils.cyberpunk2d.LoadCyberpunkAnimations;
import org.lwjglx.Sys;

public class AgentAnimationDict {
  private Map<String, Animation2D> dict;
  private String direction;
  private String pre_action;
  private String action;

  public AgentAnimationDict() throws IOException {
    dict = LoadCyberpunkAnimations.loadMeleeRobotAnimations();
    this.pre_action = "IDLE";
    this.action = "IDLE";
    this.direction = "E";
  }

  public void setInUseAnimation (String direction, String action){
    this.direction = direction;
    this.pre_action = this.action;
    this.action = action;
    resetAnimationDict();
  }

  private void resetAnimationDict(){
    for(Entry<String, Animation2D> entry:dict.entrySet()){
      entry.getValue().resetAnimation();
    }
  }

  public Animation2D getAnimation(){
    /*System.out.println(dict.size());
    for(String key:dict.keySet()){
      System.out.println(key);
    }*/

    if (!pre_action.equals("SPRINT") && action.equals("SPRINT")){
      return getAnimationMap(direction, "PRESPRINT");
    }

    if (!pre_action.equals("ATTACK1") && action.equals("ATTACK3")){

      Animation2D animation_1 = getAnimationMap(direction, "ATTACK2");
      Animation2D animation_2 = getAnimationMap(direction, action);

      return Animation2D.combineAnimations(animation_1, animation_2);
    }


    return getAnimationMap(direction, action);
  }

  private Animation2D getAnimationMap(String direction, String action){
    return dict.get(String.format("%s_%s", direction, action));
  }

}
