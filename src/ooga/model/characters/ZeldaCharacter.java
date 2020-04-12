package ooga.model.characters;

import ooga.controller.gamecontrol.player.ZeldaPlayerControl;
import ooga.model.PropertyChangeNotifier;
import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.Alive;
import ooga.model.interfaces.Attacker;
import ooga.model.interfaces.Notifier;
import ooga.model.move.MovingObject2D;

import java.beans.PropertyChangeListener;
import ooga.model.test.listener.ExampleController;

public class ZeldaCharacter extends MovingObject2D implements Alive, Attacker, Notifier {

  public static final int DEFAULT_ATTACK = 0;
  public static final int DEFAULT_WEAPON = 0;

  private CharacterBase cb;
  private WeaponBase weaponBase;
  protected PropertyChangeNotifier notifier;

  public ZeldaCharacter(int initialHp, int id) {
    this(initialHp, DEFAULT_WEAPON, id);
  }

  public ZeldaCharacter(int initialHp, int weapon, int id) {
    this(initialHp, weapon, DEFAULT_ATTACK, id);
  }

  public ZeldaCharacter(int initialHp, int weapon, int attack, int id) {
    cb = new CharacterBase(id, initialHp);
    this.weaponBase = new WeaponBase(weapon, attack, movingDirection) {
      @Override
      public void fire() {
        setState(MovingState.ATTACK1);
      }
    };
    notifier = new PropertyChangeNotifier(this);
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
    notifier.firePropertyChange(ZeldaPlayerControl.PROPERTY_STATE, oldState, newState);
  }

  @Override
  public int getId() {
    return cb.getId();
  }

  @Override
  public void setId(int id) {
    cb.setId(id);
  }

  @Override
  public int getHP() {
    return cb.getHP();
  }


  @Override
  public void setHP(int hp) {
    cb.setHP(hp);
  }

  @Override
  public void addHP(int deltaHP) {
    cb.addHP(deltaHP);
  }

  @Override
  public void subtractHP(int deltaHP) {
    cb.subtractHP(deltaHP);
  }

  @Override
  public boolean isAlive() {
    return cb.isAlive();
  }

  @Override
  public void setWeapon(int weapon) {
    weaponBase.setWeapon(weapon);
  }

  @Override
  public int getWeapon() {
    return weaponBase.getWeapon();
  }

  @Override
  public void setAttack(int attack) {
    weaponBase.setAttack(attack);
  }

  @Override
  public int getAttack() {
    return weaponBase.getAttack();
  }

  @Override
  public void setFiringDirection(Direction direction) {
    weaponBase.setFiringDirection(direction);
  }

  @Override
  public void fire() {
    weaponBase.fire();
  }
}
