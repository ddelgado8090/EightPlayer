import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
/*
 * Solves the 8-Puzzle Game (can be generalized to n-Puzzle)
 */

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node firstNode, Node secondNode) {
        // Compare the heuristic values of the two nodes.
        if ((firstNode.getgvalue() + firstNode.gethvalue()) < (secondNode.getgvalue() + secondNode.gethvalue())) {
            return -1;
        } else if ((firstNode.getgvalue() + firstNode.gethvalue()) == (secondNode.getgvalue() + secondNode.gethvalue())) {
            return 0;
        } else if ((firstNode.getgvalue() + firstNode.gethvalue()) > (secondNode.getgvalue() + secondNode.gethvalue())){
            return 1;
        }
        return 0;
    }
};

public class EightPlayer {

    static Scanner scan = new Scanner(System.in);
    static int size=3; //size=3 for 8-Puzzle.
    static int numnodes; //number of nodes generated
    static int nummoves; //number of moves required to reach goal
   
   
    public static void main(String[] args)
    {  
        int numsolutions = 0;
       
        int boardchoice = getBoardChoice();
        int algchoice = getAlgChoice();
           
        //determine numiterations based on user's choices
        int numiterations = 0;
       
        if(boardchoice==0) {
            numiterations = 1;
        } else {
            switch (algchoice){
            case 0: 
                numiterations = 100;//BFS
                break;
            case 1: 
                numiterations = 1000;//A* with Manhattan Distance heuristic
                break;
            case 2: 
                numiterations = 1000;//A* with your new heuristic
                break;
            }
        }
       
   
       
        Node initNode;
       
        for(int i=0; i<numiterations; i++){
       
            if(boardchoice==0)
                initNode = getUserBoard();
            else
                initNode = generateInitialState();//create the random board for a new puzzle
           
            boolean result=false; //whether the algorithm returns a solution
           
            switch (algchoice){
                case 0: 
                    result = runBFS(initNode); //BFS
                    break;
                case 1: 
                    result = runAStar(initNode, 0); //A* with Manhattan Distance heuristic
                    break;
                case 2: 
                    result = runAStar(initNode, 1); //A* with your new heuristic
                    break;
            }
           
           
            //if the search returns a solution
            if(result){


               
                numsolutions++;
               
               
                System.out.println("Number of nodes generated to solve: " + numnodes);
                System.out.println("Number of moves to solve: " + nummoves);            
                System.out.println("Number of solutions so far: " + numsolutions);
                System.out.println("_______");      
               
            }
            else
                System.out.print(".");
           
        }//for

       
       
        System.out.println();
        System.out.println("Number of iterations: " +numiterations);
       
        if(numsolutions > 0){
            System.out.println("Average number of moves for "+numsolutions+" solutions: "+nummoves/numsolutions);
            System.out.println("Average number of nodes generated for "+numsolutions+" solutions: "+numnodes/numsolutions);
        }
        else
            System.out.println("No solutions in "+numiterations+" iterations.");
    }
   
   
    public static int getBoardChoice()
    {
       
        System.out.println("single(0) or multiple boards(1)");
        int choice = Integer.parseInt(scan.nextLine());
       
        return choice;
    }
   
