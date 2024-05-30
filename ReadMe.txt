Name: Johnrev Bermudez and Daniela Delgado Files needed to run: EightPlayer.java; node.java All group members were present and contributing during all work on this project. We have neither given nor received unauthorized help on this assigment.

Known bugs:

Case Num of Moves Num of nodes generated

                                              BFS         A*(Manhattan Distance)       A* <Own heuristic>(Corner Heuristic + Manhattan Distance)
1 12 2216 40 37 2 2 14 5 5 3 3 29 10 10 4 5 67 10 10 5 8 294 20 17 6 10 1062 39 29 7 No solution No Solution No solution No solution

Average N/A 526 17.71 15.43 for all iterations

Corner Heuristic Description: In our corner heuristic, we calculated how many corner numbers are misplaced and incremented a variable that would then be added to the Manhattan distance heuristic. For example, if "1" or "3" or "8" or "0" is in the wrong tile, then we add one to the heuristic value

External Sources: Professor Christman, Smith Gakuya, Alec Girond, StackOverflow, Geeks4Geeks