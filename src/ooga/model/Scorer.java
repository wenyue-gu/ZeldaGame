package ooga.model;

import ooga.model.interfaces.Scorable;

public class Scorer implements Scorable {
  protected double score;
  private boolean isScoring;

  public Scorer() {
    this(0);
  }

  public Scorer(double score) {
    this(score, true);
  }

  public Scorer(double score, boolean isScoring) {
    this.score = score;
    this.isScoring = isScoring;
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
