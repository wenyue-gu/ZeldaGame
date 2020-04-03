## Implementation Plan
#### Genre
We work on games like Zelda and Super Mario Brothers games. It's also similar to RPG games due to the addition of dialogue and character interaction. 

#### Work on part of the project
Data management and colaborate on working on the interaction with frontend, backend, and controller.

#### Extensions
I will work on those extensions by providing data loading and storing support:
- Save Games to the data files
- load games
- Preferences setting
- Dynamic Game Content
- Keep track of games' high scores

#### Timeline
- Sprint 1: Storing game data in simple Json file. Support basic functionalities such as loading maps, loading icons, and loading texts every game level. 

- Sprint 2: Support new data that are requested by the new features. Use efficient data structure in data storage. Handle Basic error checking. 

- Complete: aim for employing resource managers in data management and placing data onto the web if the situation allows. Might be used for refactoring or supporting 3D feature instead as well.

## Design Plan 
#### Design and Architecture Goals 
The category of data being stored is open and data management will accomadate if more new categories of data are coming up. The mechanism of storing data and how the current data are stored are closed and indpendent to further changes. I hope to follow the open-closed principle so that the data management will be ready to handle when more complex, diversed, and larger volumn of data are added to support additional future features. In addition, I want to keep the data manangement process efficient by employing efficient data strcture.

#### Project Overview （shared among the whole group)
Components: Model, View, Controller, Data Management.

View : visualisation and UI components. Communicate with Model with Java Listener. 

Controller: handles user inputs and updates Model information accordingly. Model stores information about game elements. It also notifies View whenever a piece of information is changed. 

Data management : responsible for storing information about games and loads/saves them. This information includes that of player(s), NPCs, game elements, map, etc. It communicates with View to load UI components at the beginning of the game, Controller to load a keyMap, and Model to load/save games information at appropriate times.

#### Two APIs in detail 
- *DataLoaderAPI*: used by classes to load data into the data center.

Service: provide other classes to load and receive data from the data center.

 It's open to adding more methods. In addition, new loading methods in the future can be the linear combination of some of the simple loading functions such as loadtext, loadInt, and loadImage. Those simple methods have a Category filed categorizes different values. I make objects extend interfaces or abstract classes so that those objects will be extensible for different uses in more complex situations. Reading code will be easier if I name the methods well and name the arguments of general methods well.

- *DataStorerAPI*: used by classes to store data into the data center.


Service: provide other classes to store data from the data center.

It's open to adding more methods. In addition, new storing methods in the future can be the linear combination of some of the simple storing functions such as storetext, storeInt, and storeImage. Those simple methods have a Category filed that categorizes different values. I make objects extend interfaces or abstract classes so that those objects will be extensible for different uses in more complex situations. Reading code will be easier if I name the methods well and name the arguments of general methods well.


#### Two Use Cases in Detail 
- *Save Game Map to Data Center*: After controller sensing user's intention to save the Game map, it will call on the Model, which will save the data by calling storeMap(Collection<Cell> map); on the data loader. Data loader first will convert the data into a GameMapGraph and then Gson will convert GameMapGraph into Json data file.  

- *Error checking when nonexisting player is requested to be loaded* : When data are loaded through dataLoader, data Loader will check the validity of the data. For example, in this case, data loader checks whether the ID corresponding to the character exists. If the data is invalid, it will report error and throws an exception, which will be picked up by the controller and displayed to the User interface if needed.

#### An Alternative Design 
When I was storing the map, I have thought of using the 2D array with each of the element being an integer indicating the type of the cell instead of using a cell object. However, this strategy isn't flexible enough because a cell may hold more data as the game gets complicated. For example, it might also need to hold data to know whether it has some special effect. 