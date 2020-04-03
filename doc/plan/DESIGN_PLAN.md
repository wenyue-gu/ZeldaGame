## Introduction 
For this final assignment, our team is going to design a project that supports different kinds of  Scrolling Platformer games, like Zelda and Super Mario Brothers. The main goal of this project is making it easy to load games of the same genre without changing too much of the code. Ideally users should be able to play a different kind game by loading only configuration files.   
This project uses the MVC model with an additional data management module. View is responsible for rendering UI components. It talks to Model and uses heads up display to update UI in real-time. Controller handles user inputs and updates Model information accordingly. Model stores information about game elements. It also notifies View whenever a piece of information is changed. Data management is responsible for storing information about games and loads/saves them. This information includes that of player(s), NPCs, map, etc. It communicates with the Model to load/save games at appropriate times.

## Overview
### View
#### Game Menu
_GameMenuView_ extends _JavaFX.Scene_: generates the menu
_GameMenuController(GameMenuView)_: adds event handlers to the buttons in game menu

#### Game View
##### Game State View(JavaFX.Scene):
abstract _GameStateController_: implements the overall control of game state. It initializes the main view for the game state in its own initialization phasse.
abstract _GameStateView_() extends _JavaFX.scene_: defines the basic behaviors of the main view method. This should be the only method public to the rest of the application.
##### Map View:
abstract _MapController_(boolean fixed): implements the listeners to backend. It initializes the main view for the game state in its own initialization phasse. If _fixed_ is true, thus the construction of the map remains the same throughout the game.
abstract _MapView_: defines the basic behaviors of visualizing the map

##### Agent View:
abstract _AgentController_: mainly implements the agent listener to the backend and manage the communication between the rest of the application with its corresponding AgentView. It initializes the main view for the game state in its own initialization phasse.
abstract _AgentView_: defines the basic behaviors of Agent View
###### Playable Agent View
abstract _PlayableAgentController_ extends AgentController
abstract _PlayableAgentView_ extends AgentView

###### Non-playable Agent View
abstract _NonPlayableAgentController_ extends AgentController
abstract _NonPlayableAgentView_ extends AgentView

##### Object View:
abstract _ObjectController_: mainly implements the agent listener to the backend and manage the communication between the rest of the application with its corresponding AgentView. It initializes the main view for the game state in its own initialization phasse.
abstract _ObjectView_: defines the basic behaviors of Object View

###### Interactive Object View:
abstract _InteractiveController_ extends ObjectController
abstract _InteractiveView_ extends ObjectView

###### Non-interactive Object View:
abstract _NonInteractiveObjectController_ extends ObjectController
abstract _NonInteractiveObjectView_ extends ObjectView

##### Animation:
abstract _StateAnimation_: defines the basic behaviors of constructing animation (oject or agent)

##### Panels:
###### Game Panel(JavaFX.Panel)
abstract _GamePanelView_ extends JavaFX.Panel: implements the game panel
abstract _GamePanelController_: implements the listener bidding

####### Single Player Panel (Game Panel)
SingleGamePanelView(JavaFX.Panel)
SingleGamePanelController(int PlayableAgentID)

####### Multiple Player Panel (Game Panel)
MultipleGamePanelView(JavaFX.Panel)
MultipleGamePanelController(List<int> PlayableAgentIDs)

###### Dialogue Panel:
DialogPanel: generate dialogue panel
DialogPref: factory class to determine the preferences for the dialogue

###### Inventory Panel(JavaFx.Panel):
_Grid_: implements the composing grid of the inventory
InventoryPanel(JavaFX.Panel): generates the inventory view

### Model
Model is divided into several sections for different types of game elements. All interfaces are in src/ooga/model/interfaces as a reference of APIs: 
#### Player and Character (src/ooga/model/characters): 
Different types of players are supported in this project. MarioPlayer and MarioCharacter are able to move in a line and jump (implement Jumpable) whereas ZeldaCharacter and ZeldaPlayer are more like characters in a RPG game that can move in both x and y directions (implement Movable2D). They store player information including its moving state, attacking state, weapon, score, HP, etc.  

