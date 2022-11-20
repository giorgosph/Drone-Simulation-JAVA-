# Drone Simulation

### About
This is a project from the second year of the Computer Science course, in JAVA. The project represents a console version of iconic drones(D) that are displayed in the console, and is implemented in Eclipse. The user can interact to add, move drones, save, load state, etc. You can **observe the results** by watching the "demo.mp4" video that is provided with the code. The code is fully commented and implemented originally.  This idea is then implemented in a GUI version as a small game and can be found in the "Drone Simulation GUI" project.

### Methodology
The code follows an Object Oriented Programming design, using classes, interfaces, and more. Most of the concept is explained by the comments in the code. The drones are displayed in an arena with predefined dimensions and they can move around the arena. For the movement, the drones check if there is available space in the directions they are currently moving, to detect collision with other drones or a wall. In case there is no available space the drone changes direction following the North-East-South-West pattern. When a new drone is added, its initial position is randomly chosen, and it checks for available space again. The current state of the arena can be saved to a file, and can then be uploaded back.
