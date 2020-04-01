package controller;

public class MapController implements ZeldaController {
  private GameMap myMap;
  private MapView myMapView;

  public MapController (){
    myMap = new GameMap();
    myMapView = new MapView();

  }

  public void keyInput(String key){

  }

}
