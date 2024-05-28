package org.example;

public class Main {
    // Page 3 of Lab 4 handout (Pink graph)
    public static Graph graph0() {
        String[] vnames = {"a", "b", "c", "d"};
        Graph graph = new Graph(vnames, false);
        graph.addEdge("a", "b");
        graph.addEdge("a", "d");
        graph.addEdge("b", "c");
        graph.addEdge("b", "d");
        graph.addEdge("c", "d");
        return graph;
    }

    // Page 5 of Lab 4 handout / Page 33 of Lecture 7 (8 vertices)
    public static Graph graph1() {
        String[] vnames = {"a", "b", "c", "d", "e", "f", "g", "h"};
        Graph graph = new Graph(vnames, false);
        graph.addEdge("a", "b");
        graph.addEdge("a", "e");
        graph.addEdge("a", "f");
        graph.addEdge("b", "f");
        graph.addEdge("b", "g");
        graph.addEdge("c", "d");
        graph.addEdge("c", "g");
        graph.addEdge("d", "h");
        graph.addEdge("e", "f");
        graph.addEdge("g", "h");
        return graph;
    }

    // Page 1 of Lab 4 handout (123456)
    public static Graph graph2() {
        String[] vnames = {"1", "2", "3", "4", "5", "6"};
        Graph graph = new Graph(vnames, true);
        graph.addEdge("1", "4");
        graph.addEdge("2", "1");
        graph.addEdge("2", "3");
        graph.addEdge("2", "4");
        graph.addEdge("4", "3");
        graph.addEdge("5", "1");
        graph.addEdge("5", "2");
        graph.addEdge("5", "6");
        graph.addEdge("6", "2");
        graph.addEdge("6", "3");
        return graph;
    }

    // Page 1 of Lab 4 handout (2 Triangles)
    public static Graph graph3() {
        String[] vnames = {"c0", "c1", "c2", "c3", "c4", "c5"};
        Graph graph = new Graph(vnames, false);
        graph.addEdge("c0", "c2");
        graph.addEdge("c0", "c4");
        graph.addEdge("c1", "c3");
        graph.addEdge("c1", "c5");
        graph.addEdge("c2", "c4");
        graph.addEdge("c3", "c5");
        return graph;
    }

    // Page 1 of Lab 4 handout (directed abcdef)
    public static Graph graph4() {
        String[] vnames = {"a", "b", "c", "d", "e", "f"};
        Graph graph = new Graph(vnames, true);
        graph.addEdge("a", "b");
        graph.addEdge("a", "e");
        graph.addEdge("a", "f");
        graph.addEdge("b", "c");
        graph.addEdge("d", "b");
        graph.addEdge("e", "d");
        graph.addEdge("f", "c");
        graph.addEdge("f", "e");
        return graph;
    }

    // Complete graph with 5 vertices undirected
    public static Graph graph5() {
        String[] vnames = {"1", "2", "3", "4", "5"};
        Graph graph = new Graph(vnames, false);
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("1", "4");
        graph.addEdge("1", "5");
        graph.addEdge("2", "3");
        graph.addEdge("2", "4");
        graph.addEdge("2", "5");
        graph.addEdge("3", "4");
        graph.addEdge("3", "5");
        graph.addEdge("4", "5");
        return graph;
    }

    // Page 1 of Lab 4 handout (directed abcde)
    public static Graph graph6() {
        String[] vnames = {"a", "b", "c", "d", "e"};
        Graph graph = new Graph(vnames, true);
        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("b", "c");
        graph.addEdge("b", "e");
        graph.addEdge("c", "e");
        graph.addEdge("d", "a");
        graph.addEdge("d", "b");
        graph.addEdge("d", "c");
        graph.addEdge("d", "e");
        return graph;
    }

    public static void main(String[] args) {
//        Test variables
//        int vIndex = 2;
        String vStart = "b";

        Graph graph = graph6();
        System.out.println(graph);

//        System.out.println("Number of vertices: " + graph.size());
//        System.out.println("Vertex index " + vIndex + ": " + graph.getLabel(vIndex));

//        DFS
//        graph.runDFS(false);
//        System.out.println("DFS Order: \t\t" + graph.getLastDFSOrder());
//        System.out.println("DFS Pop Order: \t" + graph.getLastDFSDeadEndOrder());
        graph.runDFS(vStart, false);
        System.out.println("DFS Order: \t\t" + graph.getLastDFSOrder());
        System.out.println("DFS Pop Order: \t" + graph.getLastDFSDeadEndOrder());

//        BFS
//        graph.runBFS(false);
//        System.out.println("BFS Order: \t\t\t\t" + graph.getLastBFSOrder());
        graph.runBFS(vStart, false);
        System.out.println("BFS Order: \t\t" + graph.getLastBFSOrder());
    }
}