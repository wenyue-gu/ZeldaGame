package ooga.controller.test;


import javafx.scene.input.KeyCode;
import ooga.controller.gamecontrol.player.ZeldaPlayerControl;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.Direction;
import ooga.model.enums.MovingState;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerKeyInputTest1 {
    @Test
    void testLeftArrow () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ZeldaPlayer player = new ZeldaPlayer(100,0);
        ZeldaPlayerControl control = new ZeldaPlayerControl();
        control.setMyPlayer(player);
        control.keyInput(KeyCode.LEFT);
        assertEquals(MovingState.WALK, player.getState());
        assertEquals(Direction.WEST, player.getDirection());
    }


}
