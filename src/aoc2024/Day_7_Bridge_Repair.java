package aoc2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day_7_Bridge_Repair {

	public static void main(String[] args) {
		
		File f = new File("./src/aoc2024/Day_7_Bridge_Repair-Input.txt");
		long calibrationResult = 0;
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);	        

			String line;
			
			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(":");
				
				long target = Long.parseLong(lineSplit[0].trim());
				
				String[] numsString = lineSplit[1].trim().split("\\s+");
				
				int[] input = new int[numsString.length];
				
				for (int i = 0; i < numsString.length; i++) {
					input[i] = Integer.parseInt(numsString[i].trim());
				}
				
				if (checkTarget(input, target)) {
					calibrationResult += target;
				}
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
	
		// Calibration Result: 14711933466277
		System.out.println("Calibration Result: " + calibrationResult);
	}

	public static boolean checkTarget(int[] input, long target) {
        return findCombination(input, 1, input[0], target);
    }

    public static boolean findCombination(int[] input, int index, long currentResult, long target) {
        if (index == input.length) {
            return currentResult == target;
        }

        if (findCombination(input, index + 1, currentResult + input[index], target)) {
            return true;
        }

        if (findCombination(input, index + 1, currentResult * input[index], target)) {
            return true;
        }
        return false;
    }
}