- *public interface Movable1D*: used by objects that move on a 1D line.
- *public interface Movable2D extends Movable1D*: holds an object that can move on both x and y directions.
- *public interface Jumpable*: holds object that jumps following physics rules  

#### Map (src/ooga/model/interfaces/gameMap): 
The game map is implemented to have three levels - cell, grid, and gameMap. Cell is a coordinate of a grid that stores information at specific locations. Grid is a map that is the size of a window. GameMap, at last, is the entire map of this game that consists of multiple grids. 
- *public interface Cell*: stores the information of a single cell.
public interface SingleGrid: creates a single grid that is made up of {@link Cell}
- *public interface GridInMap extends SingleGrid*: allows a map to contain multiple grids. IDs of grids around the current grid are recorded.
- *public interface GameMap*: the whole map for the game. It consists of many {@link GridInMap} and talk to other parts of the project.

#### Game Elements (src/ooga/model/gameElements): 
All game elements extend from the parent class Element (which implements InteractiveElements, an API). Game elements can implement InteractiveElement to have a state indicating whether it is activated (or is interacting with the player). 
- *public abstract class Element implements Movable1D*: an abstract class for all game elements, whether interacting with players or not 
- *public interface InteractiveElements*: This interface records all game elements that can interact with players/NPCs

#### Other classes (src/ooga/model/interfaces): 
There are other APIs that describe the state/possessions of character. These APIs include Alive (for HP), Attackr (for attacking), Inventory (for possessions), and Scroable (for Scoring)
- *public interface Alive*: Objects that implement this interface have certain HP values
- *public interface Attacker*: bjects that implement this interface are allowed to attack other objects in different ways
- *public interface Inventory* : stores information of other objects that a player can take
- *public interface Scorable* : helps players keep track of scores

### Controller
- MainController
- PlayerController
- InventoryController

### Data Management (Guangyu)

## Design Details
### View
#### Game Menu
_GameMenuView_ extends _JavaFX.Scene_: generates the menu
- JavaFX.Node getMenuView: returns the menu
- JavaFX.Button getNewGameButton: returns the new game button
- JavaFX.Button getExitGameButton: returns the exit game button
_GameMenuController(GameMenuView)_: adds event handlers to the buttons in game menu

#### Game View
##### Game State View(JavaFX.Scene):
abstract _GameStateController_: implements the overall control of game state. It initializes the main view for the game state in its own initialization phasse.
- void getGameStateView(): gets the main view for the game state
- void update(): calls the game state to update accordingly to the model
abstract _GameStateView_() extends _JavaFX.scene_: defines the basic behaviors of the main view method. This should be the only method public to the rest of the application.

- void update(): calls each component to update
- MapView getMapView(): gets the generated map view (needs to call getView() to retrieve the actual visualization components)
- List<PlayableAgentView> getPlayableAgents(): gets the generated view for the playable agents
- List<NonPlayableAgentView> getPlayableAgents(): gets the generated view for the non-playable agents
- List<InteractiveObjectView> getInterObjects(): gets the generated view for the interactive objects
- List<NonInteractiveObjectView> getNonInterObjects(): gets the generated view for the non-interactive objects
- GamePanel getGamePanel(): gets the game panel

##### Map View:
abstract _MapController_(boolean fixed): implements the listeners to backend. It initializes the main view for the game state in its own initialization phasse. If _fixed_ is true, thus the construction of the map remains the same throughout the game.
- MapView getMapView(): sets the view object
- void update(): updates the map (only when _fixed_ is false)

abstract _MapView_: defines the basic behaviors of visualizing the map
- getView(): returns the view object that renders the map
- void update(): updates the view

##### Agent View:
abstract _AgentController_: mainly implements the agent listener to the backend and manage the communication between the rest of the application with its corresponding AgentView. It initializes the main view for the game state in its own initialization phasse.
- AgentView getAgentView(): gets the agent view
- void update(): update the agent
- void addStateAnimation(int ID, AnimationAgentState animation): calls the corresponding method in AgentView.
- void getStateAnimationMap(Map<int, AnimationAgentState> animationStateMap): calls the corresponding method in AgentView.
- void setAgentState(int ID): calls the corresponding method in AgentView.
- int getAnimatingState(): calls the corresponding method in AgentView.

