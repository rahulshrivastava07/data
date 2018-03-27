package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MatrixPath {
	
	
	public static void main(String[] args) {
	    String[][] map = new String[][]
	             {
	    		{ ".", ".", ".", ".", ".", "W", "W", ".", ".", "." },
                { "W", ".", ".", ".", ".", ".", "W", ".", "W", "." },
                { "W", "W", ".", "W", ".", ".", ".", "W", "W", "." },
                { ".", ".", ".", ".", ".", "W", ".", ".", "W", "." },
                { "W", ".", "W", ".", "W", "W", "W", ".", "W", "." },
                { ".", ".", ".", ".", ".", "W", "W", ".", ".", "." },
                { "W", "W", "W", "W", ".", "W", "W", ".", "W", "." },
                { "W", ".", ".", ".", ".", ".", ".", ".", "W", "W" },
                { ".", ".", ".", ".", ".", "W", "W", ".", ".", "." },
                { "W", "W", ".", "W", "W", ".", ".", "W", "W", "." },
	             };

	    Stack<Cell> path = new Stack<Cell>();
		// here starting point and end point is defined . In below example (5,0) - Start point , (5,9)- End point 
	    System.out.println(shortestPath(map, new Cell(5, 0), new Cell(5, 9), path));

	    while (!path.isEmpty()) {
	      //  System.out.print(path.pop() + ", ");
	        Cell cell = path.pop();
	        map[cell.row][cell.col] = "*";
	    }
	    
	    for(int i1=0;i1<10;i1++)
        {
             for(int j1=0;j1<10;j1++)
                 if(map[i1][j1] != null)
                 System.out.print(map[i1][j1]); 
             
             
             System.out.println();
        }
	}
	
	
	
	// add all valid adjacent points of a cell to the list
private static void addNeighbors(Cell cell, List<Cell> list, 
                                      int maxRow, int maxCol) {
    int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int[] d : ds) {
        int row = cell.row + d[0];
        int col = cell.col + d[1];          
        if (isValid(row, col, maxRow, maxCol))
            list.add(new Cell(row, col));
    }
}

// find the neighbor of a cell having a certain distance from the start        
private static Cell getNeighbor(Cell cell, int distance, int[][] distances) {
    int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int[] d : ds) {
        int row = cell.row + d[0];
        int col = cell.col + d[1];          
        if (isValid(row, col, distances.length, distances[0].length)
                && distances[row][col] == distance)
            return new Cell(row, col);              
    }
    return null;
}

// check if coordinates are inside the maze
private static boolean isValid(int row, int col, int maxRow, int maxCol) {
    return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    
}
	

public static int shortestPath(String[][] map, Cell start, Cell end, 
        Stack<Cell> path) {
// initialize distances array filled with infinity
int[][] distances = new int[map.length][];
for (int i = 0; i < map.length; i++) {
distances[i] = new int[map[i].length];
Arrays.fill(distances[i], Integer.MAX_VALUE);
}

// the start node should get distance 0
int distance = 0;
List<Cell> currentCells = Arrays.asList(start);

while (distances[end.row][end.col] == Integer.MAX_VALUE
&& !currentCells.isEmpty()) {
List<Cell> nextCells = new ArrayList<Cell>();


for (Cell cell : currentCells) {
if (distances[cell.row][cell.col] == Integer.MAX_VALUE 
&& !map[cell.row][cell.col].equals("W")) {
distances[cell.row][cell.col] = distance;
map[cell.row][cell.col] = "X";
addNeighbors(cell, nextCells, map.length, map[0].length);
}
}


currentCells = nextCells;
distance++;
}

// find the path
if (distances[end.row][end.col] < Integer.MAX_VALUE) {
Cell cell = end;
path.push(end);
for (int d = distances[end.row][end.col]-1; d >= 0; d--) {
cell = getNeighbor(cell, d, distances);
path.push(cell);

}
}

return distances[end.row][end.col];
}

}
