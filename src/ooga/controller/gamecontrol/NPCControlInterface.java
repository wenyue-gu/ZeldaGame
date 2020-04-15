package ooga.controller.gamecontrol;

import ooga.controller.ZeldaControlInterface;
import ooga.model.interfaces.movables.Movable1D;

public interface NPCControlInterface extends ZeldaControlInterface {
    void setMyNPC(Movable1D NPC);

}
