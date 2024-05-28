package org.example;

import java.util.ArrayList;

/**
 * Lab4, represents a graph.
 * It contains vertices represented as Strings,
 * edges represented using adjacency matrix,
 * and if the graph is directed or not.
 * Performs depth-first search and breadth-first search.
 *
 * @author Towa Quimbayo, A01086002, Set U
 */
public class Graph {
    private final String[] vertexLabels;
    private final int[][] edges;
    private boolean isDirected;
    private ArrayList<String> dfsOrder;
    private ArrayList<String> dfsDeadEndOrder;
    private ArrayList<String> bfsOrder;

    /**
     * Constructor for Graph.
     * @param vertexLabels array of strings, the vertices of the graph
     * @param isDirected   boolean, true if the graph is directed, false otherwise
     */
    public Graph(String[] vertexLabels, boolean isDirected) {
        this.vertexLabels = vertexLabels;
        this.edges = new int[vertexLabels.length][vertexLabels.length];
        this.isDirected = isDirected;
    }

    /**
     * Getter for isDirected.
     * @return boolean, true if the graph is directed, false otherwise
     */
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Setter for isDirected.
     * @param isDirected boolean, true if the graph is directed, false otherwise
     */
    public void setDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     * Adds an edge from vertexA to vertexB.
     * @param vertexA for first vertex
     * @param vertexB for second vertex
     */
    public void addEdge(String vertexA, String vertexB) {
        int vertexAIndex = -1;
        int vertexBIndex = -1;

        // Find the index of the vertices
        for (int i = 0; i < vertexLabels.length; i++) {
            if (vertexLabels[i].equals(vertexA)) vertexAIndex = i;
            if (vertexLabels[i].equals(vertexB)) vertexBIndex = i;
        }

        // Return if either vertex is not found
        if (vertexAIndex == -1 || vertexBIndex == -1) return;

        // Add the edge to the adjacency matrix
        edges[vertexAIndex][vertexBIndex] = 1;
        if (!isDirected) edges[vertexBIndex][vertexAIndex] = 1;
    }

    /**
     * Returns the number of vertices in the graph.
     * @return int, the number of vertices in the graph
     */
    public int size() {
        return vertexLabels.length;
    }

    /**
     * Returns the string name of the vertex at the given index.
     * @param v index of the vertex
     * @return String, the string name of the vertex at the given index
     */
    public String getLabel(int v) {
        return vertexLabels[v];
    }

    /**
     * Returns a string representation of the adjacency matrix.
     * @return String, a string representation of the adjacency matrix
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < vertexLabels.length; i++) {
            result.append(vertexLabels[i]).append(": ");
            for (int j = 0; j < vertexLabels.length; j++) {
                result.append(edges[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Recursive helper method for depth-first search.
     * @param vertexIndex index of the vertex to start the search from
     */
    private void dfs(int vertexIndex) {
        dfsOrder.add(vertexLabels[vertexIndex]);
        for (int i = 0; i < edges.length; i++) {
            if (edges[vertexIndex][i] == 1 && !dfsOrder.contains(vertexLabels[i])) dfs(i);
        }
        dfsDeadEndOrder.add(vertexLabels[vertexIndex]);
    }

    /**
     * Perform a depth-first search of the graph.
     * @param quiet boolean, if true then no console output, otherwise print the DFS order
     */
    public void runDFS(boolean quiet) {
        dfsOrder = new ArrayList<>();
        dfsDeadEndOrder = new ArrayList<>();

        dfs(0);
        // Run DFS on any vertices that were not visited
        if (dfsOrder.size() < vertexLabels.length) {
            for (int i = 0; i < vertexLabels.length; i++) {
                if (!dfsOrder.contains(vertexLabels[i])) dfs(i);
            }
        }

        if (quiet) return;
        System.out.println("\nDFS traversal of graph:");
        for (String vertex : dfsOrder) System.out.println("Visiting vertex " + vertex);
    }

