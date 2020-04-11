package ooga.view.game_view.map.map2d;

import java.io.IOException;
import ooga.view.engine.graphics.Renderer2D;
import ooga.view.engine.utils.TextMapReader;
import ooga.view.game_view.map.interfaces.MapView;

public class Map2DView implements MapView {
  private String path;
  private TextMapReader mapReader;
  private Tile2DView[] titles;

  public Map2DView(String path, float window_x, float window_y) throws IOException {
    this.mapReader = new TextMapReader(path);
    titles = new Tile2DView[mapReader.getMapWidth()*mapReader.getMapHeight()];

    float title_x = window_x / mapReader.getMapWidth();
    float title_y = window_y / mapReader.getMapHeight();

    float scale_x = title_x / mapReader.getTitlePixel();
    float scale_y = title_y / mapReader.getTitlePixel();

    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++] = new Tile2DView((int) title_x*i, (int) title_y*j, i, j, scale_x, scale_y, mapReader);
      }
    }
  }

  public void createMesh(){
    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++].createMesh();
      }
    }
  }

  public void destroyMesh(){
    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++].destroyMesh();
      }
    }
  }

  public void renderMesh(Renderer2D renderer){

    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        renderer.renderMesh(titles[idx++].getGameObject());
      }
    }

  }

  @Override
  public void getView() {

  }

  @Override
  public void update() {

  }
}
