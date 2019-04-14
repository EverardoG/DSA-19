/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState; // this is the current state the solver is in
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = board.manhattan() + moves;
        }

        public int compareTo(State s){
            return cost - s.cost;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here
        State rootState = state;
        while (rootState.prev != null){
            rootState = rootState.prev;
        }
        return rootState;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // TODO: Your code here
        solutionState = new State(initial, 0, null);
        if (!initial.solvable()){
            return;
        }
        HashMap<Board, Integer> v = new HashMap<>();
        v.put(solutionState.board, 0);
        PriorityQueue<State> q = new PriorityQueue<>(State::compareTo);
        q.add(solutionState);

        while (!q.isEmpty() && !solved){
            State currentState = q.poll();

            // base case - congrats!
            if (currentState.board.isGoal()) {
                solutionState = currentState;
                solved = true;
                minMoves = solutionState.moves;
            }

            // loop through your neighbors
            for (Board board : currentState.board.neighbors()){
                if (!v.containsKey(board) || currentState.moves + 1 < v.get(board)){
                    v.put(board, currentState.moves + 1);
                    q.add(new State(board, currentState.moves + 1, currentState));
                }
            }

        }

    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        // TODO: Your code here
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        // TODO: Your code here
        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