    /**
     * Perform a depth-first search of the graph starting at the given vertex.
     * @param v starting vertex
     * @param quiet boolean, if true then no console output, otherwise print the DFS order
     */
    public void runDFS(String v, boolean quiet) {
        dfsOrder = new ArrayList<>();
        dfsDeadEndOrder = new ArrayList<>();
        int vertexIndex = -1;

        for (int i = 0; i < vertexLabels.length; i++) {
            if (vertexLabels[i].equals(v)) {
                vertexIndex = i;
                break;
            }
        }
        if (vertexIndex == -1) { // Return if vertex is not found
            System.out.println("Vertex not found.");
            return;
        }

        dfs(vertexIndex);
        // Run DFS on any vertices that were not visited
        if (dfsOrder.size() < vertexLabels.length) {
            for (int i = 0; i < vertexLabels.length; i++) {
                if (!dfsOrder.contains(vertexLabels[i])) dfs(i);
            }
        }

        if (quiet) return;
        System.out.println("\nDFS traversal of graph starting at vertex " + v + ":");
        for (String vertex : dfsOrder) System.out.println("Visiting vertex " + vertex);
    }

    /**
     * Helper method for breadth-first search.
     * @param vertexIndex index of the vertex to start the search from
     */
    private void bfs(int vertexIndex) {
        bfsOrder.add(vertexLabels[vertexIndex]);
        ArrayList<Integer> queue = new ArrayList<>();
        queue.add(vertexIndex);
        while (!queue.isEmpty()) {
            int v = queue.remove(0);
            for (int i = 0; i < edges.length; i++) {
                if (edges[v][i] == 1 && !bfsOrder.contains(vertexLabels[i])) {
                    bfsOrder.add(vertexLabels[i]);
                    queue.add(i);
                }
            }
        }
    }

    /**
     * Perform a breadth-first search of the graph.
     * @param quiet boolean, if true then no console output, otherwise print the BFS order
     */
    public void runBFS(boolean quiet) {
        bfsOrder = new ArrayList<>();

        bfs(0);
        // Run BFS on any vertices that were not visited
        if (bfsOrder.size() < vertexLabels.length) {
            for (int i = 0; i < vertexLabels.length; i++) {
                if (!bfsOrder.contains(vertexLabels[i])) bfs(i);
            }
        }

        if (quiet) return;
        System.out.println("\nBFS traversal of graph:");
        for (String vertex : bfsOrder) System.out.println("Visiting vertex " + vertex);
    }

    /**
     * Perform a breadth-first search of the graph starting at the given vertex.
     * @param v starting vertex
     * @param quiet boolean, if true then no console output, otherwise print the BFS order
     */
    public void runBFS(String v, boolean quiet) {
        bfsOrder = new ArrayList<>();
        int vertexIndex = -1;

        for (int i = 0; i < vertexLabels.length; i++) {
            if (vertexLabels[i].equals(v)) {
                vertexIndex = i;
                break;
            }
        }
        if (vertexIndex == -1) { // Return if vertex is not found
            System.out.println("Vertex not found.");
            return;
        }

        bfs(vertexIndex);
        // Run BFS on any vertices that were not visited
        if (bfsOrder.size() < vertexLabels.length) {
            for (int i = 0; i < vertexLabels.length; i++) {
                if (!bfsOrder.contains(vertexLabels[i])) bfs(i);
            }
        }

        if (quiet) return;
        System.out.println("\nBFS traversal of graph starting at vertex " + v + ":");
        for (String vertex : bfsOrder) System.out.println("Visiting vertex " + vertex);
    }

    /**
     * Returns a DFS last order string representation of the most recent DFS traversal.
     * @return String, a string representation of the most recent DFS traversal
     */
    public String getLastDFSOrder() {
        return dfsOrder != null ? dfsOrder.toString() : "No recent DFS traversal found. Please run DFS first.";
    }

    /**
     * Returns a DFS dead end / popped order string representation of the most recent DFS traversal.
     * @return String, a string representation of the most recent DFS traversal
     */
    public String getLastDFSDeadEndOrder() {
        return dfsDeadEndOrder != null ? dfsDeadEndOrder.toString() : "No recent DFS traversal found. Please run DFS first.";
    }

    /**
     * Returns a BFS order string representation of the most recent BFS traversal.
     * @return String, a string representation of the most recent BFS traversal
     */
    public String getLastBFSOrder() {
        return bfsOrder != null ? bfsOrder.toString() : "No recent BFS traversal found. Please run BFS first.";
    }

    public void reverseGraph() {
        Graph reversedGraph = new Graph(vertexLabels, isDirected);
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                reversedGraph.edges[j][i] = edges[i][j];
            }
        }
        System.out.println("\nReversed graph:");
        System.out.println(reversedGraph);
    }
}
