package ooga.model.test.listener;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.MovingState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class  ModelListenerTest {
  ExampleController ec = new ExampleController();
  ZeldaCharacter zeldaCharacter = new ZeldaCharacter(0,0);

  @Test
  void listenCharacterStateChange() {
    ec.addModel(zeldaCharacter);
    zeldaCharacter.setState(MovingState.DEATH);
    assertEquals(zeldaCharacter.getState(), MovingState.DEATH);

    zeldaCharacter.setState(MovingState.ATTACK1);
    assertEquals(zeldaCharacter.getState(), MovingState.ATTACK1);
  }
}
