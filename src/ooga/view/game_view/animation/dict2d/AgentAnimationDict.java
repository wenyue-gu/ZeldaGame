package ooga.view.game_view.animation.dict2d;

import java.io.IOException;
import java.util.Map;
import ooga.view.engine.graphics.Animation2D;
import ooga.view.engine.utils.cyberpunk2d.LoadCyberpunkAnimations;

public class AgentAnimationDict {
  private Map<String, Animation2D> dict;
  private String direction;
  private String pre_action;
  private String action;

  public AgentAnimationDict() throws IOException {
    dict = LoadCyberpunkAnimations.loadMeleeRobotAnimations();
  }

  public void setInUseAnimation (String direction, String action){
    this.direction = direction;
    this.pre_action = this.action;
    this.action = action;
  }

  public Animation2D getAnimation(){

    if (!pre_action.equals("SPRINT") && action.equals("SPRINT")){

      Animation2D animation_1 = getAnimationMap(direction, "PRESPRINT");
      Animation2D animation_2 = getAnimationMap(direction, action);

      return Animation2D.combineAnimations(animation_1, animation_2);
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
