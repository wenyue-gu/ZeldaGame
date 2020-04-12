package ooga.view.game_view.agent.interfaces.agent2d;

import ooga.view.engine.graphics.Material;
import ooga.view.engine.maths.Vector2f;
import ooga.view.game_view.animation.dict2d.AgentAnimationDict;

abstract public class AgentController {

  protected AgentAnimationDict agentAnimationDict;
  protected String direction;
  protected String action;

  public AgentController(){


  }

  public void setCurrentAnimation(String direction, String action){
    agentAnimationDict.setInUseAnimation(direction, action);
  }

  public Material getMaterial(){
    return agentAnimationDict.getAnimation().getCurrentFrame();
  }

  public abstract void move(Vector2f directions);




}
