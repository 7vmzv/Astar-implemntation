package com.eil.elements;

import lombok.Getter;

@Getter
public class Grid {
    private int[][] grid;
    private int size;
    private int line0;
    private int column0;

    public Grid(int[][] grid) {
        size = grid[0].length;
        this.grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(grid[i][j] == 0) {
                    line0 = i;
                    column0 = j;
                }
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    private Grid(int[][] grid, int size, int line0, int column0) {
        this.grid = grid;
        this.size = size;
        this.line0 = line0;
        this.column0 = column0;
    }

    public int getValue(int i, int j) {
        return this.grid[i][j];
    }

    public int[][] copy() {
        return this.grid.clone();
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if(this.grid[i][j] == 0) res += "   ";
                else res += " " + grid[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (obj instanceof Grid) {
            Grid otherObj = (Grid) obj;
            if(otherObj.size != this.size) return false;
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if(this.grid[i][j] != otherObj.grid[i][j]) {
                        return false;
                    }
                }
            }
        }else {
            return false;
        }
        return true;
    }
}