    public static int getAlgChoice()
    {
       
        System.out.println("BFS(0) or A* Manhattan Distance(1) or A* Corner Priority(2)");
        int choice = Integer.parseInt(scan.nextLine());
       
        return choice;
    }

   
    public static Node getUserBoard()
    {
       
        System.out.println("Enter board: ex. 012345678");
        String stbd = scan.nextLine();
       
        int[][] board = new int[size][size];
       
        int k=0;
       
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                //System.out.println(stbd.charAt(k));
                board[i][j]= Integer.parseInt(stbd.substring(k, k+1));
                k++;
            }
        }
       
       
        // for(int i=0; i<board.length; i++){
        //     for(int j=0; j<board[0].length; j++){
        //         System.out.println(board[i][j]);
        //     }
        //     System.out.println();
        // }
       
       
        Node newNode = new Node(null,0, board);

        return newNode;
       
       
    }

   
   
   
    /**
     * Generates a new Node with the initial board
     */
    public static Node generateInitialState()
    {
        int[][] board = getNewBoard();
       
        Node newNode = new Node(null,0, board);

        return newNode;
    }
   
   
    /**
     * Creates a randomly filled board with numbers from 0 to 8.
     * The '0' represents the empty tile.
     */
    public static int[][] getNewBoard()
    {
       
        int[][] brd = new int[size][size];
        Random gen = new Random();
        int[] generated = new int[size*size];
        for(int i=0; i<generated.length; i++)
            generated[i] = -1;
       
        int count = 0;
       
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                int num = gen.nextInt(size*size);
               
                while(contains(generated, num)){
                    num = gen.nextInt(size*size);
                }
               
                generated[count] = num;
                count++;
                brd[i][j] = num;
            }
        }
       
        
        //Case 1: 12 moves
        brd[0][0] = 1;
        brd[0][1] = 3;
        brd[0][2] = 8;
       
        brd[1][0] = 7;
        brd[1][1] = 4;
        brd[1][2] = 2;
       
        brd[2][0] = 0;
        brd[2][1] = 6;
        brd[2][2] = 5;
        
       
        return brd;
       
    }
   
    /**
     * Helper method for getNewBoard()
     */
    public static boolean contains(int[] array, int x)
    {
        int i=0;
        while(i < array.length){
            if(array[i]==x)
                return true;
            i++;
        }
        return false;
    }
   
   
    /**
     * TO DO:
     * Prints out all the steps of the puzzle solution and sets the number of moves used to solve this board.
     */
    public static void printSolution(Node node) {
       
        /*TO DO*/
        //DOUBLE CHECK THIS
        //while the goal of the board order is false (so order is not reached)
		ArrayList<Node> listofSolutionPath = new ArrayList<Node>();

        //while there is a node, we add the node to the solution past array list. 
        //the node is now the parent
		while(node != null){
			listofSolutionPath.add(node);
			node = node.getparent();
		}

        //gets the node in the solution path and sets it as the current node 
		for (int i = listofSolutionPath.size() - 2; i >= 0; i--){
			Node cur_node = listofSolutionPath.get(i);
			System.out.println("Step " + (listofSolutionPath.size() - (i+1)) + ": "); 
			cur_node.print();
			nummoves++;
		}
		
    }
   
   
   
   
    /**
     * TO DO:
     * Runs Breadth First Search to find the goal state.
     * Return true if a solution is found; otherwise returns false.
     */
    public static boolean runBFS(Node initNode)
    {
        Queue<Node> Frontier = new LinkedList<Node>();
        ArrayList<Node> Explored = new ArrayList<Node>();
       
        Frontier.add(initNode);
        numnodes++;
        int maxDepth = 13;

        Node cur_state = null;
       
        /*TO DO*/

        while(!Frontier.isEmpty()){
            //remove first node from the frontier adn sets it as the current state
            cur_state = Frontier.remove();
            //add the current state to explored
            Explored.add(cur_state);
            if(cur_state.getdepth() < maxDepth){
                if (cur_state.isGoal()){ //If the current node we're looking at is the goal, then solution is found
                    System.out.println("Solution Found!");
                    printSolution(cur_state);
                    return true;
                } else {
                    ArrayList<int[][]> neighbor_list = cur_state.expand(); //this is the successor boards that are taken from the cur_state
                    ArrayList<Node> listOfSuccessors = new ArrayList<Node>(); // Changed the type to Node (this is the list of successors to check)

                    //For loop for creating a new node for every successor and putting them on the successors list
                    for (int i = 0; i < neighbor_list.size(); i++) {
                        Node neigh_node = new Node (null,0,neighbor_list.get(i));
                        listOfSuccessors.add(neigh_node);
                    }

                    //traverses through the successors list
                    for (int i = 0; i < listOfSuccessors.size(); i++) {
                        Node neighbor = listOfSuccessors.get(i);
                        neighbor.setparent(cur_state);
                        neighbor.setdepth(cur_state.getdepth() + 1); //increments the depth of node by 1
                        if(!Explored.contains(neighbor) && !Frontier.contains(neighbor)){
                            Frontier.add(neighbor);
                            numnodes++;
                        } 
                    }
                } 
            } 
        }
        System.out.println("No solution found");
        return false;
    }//BFS
   

    /***************************A* Code Starts Here ***************************/


    /**
     * TO DO:
     * Runs A* Search to find the goal state.
     * Return true if a solution is found; otherwise returns false.
     * heuristic = 0 for Manhattan Distance, heuristic = 1 for your new heuristic
     */
    public static boolean runAStar(Node initNode, int heuristic)
    {
        PriorityQueue<Node> Frontier = new PriorityQueue<Node>(new NodeComparator());
        ArrayList<Node> Explored = new ArrayList<Node>();
    
        initNode.setgvalue(0);
        if(heuristic == 0){
            initNode.sethvalue(initNode.evaluateHeuristic()); //Manhattan Distance heuristic
        } else if (heuristic == 1){
            initNode.sethvalue(initNode.evaluateHeuristic() + initNode.cornerHeuristic()); //Corner Priority heuristic
        }
    
        Frontier.add(initNode);
        numnodes++;
        
        int maxDepth = 13;
        
        //while the current state is not the goal and its depth is less than the max depth of 13 and the frontier is not empty 
        while (!Frontier.isEmpty()){
            Node X = Frontier.remove();
            Explored.add(X);
            //if the first node is the goal, that is the solution
            if(X.isGoal()){
                System.out.println("Solution Found!");
                printSolution(X);
                return true;
            } else {
                if(X.getgvalue() + 1 >= maxDepth){ //Checks to see if the check is reaching the max depth; return false if already over max depth
                    return false;
                } else {
                    ArrayList<int[][]> neighbor_list = X.expand();
                    for(int i = 0; i < neighbor_list.size(); i++){
                        Node child = new Node(X, X.getgvalue() + 1, 0, neighbor_list.get(i)); //Create a new node for every board that are expanded
                        if(heuristic == 0){//if heuristic is A*Manhattan Distance
                            child.sethvalue(child.evaluateHeuristic()); //Set h value of the successors using the evaluateHeuristic method
                        } else if (heuristic == 1){//if heuristic is A*CornerPriority+ManhattanDistance
                            child.sethvalue(child.evaluateHeuristic() + child.cornerHeuristic()); //Set h value of the successors using the evaluateHeuristic method
                        }
                        
                        if(!Explored.contains(child)){//if Explored doesn't contain the successor
                            if (!Frontier.contains(child)){//if Frontier doesn't contain the successor
                                Frontier.add(child);
                                numnodes++;
                            } else if (Frontier.contains(child)) {
                                // boolean nodeCheck = false;
                                // Node nodeHolder = null;
                                for(Node node : Frontier){//For every node (successor boards) in Frontier
                                    if(node.equals(child)){//If the current node we are traversing is equal to the successor and the current node has a lower f of n value than the successor
                                        if(heuristic == 0){
                                            if((child.getgvalue() + child.gethvalue()) < (node.getgvalue() + node.gethvalue())){ //Manhattan distance
                                                Frontier.remove(node);
                                                Frontier.add(child);
                                            }
                                        } else if (heuristic == 1){
                                            if((child.getgvalue() + child.gethvalue() + child.cornerHeuristic()) < (node.getgvalue() + node.gethvalue() + child.cornerHeuristic())){ //Manhattan Distance + cornerHeuristic
                                                Frontier.remove(node);
                                                Frontier.add(child);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("No solution!");
        return false;
    }
}