package ooga.view.game_view.agent.interfaces.agent2d;

import ooga.view.engine.graphics.Material;
import ooga.view.engine.maths.Vector2f;
import ooga.view.game_view.animation.dict2d.AgentAnimationDict;

abstract public class AgentController {

  private static final String DEFAULT_ACTION = "IDLE";
  protected AgentAnimationDict agentAnimationDict;
  protected String direction;
  protected String action;

  public AgentController(){}

  public void setCurrentAnimation(String direction, String action){
    this.direction = direction;
    this.action = action;
    agentAnimationDict.setInUseAnimation(direction, action);
  }

  public Material getMaterial(){
    Material frame = agentAnimationDict.getAnimation().getCurrentFrame();
    if (frame == null){
      setCurrentAnimation(direction, DEFAULT_ACTION);
      return agentAnimationDict.getAnimation().getCurrentFrame();
    }
    else{
      return frame;
    }
  }

  public abstract void move(Vector2f directions);




}
