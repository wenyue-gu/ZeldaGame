package ooga.controller.gamecontrol;

import ooga.controller.ZeldaControlInterface;
import ooga.model.interfaces.movables.Movable1D;

import java.util.List;

public class NPCControl implements ZeldaControlInterface {
    Movable1D myNPC;
    public NPCControl(Movable1D NPC){
        myNPC = NPC;
    }

    @Override
    public void update() {
        //for(Object c:myNPC) c.update();
    }
}
