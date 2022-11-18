package com.eil.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public static int[][] copy(int[][] src) {
        if (src == null) {
            return null;
        }

        int[][] copy = new int[src.length][];
        for (int i = 0; i < src.length; i++) {
            copy[i] = Arrays.copyOf(src[i], src[i].length);
        }

        return copy;
    }

    public List<Grid> successors() {
        List<Grid> successorsList = new ArrayList<>();
        if(grid.getLine0() > 0) {
            int[][] top = copy(grid.getGrid());
            top[grid.getLine0()][grid.getColumn0()] = top[grid.getLine0() - 1][grid.getColumn0()];
            top[grid.getLine0() - 1][grid.getColumn0()] = 0;
            successorsList.add(new Grid(top));
        }

        if(grid.getLine0() < grid.getSize() - 1) {
            int[][] bottom = copy(grid.getGrid());
            bottom[grid.getLine0()][grid.getColumn0()] = bottom[grid.getLine0() + 1][grid.getColumn0()];
            bottom[grid.getLine0() + 1][grid.getColumn0()] = 0;
            successorsList.add(new Grid(bottom));
        }

        if(grid.getColumn0() > 0) {
            int[][] left = copy(grid.getGrid());
            left[grid.getLine0()][grid.getColumn0()] = left[grid.getLine0()][grid.getColumn0() - 1];
            left[grid.getLine0()][grid.getColumn0() - 1] = 0;
            successorsList.add(new Grid(left));
        }

        if(grid.getLine0() < grid.getSize() - 1) {
            int[][] right = copy(grid.getGrid());
            right[grid.getLine0()][grid.getColumn0()] = right[grid.getLine0()][grid.getColumn0() + 1];
            right[grid.getLine0()][grid.getColumn0() + 1] = 0;
            successorsList.add(new Grid(right));
        }
        return successorsList;
    }
}