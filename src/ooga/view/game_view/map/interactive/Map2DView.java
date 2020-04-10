package ooga.view.game_view.map.interactive;

import ooga.view.engine.graphics.Renderer2D;
import ooga.view.engine.utils.TextMapReader;
import ooga.view.game_view.map.MapView;

public class Map2DView implements MapView {
  private String path;
  private TextMapReader mapReader;
  private Title2DView[] titles;

  public Map2DView(String path, float window_x, float window_y){
    this.mapReader = new TextMapReader(path);
    titles = new Title2DView[mapReader.getMapWidth()*mapReader.getMapHeight()];

    float title_x = window_x / mapReader.getMapWidth();
    float title_y = window_y / mapReader.getMapHeight();

    float scale_x = title_x / mapReader.getTitlePixel();
    float scale_y = title_y / mapReader.getTitlePixel();

    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++] = new Title2DView((int) title_x*i, (int) title_y*j, scale_x, scale_y, mapReader);
      }
    }
  }

  public void render(Renderer2D renderer){

    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        renderer.renderMesh(titles[i++].getGameObject());
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
