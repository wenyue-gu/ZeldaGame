package ooga.view.game_view.map.map2d;

import java.io.IOException;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.utils.TextMapReader;

public class Tile2DController {
  private static String path = "/view/textures/2d/cyberpunk/map/subtitles";
  private static int id_idx = 0;
  private boolean walkable;
  private int map_x, map_y;
  private int palette_x, palette_y;
  private Material material;
  private int id;

  public Tile2DController(int map_x, int map_y, TextMapReader mapReader) throws IOException {

    this.id = id_idx++;
    System.out.println(map_x);
    System.out.println(map_y);
    this.map_x = map_x;
    this.map_y = map_y;

    this.palette_x = mapReader.getMapCell(map_x, map_y)%mapReader.getPaletteWidth();
    this.palette_y = mapReader.getMapCell(map_x, map_y)/mapReader.getPaletteWidth();

    System.out.println(palette_x);
    System.out.println(palette_y);

    this.material = new Material(String.format("%s/%s_%s.png", path, String.valueOf(palette_x), String.valueOf(palette_y)));
    //this.material.createTitledTexture(palette_x,palette_y,mapReader.getTitlePixel(), mapReader.getTitlePixel());

    this.walkable = mapReader.isMapCellWalkable(map_x, map_y);

  }

  public Material getMaterial(){return material;}

  public boolean isWalkable(){return walkable;}

}
