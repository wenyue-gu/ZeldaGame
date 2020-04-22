package ooga.model.characters;

import ooga.model.Scorer;
import ooga.model.interfaces.Scorable;

public class  ZeldaPlayer extends ZeldaCharacter implements Scorable {

  Scorer scorer;//= new Scorer();

  public ZeldaPlayer(int initialHp, int id) {
    super(initialHp, id);
    scorer= new Scorer();
  }

  public ZeldaPlayer(int initialHp, int weapon, int id) {
    super(initialHp, weapon, id);
    scorer= new Scorer();
  }

  public ZeldaPlayer(int initialHp, int weapon, int attack, int id, int x, int y) {
    super(initialHp, weapon, attack, id, x, y);
    scorer= new Scorer();
  }

  @Override
  public boolean isScoring() {
    return scorer.isScoring();
  }

  @Override
  public double getScore() {
    return scorer.getScore();
  }

  @Override
  public void setScore(double score) {
    scorer.setScore(score);
  }

  @Override
  public void addScore(double deltaScore) {
    scorer.addScore(deltaScore);
  }
}