abstract _AgentView_: defines the basic behaviors of Agent View
- getView(): returns the view object _(debating between Javafx animation timeline object or something else)_ that renders the map
- void addStateAnimation(int ID, StateAnimation animation): add state animation with its corresponding id.
- void getStateAnimationMap(Map<int, StateAnimation> animationStateMap): retrieves the state map for the convienience of changing multiple states at one operation
- void setAgentState(int ID): allows the manual changing of the current agent state
- int getAnimatingState(): returns the state id being animated

###### Playable Agent View
abstract _PlayableAgentController_ extends AgentController
abstract _PlayableAgentView_ extends AgentView

###### Non-playable Agent View
abstract _NonPlayableAgentController_ extends AgentController
abstract _NonPlayableAgentView_ extends AgentView

##### Object View:
abstract _ObjectController_: mainly implements the agent listener to the backend and manage the communication between the rest of the application with its corresponding AgentView. It initializes the main view for the game state in its own initialization phasse.
- ObjectView getObjectView(): gets the object view
- void update(): update the object
- addStateAnimation(int ID, StateAnimation animation): calls the corresponding method in ObjectView.
- getStateAnimationMap(Map<int, StateAnimation> animationStateMap): calls the corresponding method in ObjectView.
- setObjectState(int ID): calls the corresponding method in ObjectView.
- int getAnimatingState(): calls the corresponding method in ObjectView.

abstract _ObjectView_: defines the basic behaviors of Object View
- getObjectView(): returns the view object _(debating between Javafx animation timeline object or something else)_ that renders the map
- addStateAnimation(int ID, StateAnimation animation): add state animation with its corresponding id.
- getStateAnimationMap(Map<int, StateAnimation> animationStateMap): retrieves the state map for the convienience of changing multiple states at one operation
- setViewState(int ID): allows the manual changing of the current agent state
- int getAnimatingState(): returns the state id being animated

###### Interactive Object View:
abstract _InteractiveController_ extends ObjectController
abstract _InteractiveView_ extends ObjectView

###### Non-interactive Object View:
abstract _NonInteractiveObjectController_ extends ObjectController
abstract _NonInteractiveObjectView_ extends ObjectView

##### Animation:
abstract _StateAnimation_: defines the basic behaviors of constructing animation (oject or agent)
- setSubject(): sets the subject to be animated
- getStateAnimation(): gets the animation timeline

##### Panels:

###### Game Panel(JavaFX.Panel)
abstract _GamePanelView_ extends JavaFX.Panel: implements the game panel
- void update(): updates the panel (e.g. after the player consumes an object)

abstract _GamePanelController_: implements the listener bidding

####### Single Player Panel (Game Panel)
SingleGamePanelView(JavaFX.Panel)
SingleGamePanelController(int PlayableAgentID)

####### Multiple Player Panel (Game Panel)
MultipleGamePanelView(JavaFX.Panel)
MultipleGamePanelController(List<int> PlayableAgentIDs)

###### Dialogue Panel:
DialogPanel: generate dialogue panel
- Pane getDialogPanel()
- void update(String text, DialogPref pref): update the text and the dialogue preference

DialogPref: factory class to determine the preferences for the dialogue
- setSound(): set the voice audio if any
- setSpeaker(): set who is speaking (to determine the text color and etc)
- getSound(): gets the voice audio
- getTextColor(): gets the text color
- getTextFont(): gets the text font if any

###### Inventory Panel(JavaFx.Panel):

_Grid_: implements the composing grid of the inventory
- void containItem(ObjectView): adds a new item
- void deleteItem: deletes the contained item

InventoryPanel(JavaFX.Panel): generates the inventory view
- void display(int PlayableAgentID): display the view of the player under the provided ID
- void update(int PlayableAgentID): updates the inventory (e.g. after the player uses an item)
- boolean isDisplayed(int PlayableAgentID): returns true if the inventory for the player under the provided ID is being displayed
- void close(): closes the display of the inventory

