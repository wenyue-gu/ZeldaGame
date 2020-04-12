package ooga.model.characters;

import ooga.model.PropertyChangeNotifier;
import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;
import ooga.model.interfaces.Alive;
import ooga.model.interfaces.Attacker;
import ooga.model.interfaces.Notifier;
import ooga.model.move.MovingObject2D;

import java.beans.PropertyChangeListener;

public class ZeldaCharacter extends MovingObject2D implements Alive, Attacker, Notifier {

  public static final int DEFAULT_ATTACK = 0;
  public static final int DEFAULT_WEAPON = 0;

  protected int hp;
  protected int weapon;
  protected int attack;
  protected int id;
  protected Direction attackingDirection;
  protected transient PropertyChangeNotifier notifier;

  public ZeldaCharacter(int initialHp, int id) {
    this(initialHp, DEFAULT_WEAPON, id);
  }

  public ZeldaCharacter(int initialHp, int weapon, int id) {
    this(initialHp, weapon, DEFAULT_ATTACK, id);
  }

  public ZeldaCharacter(int initialHp, int weapon, int attack, int id) {
    super();
    hp = initialHp;
    this.weapon = weapon;
    this.attack = attack;
    this.id = id;
    attackingDirection = movingDirection;
    notifier = new PropertyChangeNotifier(this);
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getHP() {
    return hp;
  }

  @Override
  public void setHP(int hp) {
    this.hp = hp;
  }

  @Override
  public void addHP(int deltaHP) {
    hp += deltaHP;
  }

  @Override
  public void subtractHP(int deltaHP) {
    hp -= deltaHP;
  }

  @Override
  public boolean isAlive() {
    return hp > 0;
  }

  @Override
  public void setWeapon(int weapon) {
    this.weapon = weapon;
  }

  @Override
  public int getWeapon() {
    return weapon;
  }

  @Override
  public void setAttack(int attack) {
    this.attack = attack;
  }

  @Override
  public int getAttack() {
    return attack;
  }

  @Override
  public void setFiringDirection(Direction direction) {
    this.attackingDirection = direction;
  }

  @Override
  public void fire() {
    setState(MovingState.ATTACK);
  }

  @Override
  public void addListener(PropertyChangeListener listener) {
    notifier.addListener(listener);
  }

  @Override
  public void removeListener(PropertyChangeListener listener) {
    notifier.removeListener(listener);
  }

  // TODO: change the string
  @Override
  protected void notifyStateChange(MovingState oldState, MovingState newState) {
    notifier.firePropertyChange("TODO",oldState, newState);
  }
}
