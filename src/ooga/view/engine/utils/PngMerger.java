package ooga.view.engine.utils;

import static ooga.view.engine.utils.ImageLoader.loadImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class PngMerger {
  public PngMerger(){}

  public static String mergePngPics(String dir, List<String> paths, String newpath)
      throws IOException {

    List<String> fullPaths = new ArrayList<>();
    for (String path:paths){
      fullPaths.add(String.format("%s/%s", dir, path));
    }

    //System.out.println(fullPaths.get(0));
    BufferedImage combined = new BufferedImage(ImageLoader.getImageWidth(fullPaths.get(0)), ImageLoader.getImageHeight(fullPaths.get(0)), BufferedImage.TYPE_INT_ARGB);

    Graphics g = combined.getGraphics();
    for(String path:fullPaths){
      BufferedImage layer = ImageLoader.loadImage(path);
      g.drawImage(layer, 0, 0, null);
    }

    g.dispose();

    String newImagePath = String.format("resources/%s/%s", dir, newpath);
    System.out.println(newImagePath);
    File outputfile = new File(newImagePath);
    ImageIO.write(combined, "png", outputfile);

    return newImagePath;
  }

}
