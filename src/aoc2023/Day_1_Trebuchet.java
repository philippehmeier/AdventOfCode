package aoc2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_1_Trebuchet {
	
	public static void main(String[] args) {
		
		int r = 0;
		
		String[] numWords = {
				"",
				"one",
				"two",
				"three",
				"four",
				"five",
				"six",
				"seven",
				"eight",
				"nine"
		};
	
		try {
			Scanner s = new Scanner(new File("./src/aoc2023/Day_1_Trebuchet-Input.txt"));
					
			while (s.hasNextLine()) {
				
				ArrayList<Integer> nums = new ArrayList<Integer>();
				
// ============ First part only ============
				
//				char[] ch = s.nextLine().toCharArray();
//				
//				for (char c : ch) {
//					if (Character.isDigit(c)) {
//						nums.add(Character.getNumericValue(c));
//					}
//				}
				
// ============ Second part only ============
								
				String line = s.nextLine();
				int[] numsLine = new int[line.length()];
				
								
				for (int i = 1; i < numWords.length; i++) {
					if (line.contains(numWords[i])) {
						numsLine[line.indexOf(numWords[i])] = i;
					} 					
					if (line.contains(Integer.toString(i))) {
						numsLine[line.indexOf(Integer.toString(i))] = i;
					}		
				}
				
				for (int i = 1; i < numWords.length; i++) {
					if (line.contains(numWords[i])) {
						numsLine[line.lastIndexOf(numWords[i])] = i;
					} 					
					if (line.contains(Integer.toString(i))) {
						numsLine[line.lastIndexOf(Integer.toString(i))] = i;
					}		
				}
								
				for (int i = 0; i < numsLine.length; i++) {
					if (numsLine[i] > 0) {
						nums.add(numsLine[i]);
					}
				}
				
// ============ Result sum ============
					
				if (nums.size() == 1) {
					r += nums.get(0) * 10 + nums.get(0);
				} else {
					r += nums.get(0) * 10 + nums.get((nums.size() - 1));
				}								
			}
			
			System.out.println("Result: " + r);
			// Result: 54925
			s.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
}