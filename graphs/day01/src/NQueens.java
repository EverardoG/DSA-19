import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }

        return false;
    }

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        // make our list of boards for later
        List<char[][]> answerList = new ArrayList<>();

        // make a new board and populate it with '.'
        char[][] newBoard = new char[n][n];
        for (char[] row: newBoard){
            Arrays.fill(row, '.');
        }

        // make our unused Xs and Ys
        List<Integer> unusedXs = new LinkedList<>();
        List<Integer> unusedYs = new LinkedList<>();
        for (int i = 0; i < n; i++){
            unusedXs.add(i);
            unusedYs.add(i);
        }

        // explore all possible solutions
        nQueensHelper(newBoard, unusedXs, answerList, 0);

        // tell us what you found!
        return answerList;
    }

    private static void nQueensHelper(char[][] currentBoard, List<Integer> unusedXs, List<char[][]> answerList, int depth){
        // base case -> all rows and columns are covered
        if (unusedXs.isEmpty()){
            answerList.add(copyOf(currentBoard));
        }

        // iterate through cols at each level of recursion (row)
            for (Integer x : unusedXs){
                if (!checkDiagonal(currentBoard, depth, x)){
                    // place the queen and update the unused xs and ys
                    currentBoard[depth][x] = 'Q';

                    ArrayList<Integer> unusedXsNew = new ArrayList<>(unusedXs);
                    unusedXsNew.remove(x);

                    // explore the space further, but only in available rows and cols
                    nQueensHelper(currentBoard, unusedXsNew, answerList, depth+1);

                    // put everything back like you found it
                    currentBoard[depth][x] = '.';
                }
            }

    }

// anything past here is probably garbage
    public static boolean checkDiagonalBETTER(char[][] board, int r, int c) {
//        System.out.println("------------------------------------");
//        System.out.println(Arrays.toString(board[0])); System.out.println(Arrays.toString(board[1])); System.out.println(Arrays.toString(board[2])); System.out.println(Arrays.toString(board[3]));
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') { return true;}
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q' ){return true;}
            x++;
            y--;
        }
        y = r + 1;
        x = c + 1;
        while (y < board[0].length && x < board[0].length) {
            if (board[y][x] == 'Q') { return true;}
            x++;
            y++;
        }
        y = r + 1;
        x = c - 1;
        while (y < board[0].length && x >=0) {
            if (board[y][x] == 'Q') { return true;}
            x--;
            y++;
        }
        return false;
    }
//    public static List<char[][]> nQueensSolutionsBAD(int n) {
//        // TODO
//        List<char[][]> answerList = new ArrayList<>();
//        char[][] newBoard = new char[n][n];
//        // fill board with '.'
//        for (char[] row: newBoard){
//            Arrays.fill(row, '.');
//        }
//        nQueensHelperBAD(newBoard, new boolean[n], new boolean[n], answerList);
//        return answerList;
//    }
//
//    /** INPUTS:
//     array - 1D array of booleans
//     returns true if all boolean values in array are true
//     */
//    public static boolean areAllTrue(boolean[] array){
//        for (boolean b : array) if(!b) return false;
//        return true;
//    }
//
//    /** INPUTS:
//    // answers - list of 2d arrays representing a list of all valid chess boards
//    // n - finding solutions for an n by n board
//     */
//    public static void nQueensHelperBAD(char[][] currentBoard ,boolean[] usedYs, boolean[] usedXs, List<char[][]> answerList){
//        // base case - this is a complete and valid N Queens solution -> add it to the answer list
//        if (areAllTrue(usedYs)){
//            answerList.add(copyOf(currentBoard));
//        }
//
//        // recursive case
//        for (int x = 0; x < usedXs.length; x++){
//            for (int y = 0; y < usedYs.length; y++){
//                if (!checkDiagonal(currentBoard, y, x) && !usedXs[x] && !usedYs[y]){
//                    currentBoard[y][x] = 'Q';
//                    usedXs[x] = true; usedYs[y] = true;
//                    nQueensHelperBAD(currentBoard, usedYs, usedXs, answerList);
//                    currentBoard[y][x] = '.';
//                    usedXs[x] = false; usedYs[y] = false;
//                }
//            }
//
//        }
//    }
}
