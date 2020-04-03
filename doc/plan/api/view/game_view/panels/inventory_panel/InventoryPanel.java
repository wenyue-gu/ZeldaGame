package ooga.view.game_view.panels.inventory_panel;

public interface InventoryPanel {

  void display(int PlayableAgentID);

  void update(int PlayableAgentID);

  boolean isDisplayed(int PlayableAgentID);

  void close();

}
