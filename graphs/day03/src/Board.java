import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//                int fuckingdebuggernotworkinghowiwant = 500/3;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n; // size of board (one side length)
    public int[][] tiles; // how the tiles are set up

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal;// = {{}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        // set the board tiles and set the size
        tiles = b;
        n = tiles[0].length;

        // populate the goal state
        goal = new int[n][n];
        for (int i = 1; i < (n*n); i++){
            goal[(i-1)/n][(i-1)%n] = i;
        }
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        // TODO: Your code here
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        // TODO: Your code here

        // iterate through each element in 8 puzzle
        // figure out its coordinates
        // figure out coords of where it goes
        // compare
        // add that to a counter

        int totalDist = 0;
        for (int y = 0; y < n; y++){
            for (int x = 0; x < n; x++){
                int theNumber = tiles[y][x];
                if (theNumber != 0){
                    int desiredY = (theNumber-1)/n;
                    int desiredX = (theNumber-1)%n;
                    totalDist += (Math.abs(desiredY-y) + Math.abs(desiredX-x));
                }
            }
        }
        return totalDist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        // TODO: Your code here
        for (int y = 0; y < n; y++){
            for (int x = 0; x < n; x++){
                if (tiles[y][x] != goal[y][x]){
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // TODO: Your code here
        int inversions = 0;
        for (int i = 0; i < n*n; i++) {
            for (int j = i + 1; j < n*n; j++) {
                if (tiles[i/n][i%n] > tiles[j/n][j%n] && tiles[i/n][i%n] != 0 && tiles[j/n][j%n] != 0) inversions ++;
            }
        }
        return inversions % 2 == 0;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        int[] coords = new int[2];
        for (int y = 0; y < n; y++){
            for (int x = 0; x < n; x++){
                if (tiles[y][x] == 0){
                    coords[0] = x; coords[1] = y;
                }
            }
        }

        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int[] delCoords = new int[2];
        List<Board> boardList = new LinkedList<>();
        for (int[] direction : directions){
            delCoords[0] = coords[0] + direction[0];
            delCoords[1] = coords[1] + direction[1];
            if (0 <= delCoords[0] && delCoords[0] < n && 0 <= delCoords[1] && delCoords[1] < n){
                // make a new board that's the same as the old one
                Board newBoard = new Board(deepCopySquareArray(tiles));
                // do the change
                // set where zero is to the new number
                newBoard.tiles[coords[1]][coords[0]] = newBoard.tiles[delCoords[1]][delCoords[0]];
                newBoard.tiles[delCoords[1]][delCoords[0]] = 0;
                // add the new board to the boardList
                boardList.add(newBoard);
            }
        }
        return boardList;
    }

    private int[][] deepCopySquareArray(int[][] startArray){
        // because NO ONE at java headquarters thought, Oh hey, maybe someone might want a DEEP COPY of a multidimensional array?
        // what a crazy idea???????????????
        // only works with square arrays
        int[][] newArray = new int[startArray.length][startArray.length];
        for (int i = 0; i < startArray.length; i++){
            for (int j = 0; j < startArray.length; j++){
                newArray[i][j] = startArray[i][j];
            }
        }
        return newArray;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
