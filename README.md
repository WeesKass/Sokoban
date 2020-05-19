# Sokoban
Sokoban game (Pacman Theme)
Project Proposal: https://docs.google.com/document/d/1eyy7db9P98xubsREMJhf_5a3N34jLeF4XBunXLhl4Ho/edit?usp=sharing
Wiki: https://github.com/WeesKass/Sokoban/wiki

## Background
Recently I had an opportunity to get internship in the local company. 
We were told to create game for better understanding of the MCV(Model-View-Controller) pattern. 
It was a good expreience and test of my skills. 
Here my project comes form. I decided to create Sokoban since it is popular and entertaining game. 
Furthermore, I stylized it as pacman game.
  
## Project
Sokoban is a logical puzzle game, in which the player pushes boxes around in a maze according to the plan. 
The number of boxes (which are undistinguishable) varies from puzzle to puzzle, but there is always only one Sokoban. 
The goal is to get from a given initial situation to a situation in which all boxes reside on solution squares, by moving the Sokoban and the boxes.


## Feature list:
•	Swing (GUI widget toolkit for Java)
•	Text I/O - level loading from the text file, level format in txt file: 
1 – player (cannot point more than one player)
2 – wall
3 – box (ghost)
4 – position/destination where box has to be placed
•	Exceptions
•	8 Classes 


Project consists of 8 classes:
•	3 of them are responsible for MVC pattern:  
 Model.java – logic of the game
 Viewer.java. – Interface
 Controller.java-  processes the command, creates the corresponding Model and passes it for display to the Viewer
•	Main.java is the entry point to the program. The whole project is launched here.


•	4 auxiliary classes: 
1) LevelManager.java - logic for changing levels
2) Audio.java -  runs the second flow, music 
3) Canvas.java - graphics 
4) Parser.java – parses text from files and creates levels

  
	## The way it works:
Viewer class object is created in main method. While creating the object of Viewer class, controller is creating first. 
While creating the object of Controller class model is created and model passes reference to viewer. In Model the LevelManager and Audio classes’ objects are created. 
After level initialization (checking 1st level validity) music plays around. 
As creation of the model object finishes it returns to Controller, where controller is created, then returns to Viewer and object canvas is created.
Interface with all the working fields and buttons are addressed there: “restart” (shortcut “R”), “next”, “prev”, “turn on/off” the music, “search” buttons.
