package aoc2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_2_Cube_Conundrum {
		
	public static void main(String[] args) {
		
		int result = 0;
		int sumPower = 0;

		String[] cubeColors = {
				" red",
				" green",
				" blue"
		};
				
		int[] numCubes = {12, 13, 14}; // red, green, blue
				
		int gameNo = 0;

		try {
			Scanner s = new Scanner(new File("./src/aoc2023/Day_2_Cube_Conundrum.txt"));
					
			while (s.hasNextLine()) {
				gameNo++;
								
				String line = s.nextLine();
				
				line = line.replaceAll("Game [0-9]: |Game [0-9][0-9]: |Game [0-9][0-9][0-9]: ", "");
				
				String[] splitSets = line.split("; ");
				
				int sumSet = 0;
				
				int[] minGame = new int[3];
	
				for (int i = 0; i < splitSets.length; i++) {
									
					System.out.println("Game " + gameNo + ": " + "Set " + i + ": " + splitSets[i]);

					String[] splitSetsColors = splitSets[i].split(", ");
					String[] splitSetsColorsOrdered = new String[3];
					
					int[] singleGameSet = new int[4];
					
					for (int j = 0; j < splitSetsColors.length; j++) {
						for (int k = 0; k < cubeColors.length; k++) {
							if (splitSetsColors[j].contains(cubeColors[k])) {
								splitSetsColorsOrdered[j] = splitSetsColors[j];
								splitSetsColorsOrdered[j] = splitSetsColorsOrdered[j].replaceAll(cubeColors[k], "");
								singleGameSet[k] = Integer.parseInt(splitSetsColorsOrdered[j]);
								System.out.println("Vor IF: minGame[" + k + "] = " + minGame[k]);
								if (singleGameSet[k] > minGame[k]) {
									minGame[k] = singleGameSet[k];
									System.out.println("Im IF: minSet[" + k + "] = " + minGame[k]);
								} 
							} 
						}
					}
					
					for (int l = 0; l < numCubes.length; l++) {
						if (singleGameSet[l] <= numCubes[l]) {
							singleGameSet[3] += 1;
						}
					}
										
					if (singleGameSet[3] * splitSets.length == splitSets.length * 3) {
						sumSet++;
					}
				}
								
				if (sumSet == splitSets.length) {
					result += gameNo;
				}
								
				sumPower += minGame[0] * minGame[1] * minGame[2];
			}
			
			System.out.println("Result: " + result);
			// 2727
			System.out.println("Power: " + sumPower);
			// 56580
			s.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
}
