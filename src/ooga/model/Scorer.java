package ooga.model;

import ooga.model.interfaces.Scorable;

public class  Scorer implements Scorable {
  protected double score;
  protected double goalScore;
  private boolean isScoring;

  public Scorer(double goalScore) {
    this(0, goalScore);
  }

  public Scorer(double score, double goalScore) {
    this(score, goalScore, true);
  }

  public Scorer(double score, double goalScore, boolean isScoring) {
    this.score = score;
    this.goalScore = goalScore;
    this.isScoring = isScoring;
  }

  @Override
  public double getGoalScore() {
    return goalScore;
  }

  @Override
  public void setGoalScore(double goalScore) {
    this.goalScore = goalScore;
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
