package ooga.model.characters;

import ooga.model.enums.backend.CharacterType;
import ooga.model.interfaces.Alive;

public class  CharacterBase implements Alive {
  protected int hp;
  protected int id;
  protected CharacterType characterType;

  public CharacterBase(int id, int initialHP, CharacterType characterType) {
    this.id = id;
    this.hp = initialHP;
    this.characterType = characterType;
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
  public void setType(CharacterType type) {
    this.characterType = type;
  }

  @Override
  public CharacterType getType() {
    return characterType;
  }
}
