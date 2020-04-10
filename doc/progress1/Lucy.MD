## Implementation Plan
#### Genre
We are implementing Scrolling Platformer games. Our main focus is on Zelda but should also be 
able to support Mario features (side-viewed instead of top viewed). It also shares characteristics 
RPG games

#### Work on part of the project
Controller and Driver

#### Extensions
- Loading preference 
- Saving settings 
- Multiple player 

#### Timeline
- Sprint 1: provide skeleton for control API, write main and control to functional extend connecting player input to model/view
- Sprint 2: implement control for features added in second sprint, such as control for inventory, artificial player, etc
- Complete: complete implementations that support all back and front end final features

## Design Plan 
#### Design and Architecture Goals
My design plan is to modualize the backend and uses interfaces/abstraction for each module. As described in DESIGN_PLAN.md, the backend is separated into characters(people), map, game elements, and other additional features that can be added to characters/game elements. Everything should follow the open/closed principle. Other parts of the project should call methods in the API to update information in Model. All concrete objects show in the game should come from an extension of abstract classes to make future extension possible. 

#### Project Overview 
This project uses the MVC model with an additional data management module. View is responsible for rendering UI components. It talks to Model with Java Listener and uses heads up display to update UI in real-time. Controller handles user inputs and updates Model information accordingly. Model stores information about game elements. It also notifies View whenever a piece of information is changed. Data management is responsible for storing information about games and loads/saves them. This information includes that of player(s), NPCs, game elements, map, etc. It communicates with View to load UI components at the beginning of the game, Controller to load a keyMap, and Model to load/save games information at appropriate times.

#### Two APIs in detail 
- *public interface ZeldaControlInterface*: supposedly used by all controllers except window
    * Has an update method that is used by all controller to update the content of model each frame
- *public interface PlayerControlInterface extends ZeldaControlInterface*: used by controllers that 
 connect with the backend player model 
    * Has a keyInput method 
 

#### Two Use Cases in Detail 
- Start Game: In menu page, user clicks "Start" button. WindowControl detects that button has been clicked, calls startgame method, which creates the GameController and switch the scene from menu view to the playing field/game view and starts the animation timer. 

- User presses key to go left: Scene detects key pressed, whose event connects to MainController.keyInput. MainController.keyInput passes the argument to MainPlayerController, which passes to argument to either ZeldaPlayerControl or MarioPlayerControl depending on the type of the game. Method “left” in either player control is called, which calls backend player model’s moveInX(-1) method, moving the player left one unit. The amount moved (“-1”) can be changed through preference setting (?) 


#### An Alternative Design 
There was initial debates on what the controller holds and what it actually updates. For example, 
currently we have the model created outside the controller and pass the model in, though initially 
there had been talks of having the controller create both the view and model inside its constructor. 
There was also discussion for the controller to update both the view and model. However, we ended 
up deciding that front end can have listeners that bind to the backend models and directly update 
as any backend state change. Controller will mainly be responsible for detecting user key input and 
relating them to the backend.