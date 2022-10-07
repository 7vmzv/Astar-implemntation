package com.eil;

import com.eil.elements.Grid;
import com.eil.elements.Node;

public class App {
    public static void main(String[] args) {
        int[][] g1 = {{7,2,4}, {5,0,5}, {6,7,8}};

        int[][] g = {{7, 2, 4}, {5, 0, 6}, {8, 3, 1}};
        Grid grid1 = new Grid(g1);
        Grid grid = new Grid(g);


        System.out.println(grid.equals(grid1));
        System.out.println(grid.equals(null));

        System.out.println(grid);

        Node node = new Node(grid, null, 0);
        System.out.println(node.h2());
        System.out.println(node.rightPosition(5)[0] + " " + node.rightPosition(5)[1]);

    }
}
