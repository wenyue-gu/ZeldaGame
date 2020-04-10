package ooga.model.characters;

import ooga.model.interfaces.Scorable;

public class ZeldaPlayer extends ZeldaCharacter implements Scorable {

  protected double score;
  private boolean isScoring;

  public ZeldaPlayer(int initialHp, int id) {
    super(initialHp, id);
  }

  public ZeldaPlayer(int initialHp, int weapon, int id) {
    super(initialHp, weapon, id);
  }

  public ZeldaPlayer(int initialHp, int weapon, int attack, int id) {
    super(initialHp, weapon, attack, id);
    isScoring = true;
    score = 0;
  }

  @Override
  public boolean isScoring() {
    return isScoring;
  }

  @Override
  public double getScore() {
    return score;
  }

  @Override
  public void setScore(double score) {
    this.score = score;
  }

  @Override
  public void addScore(double deltaScore) {
    score += deltaScore;
  }
}