### Model
Although Model has many classes for different objects, there is a large class ```Model.java```  that loads and creates all elements at the beginning of the game. Controller keeps a copy of Model. Model APIs are mostly used by Controller. When Controller receives a user input and needs to update Model information based on that, it calls methods from Model APIs. Some of these examples can be ```player.setState(state)```, or ```player.jump()```.  
Model communicates with View using Java LIstener. Since we want to achieve HUD, Model needs to notify View whenever any model state changes. Model also has a listener that listens to the state from the frontend so that it can disable state changes when View is animating.  
This design is very extensible since adding more APIs in the backend by request will not affect code in other modules. Also since a lot of abstractions are used, it is easy to add another property/class/interface for another feature. For example, if a dialogue feature will be added in the future, another API that allows loading dialogues from the data and keeping track of the dialogue can be made. Controller does not need to be changed. The communication between Model and View is made through Listener so that adding more modules only requires the backend to notify the frontend more often. 

#### Controller
The controller package will contain one MenuController and one GameController. Menu Controller takes care of the buttons on the initial menu screen and starts the game. GameController holds various smaller controllers that takes in user input. Every frame, each controller under Game Controller will call its update method that updates the backend components (since front end directly binds to backend state changes, controller would not need to individually update view components). Every time user input is detected, corresponding controller will call its keyInput method that updates the backend component.  
In order to maximize the extensibility, the design of view is structured to include an abstract API: Game View, in which the specific 2D and 3D implementation would follow the basic structure and build upon it. The specific composing parts of the Game View also follows the inheritance structure to maximize its consistency and extensibility at the same time. In addition to the Game View API, Game Menu API implements the generation of the game menu where users could interact by clicking different buttons to control the game.

#### Data Management (Guangyu)

## Example Games
#### Zelda RPG
The RPG game provides a two dimensional top-viewed playing field where users could move in four directions, North, East, South and West. An advanced version would include enemies and NPC.   
This example explores the possibility of using the design structure to realize a complex gameplay, enabling the user to view and operate the map from top view. With a potentionality of expanding it to 3D, it helps to demonstrate the flexibility of our design in both front-end and back-end on how to handle rich funcionalities.

#### Multiplayer Zelda RPG
The multiplayer game provides a gaming platform that allows simultaneous gameplay of multiple users. An advanced version would allow multiple players to operate on different network hosts, having a server (local host in the beta version) to process the gameplay.  
Multiplayer version requires flexibility in front-end, back-end and controller on extending to include more players. If the design structure is indeed flexible, the added code to achieve the multiplayer game would be minimal.

#### Open-world Zelda
Open-worldt allows users to wander freely on a randomly generated map. Without the limitation of a pre-configured map, the fun would be different each time.   
We believe this feature significantlt challenge how backend manages the generation of map content, and also challenge how front-end effectively use the limited memory to achieve the high-quality visualization.

## Design Considerations
- The way we implemented the MVC structure, the View component, using listener, would be able to directly bind to changes in backend data and react accordingly, not passing through the Controller in the process. There was slight confusion regarding this implementation when first proposed, as many of us thought that backend and frontend components should not touch each other at all, but after looking through a previous reading regarding MVC implementation, we agreed that this implementation would make things easier for both the controller and view.
- There was extensive discussion regarding whether the different player classes need to have an abstract overarching player class. Since mario player (1D movement but with direction “Up”) and zelda player (2D movement, no jump option) behaves very differently we will be having two different player classes to account for. One option discussed was to have these classes have an overarching abstract player superclass, and have the singular playerControl hold a “player” object, whose actual initialization would be decided upon the selection of game. However, this could be undesirable since each time we call a method specific to the different player subclass, it would require downcasting and perhaps more if-else trees regarding the game type. We resolved to having multiple player controllers, one for each possible character, and have a main player control chooses which controller to use upon receiving note about the game type. 
