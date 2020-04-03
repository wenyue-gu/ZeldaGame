## Implementation Plan
#### Genre
We are implementing Scrolling Platformer games, like Zelda and Super Mario Brothers. This game might also add dialogue feature and feature to interact with objects, so it also shares characteristics of RPG games. 

#### Work on part of the project
Mainly Model, might also help Controller/Data 

#### Extensions
- Artificial Player
- Model for save games & load games
- Backend for Dialogue feature 
- Backend for dynamic game content

#### Timeline
- Sprint 1: implement Model for the basis of this project, including the movement of the player/NPCs, the map(s), the life and attacking system, and the scoring system. 
- Sprint 2: implement more advanced features, including the player package/inventory, game loading/saving, Dialogue, artificial player, dynamic game content etc. 
- Complete: complete features that are not implemented due various reasons and refactor code if necessary; solve arising problems; aim for 3D game if allowed. 

## Design Plan 
#### Design and Architecture Goals
My design plan is to modualize the backend and uses interfaces/abstraction for each module. As described in DESIGN_PLAN.md, the backend is separated into characters(people), map, game elements, and other additional features that can be added to characters/game elements. Everything should follow the open/closed principle. Other parts of the project should call methods in the API to update information in Model. All concrete objects show in the game should come from an extension of abstract classes to make future extension possible. 

#### Project Overview 
This project uses the MVC model with an additional data management module. View is responsible for rendering UI components. It talks to Model with Java Listener and uses heads up display to update UI in real-time. Controller handles user inputs and updates Model information accordingly. Model stores information about game elements. It also notifies View whenever a piece of information is changed. Data management is responsible for storing information about games and loads/saves them. This information includes that of player(s), NPCs, game elements, map, etc. It communicates with View to load UI components at the beginning of the game, Controller to load a keyMap, and Model to load/save games information at appropriate times.

#### Two APIs in detail 
- *public interface Movable1D*: used by objects that move on a 1D line.
- *public interface Movable2D extends Movable1D*: holds an object that can move on both x and y directions.
- *public interface Jumpable*: holds object that jumps following physics rules  

These three APIs all provide service for an object to move, yet in different ways. *Movable1D* describes the movement of an object on the x direction. *Movable2D* describes a 2D movement (by looking from the top of the graph), which is an extension from *Movable1D*. *Jumpable* describes jumping. They are very extensible. (From the fact that *Movable2D* extends *Movable1D*, and a class can implement both *Movable1D* and *Jumpable*). Other team mates can use these interfaces to update the position of elements in the backend. 

#### Two Use Cases in Detail 
- *User presses a key (for example, right arrow) to make the player character move to the right*: Controller receives the signal that user presses a key, and calls player.setState(MOVE_TO_RIGHT). In the backend, updates its state, and notify the frontend that the X position has been changed. Frontend performs this state change. 
- *Player jumps*: The player presses a key so that makes the character jump. The controller detects the key press and calls player.jump(), which changes the player’s state to jump. The backend notifies the frontend and the frontend starts to animate. The backend waits a notification from the frontend to stop animation, and changes its state back to rest. 

#### An Alternative Design 
For moving the character, initially we thought about using player.moveInX(distance) method to move a small distance whenever the player presses a key. The frontend animates according to the new position of this player. For the current design, we simply set the state of this player to MOVE_TO_RIGHT. The frontend receives this state change and animates until the state is set back to REST/STOP. This is a relative easy implementation for backend and frontend, but the backend loses the positional information of game elements in the backend.  