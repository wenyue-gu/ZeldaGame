package ooga.model.characters;

import ooga.model.interfaces.Scorable;

public class ZeldaPlayer extends ZeldaCharacter implements Scorable {

  protected double score;
  private boolean isScoring;

  public ZeldaPlayer(int initialHp) {
    super(initialHp);
  }

  public ZeldaPlayer(int initialHp, int weapon) {
    super(initialHp, weapon);
  }

  public ZeldaPlayer(int initialHp, int weapon, int attack) {
    super(initialHp, weapon, attack);
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
