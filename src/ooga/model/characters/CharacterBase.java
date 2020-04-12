package ooga.model.characters;

import ooga.model.interfaces.Alive;

public class CharacterBase implements Alive {
  protected int hp;
  protected int id;

  public CharacterBase(int id, int initialHP) {
    this.id = id;
    this.hp = initialHP;
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
}
