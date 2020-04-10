package ooga.model.characters;

import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;
import ooga.model.interfaces.Alive;
import ooga.model.interfaces.Attacker;
import ooga.model.move.MovingObject2D;

public class ZeldaCharacter extends MovingObject2D implements Alive, Attacker {

  public static final int DEFAULT_ATTACK = 0;
  public static final int DEFAULT_WEAPON = 0;

  protected int hp;
  protected int weapon;
  protected int attack;
  protected int id;
  protected Direction attackingDirection;

  public ZeldaCharacter(int initialHp, int id) {
    this(initialHp, DEFAULT_WEAPON, id);
  }

  public ZeldaCharacter(int initialHp, int weapon, int id) {
    this(initialHp, weapon, DEFAULT_ATTACK, id);
  }

  public ZeldaCharacter(int initialHp, int weapon, int attack, int id) {
    hp = initialHp;
    this.weapon = weapon;
    this.attack = attack;
    this.id = id;
    attackingDirection = movingDirection;
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
    notifyAnimation();
  }

  // TODO: implement this
  private void notifyAnimation() {
  }
}
