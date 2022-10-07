package com.eil.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class Node {
    private Grid grid;
    private Grid parent;
    private int numberOfMovementFromInitialState;

    public int h1() {
        int count = 0;
        int tracker = 1;
        int[][] gridArray = grid.getGrid();
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize() - 1; j++) {
                if(gridArray[i][j] != tracker) count++;
                tracker++;
            }
        }
        return count;
    }

    public int h2() {
        int sumOfDistances = 0;
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if(grid.getValue(i, j) == 0) continue;
                int[] pos = rightPosition(grid.getValue(i, j));
                sumOfDistances += hamiltonianDistance(pos[0], pos[1], i, j);
            }
        }
        return sumOfDistances;
    }

    private int h() {
        return h1();
    }

    private int hamiltonianDistance(int i_finale, int j_finale, int i_acc, int j_acc) {
        return Math.abs(i_acc - i_finale) + Math.abs(j_acc - j_finale);
    }

    public int[] rightPosition(int val) {
        int[] pos = new int[2];
        pos[0] = (int)(val - 0.00001) / grid.getSize();
        pos[1] = val - (pos[0] * grid.getSize() + 1);
        return pos;
    }
}