package aoc2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day_2_Red_Nosed_Reports {
	
	public static void main(String[] args) {
		
		File f = new File("./src/aoc2024/Day_2_Red-Nosed_Reports-Input.txt");
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String[] numString;
			int[] nums = null;
			int counter = 0;
			int correctedCounter = 0;
					
			while ((line = br.readLine()) != null) {
				numString = line.split(" ");
				
				nums = new int[numString.length];
				
				for (int i = 0; i < numString.length; i++) {
					nums[i] = Integer.valueOf(numString[i]);
				}
				
			    if (isSafe(nums)) {
			    	counter++;
			    }
			    	
			    if (isSafeWithDampener(nums)) {
			    	correctedCounter++;
			    }
				
			}
			
			br.close();
			
			// Safe reports: 472
			System.out.println("Safe reports: " + counter);
			
			// Safe reports (with dampner): 520
			System.out.println("Safe reports (with dampner): " + correctedCounter);
			
			
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	private static boolean isSafe(int[] nums) {
		
	    return isStrictlyIncreasing(nums) || isStrictlyDecreasing(nums);
	}

	private static boolean isSafeWithDampener(int[] nums) {
		
	    if (isSafe(nums)) {
	    	return true;
	    }

	    for (int i = 0; i < nums.length; i++) {
	        if (isSafe(removeElement(nums, i))) {
	            return true;
	        }
	    }
	    return false;
	}

	private static boolean isStrictlyIncreasing(int[] nums) {
		
	    for (int i = 1; i < nums.length; i++) {
	    	
	        int diff = nums[i] - nums[i - 1];
	        if (diff <= 0 || diff > 3) {
	        	return false;
	        }
	    }
	    return true;
	}

	private static boolean isStrictlyDecreasing(int[] nums) {
		
	    for (int i = 1; i < nums.length; i++) {
	    	
	        int diff = nums[i - 1] - nums[i];
	        if (diff <= 0 || diff > 3) {
	        	return false;
	        }
	    }
	    return true;
	}

	private static int[] removeElement(int[] nums, int idx) {
		
	    int[] result = new int[nums.length - 1];
	    for (int i = 0, j = 0; i < nums.length; i++) {
	    	
	        if (i != idx) {
	            result[j++] = nums[i];
	        }
	    }
	    return result;
	}
}