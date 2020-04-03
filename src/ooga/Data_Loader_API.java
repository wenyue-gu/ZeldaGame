package ooga;

import javax.swing.text.html.ImageView;
import java.util.List;
import java.util.Map;

/**
 * The interface for game loader
 */
public interface Data_Loader_API {
    /**
     * Load the map of single game level
     * @param levelName
     * @return
     */
    GameMapGraph loadLevel(double levelName);

    /**
     * load text files from the database. Same concern as loadGameIcon.
     * @return
     */
    List<String> loadText();

    /**
     * Load the game's status and string is the name of the status while integer is the value
     * @return
     */
    Map<String, Integer> loadGameStatus();

    /**
     * I am concerned about this way because list will not tell you which imageView is which.
     * @return list of icons
     */
    List<ImageView> loadGameIcon();

    /**
     * Load the player's preference and string is the name of the preference while integer is the value
     * @return
     */
    Map<String, Integer>  loadPlayerPreference();

}
