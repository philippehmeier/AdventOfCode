package aoc2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day_6_Guard_Gallivant {
	
	private static char[][] map;
	
	public static void main(String[] args) {

		File mapInput = new File("./src/aoc2024/Day_6_Guard_Gallivant-Input.txt");
		
		int[] pos = loadData(mapInput);
		
		int moves = 1;
		moves += move(pos[0], pos[1], 'u');

		printMap();
		
		// Moves: 4789
		System.out.println("Moves: " + moves);
				
	}
	
	private static int move(int startRow, int startCol, char startDirection) {
	    int moves = 0;

	    int row = startRow, col = startCol;
	    char direction = startDirection;

	    while (true) {
	        int nextRow = row, nextCol = col;

	        switch (direction) {
	            case 'u': nextRow = row - 1; break;
	            case 'r': nextCol = col + 1; break;
	            case 'd': nextRow = row + 1; break;
	            case 'l': nextCol = col - 1; break;
	        }

	        if (nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) {
	            System.out.println("Last pos. (" + row + ", " + col + ")");
	            break;
	        }

	        if (map[nextRow][nextCol] == '#') {
	            direction = getNextDirection(direction);
	        } else {
	            row = nextRow;
	            col = nextCol;
	            

	            if (map[row][col] == '.') {
	                map[row][col] = '*';
	                moves++;
	            }
	        }
	    }
	    return moves;
	}
	
	private static char getNextDirection(char direction) {
	
		char nextDirection = 0;
				
		switch (direction) {
			case 'u':
				nextDirection = 'r';
				break;
			case 'r':
				nextDirection = 'd';
				break;
			case 'd':
				nextDirection = 'l';
				break;
			case 'l':
				nextDirection = 'u';
				break;
		}
		return nextDirection;
	}

	private static void printMap() {
		System.out.println("      0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111");
		System.out.println("      0000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999000000000011111111112222222222");
		System.out.println("      0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		System.out.println("    +-----------------------------------------------------------------------------------------------------------------------------------");;
		
		for (int i = 0; i < map.length; i++) {
			System.out.printf("%3d", i);
			System.out.print(" | ");
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static int[] loadData(File mapInput) {
		
		int[] pos = new int[2];
		
		ArrayList<String> lines = new ArrayList<>();

		try {
			FileReader fr = new FileReader(mapInput);
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			int posH = -1;
			
			while ((line = br.readLine()) != null) {
				posH = line.indexOf('^');	
				if (posH != -1) {
					pos[0] = lines.size();
					pos[1] = posH;
				}
				lines.add(line);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.getStackTrace();				
		} catch (IOException e) {
			e.getStackTrace();
		}
		
		map = new char[lines.size()][];
		
	    for (int i = 0; i < lines.size(); i++) {
	        map[i] = lines.get(i).toCharArray();
	    }
	    return pos;
	}
}