package controller.gamecontrol;

import controller.gamecontrol.MovableControll1D;

public interface MovableControll2D extends MovableControll1D {
  void up(double deltaY);
  void down(double deltaY);
}
