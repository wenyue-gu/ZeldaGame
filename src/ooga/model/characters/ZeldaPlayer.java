package ooga.model.characters;

import ooga.model.Scorer;
import ooga.model.enums.backend.CharacterType;
import ooga.model.interfaces.Scorable;

public class  ZeldaPlayer extends ZeldaCharacter implements Scorable {

  Scorer scorer;

  public ZeldaPlayer(int initialHp, int id, double currentScore, double goal, CharacterType type) {
    super(initialHp, id, type);
    scorer= new Scorer(currentScore, goal);
  }

  public ZeldaPlayer(int initialHp, int weapon, int id, double currentScore, double goal, CharacterType type) {
    super(initialHp, weapon, id, type);
    scorer= new Scorer(currentScore, goal);
  }

  public ZeldaPlayer(int initialHp, int weapon, int attack, int id, int x, int y, double currentScore, double goal, CharacterType type) {
    super(initialHp, weapon, attack, id, x, y, type);
    scorer= new Scorer(currentScore, goal);
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
  public double getGoalScore() {
    return scorer.getGoalScore();
  }

  @Override
  public void setGoalScore(double goalScore) {
    scorer.setScore(goalScore);
  }

  @Override
  public void setScore(double score) {
    scorer.setScore(score);
  }

  @Override
  public void addScore(double deltaScore) {
    scorer.addScore(deltaScore);
  }

  @Override
  public boolean hasWon() {
    return scorer.hasWon();
  }
}
