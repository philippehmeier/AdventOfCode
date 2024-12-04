package aoc2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day_4_Ceres_Search {
	
	private static char[][] inputText;
	
	public static void main(String[] args) {
		
		File f = new File("./src/aoc2024/Day_4_Ceres_Search-Input.txt");
		
		int xmasCounter = 0;
		int xMas = 0;
		
		inputText = textToCharArr(f);
		
		if (inputText == null) {
		    System.out.println("Error while reading input file.");
		    return;
		}
				
		for (int r = 3; r < inputText.length; r++) {
			for (int c = 3; c < inputText[r].length; c++) {
				xmasCounter += countXmas(r, c);
			}
		}
				
		for (int r = 2; r < inputText.length; r++) {
			for (int c = 2; c < inputText[r].length; c++) {
				xMas += countXmasXmas(r, c);
			}
		}
		
		// XMAS: 2297
		System.out.println("XMAS: " + xmasCounter);
		
		// X-MAS: 1745
		System.out.println("X-MAS: " + xMas);
	}

	private static char[][] textToCharArr(File f) {
		
		ArrayList<String> lines = new ArrayList<>();

		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
						
		} catch (IOException e) {
			e.getStackTrace();
		}
		
		char[][] text = new char[lines.size()][];
		
	    for (int i = 0; i < lines.size(); i++) {
	        text[i] = lines.get(i).toCharArray();
	    }
		
		return text;
	}

	private static int countXmas(int r, int c) {
		
		int xmasCounter = 0;
		char[] temp = new char[4];
		
		// To avoid lines to be counted multiple times
		if (((r + 1) % 4) == 0) {
			
			// Line by line
			for (int i = r - 3; i <= r; i++) {
				temp[0] = inputText[i][c - 3];
				temp[1] = inputText[i][c - 2];
				temp[2] = inputText[i][c - 1];
				temp[3] = inputText[i][c];
				
				xmasCounter += checkXmas(new String(temp));
			}
		}
		
		// To avoid rows to be counted multiple times
		if (((c + 1) % 4) == 0) {
			
			// Row by row
			for (int i = c - 3; i <= c; i++) {
				temp[0] = inputText[r - 3][i];
				temp[1] = inputText[r - 2][i];
				temp[2] = inputText[r - 1][i];
				temp[3] = inputText[r][i];
							
				xmasCounter += checkXmas(new String(temp));
			}
		}
		
		// Diagonal (left top -> right bottom)
		for (int i = 0; i <= 3; i++) {
			temp[i] = inputText[r - 3 + i][c - 3 + i];
		}
		xmasCounter += checkXmas(new String(temp));
		
		// Diagonal (right top -> left bottom)
		for (int i = 0; i <= 3; i++) {
			temp[i] = inputText[r - 3 + i][c - i];
		}
		xmasCounter += checkXmas(new String(temp));
			
		return xmasCounter;
	}

	private static int checkXmas(String s) {
		
		if ((s.equals("XMAS")) || (s.equals("SAMX"))) {
			return 1;
		} else {
			return 0;
		}
	}
	
	private static int countXmasXmas(int r, int c) {
		int xmasXmasCounter = 0;
		char[][] temp = new char[3][3];
		
		temp[0][0] = inputText[r - 2][c - 2];
		temp[0][1] = inputText[r - 2][c - 1];
		temp[0][2] = inputText[r - 2][c];
		
		temp[1][0] = inputText[r - 1][c - 2];
		temp[1][1] = inputText[r - 1][c - 1];
		temp[1][2] = inputText[r - 1][c];
		
		temp[2][0] = inputText[r][c - 2];
		temp[2][1] = inputText[r][c - 1];
		temp[2][2] = inputText[r][c];
		
		xmasXmasCounter += checkXmasXmas(temp);
		
		return xmasXmasCounter;
	}

	private static int checkXmasXmas(char[][] x) {
		
		char[] ltrb = new char[] {x[0][0], x[1][1], x[2][2]};
		char[] rtlb = new char[] {x[0][2], x[1][1], x[2][0]};
				
		String ltrbS = new String(ltrb);
		String rtlbS = new String(rtlb);
				
		if (((ltrbS.equals("MAS")) || (ltrbS.equals("SAM"))) && ((rtlbS.equals("MAS")) || (rtlbS.equals("SAM")))) {
			return 1;
		}
		return 0;
	}
}