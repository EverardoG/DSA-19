import java.util.HashMap;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // TODO

            // make a hashmap of visited vertices (already seen vertices)
            // make a priority queue of minQ (minimum cost)
            // initialize addedvertices to 0
            // initialize cost to 0

            HashMap<Integer, Integer> visited = new HashMap<>();
            IndexPQ<Integer> minQ = new IndexPQ<>(G.numberOfV()); // key is cost
            int addedV = 0;
            int currentV = 0;
            int cost = 0;

            // while addedvertices != g.numberofV() // while we still haven't gotten all vertices
                // for each neighbor of a vertex
                    // if it's visited and the key is less than what's in the PQ, change it
                    // elif it's not visited, put it in the PQ and in visited

                // pop off the lowest key in PQ
                // add that key to the cost
                // add 1 to the addedverticies
                // set a new vertex to check neighbors for

            visited.put(0, 0);
            addedV += 1;
            while (addedV < G.numberOfV()){ // O(v)
                for (Edge neighborEdge : G.edges(currentV)){ // O(e/v)
                    int neighborV = neighborEdge.other(currentV);
                    if (minQ.contains(neighborV) && visited.containsKey(neighborV) && (neighborEdge.weight() < visited.get(neighborV))){ // contains may be O(v) if minQ is full of things
                        minQ.changeKey(neighborV, neighborEdge.weight());
                        visited.put(neighborV, neighborEdge.weight());
                    }

                    if (minQ.contains(currentV) && visited.containsKey(currentV) && (neighborEdge.weight() < visited.get(currentV))){
                        minQ.changeKey(currentV, neighborEdge.weight());
                        visited.put(currentV, neighborEdge.weight());
                    }

                    else if (!visited.containsKey(neighborV)){
                        minQ.insert(neighborV, neighborEdge.weight());
                        visited.put(neighborV, neighborEdge.weight());
                    }

                    // potentially need else if clause for the currentV
                }

                int minKey = minQ.delMin();
                cost += visited.get(minKey);
                addedV += 1;
                currentV += 1;

            }

            return cost;
        }

    }

