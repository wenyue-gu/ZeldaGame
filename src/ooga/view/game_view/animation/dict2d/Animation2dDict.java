package ooga.view.game_view.animation.dict2d;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import ooga.view.engine.graphics.animation.Animation2D;
import ooga.view.engine.utils.cyberpunk2d.LoadCyberpunkAnimations;
import ooga.view.game_view.animation.interfaces.AnimationDict;

public class Animation2dDict extends AnimationDict {
  private Map<String, Animation2D> dict;
  private String direction;
  private String pre_action;
  private String action;

  public Animation2dDict() throws IOException {
    dict = LoadCyberpunkAnimations.loadMeleeRobotAnimations();

    //TODO: should remove from hardcoded!
    this.pre_action = "IDLE";
    this.action = "IDLE";
    this.direction = "E";
  }

  @Override
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

    //TODO: should remove from hardcoded!
    if (!pre_action.equals("SPRINT") && action.equals("SPRINT")){
      return getAnimationMap(direction, "PRESPRINT");
    }

    if (!pre_action.equals("ATTACK1") && action.equals("ATTACK3")){

      return getAnimationMap(direction, "ATTACKCOMBO");
    }


    return getAnimationMap(direction, action);
  }

  private Animation2D getAnimationMap(String direction, String action){
    return dict.get(String.format("%s_%s", direction, action));
  }

}
