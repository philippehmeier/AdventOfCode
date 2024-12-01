package aoc2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day_1_Historian_Hysteria {
	
	private static int[] left = new int[1000];
	private static int[] right = new int[1000];
	
	public static void main(String[] args) {

		File f = new File("./src/aoc2024/Day_1_Historian_Hysteria-Input.txt");
		
		try {
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String[] values;
			int counter = 0;
			
			while ((line = br.readLine()) != null) {
				
				values = line.split("   ");
	
				left[counter] = Integer.valueOf(values[0]);
				right[counter] = Integer.valueOf(values[1]);
				
				counter++;
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		quickSort(left, 0, left.length -1);
		quickSort(right, 0, right.length -1);
				
		// --- Part One ---
		System.out.println("Total distance: " + calcDistance());
		// Total distance: 2378066
		
		// --- Part Two ---
		System.out.println("Similarity score: " + calcSimilarityScore());
		// Similarity score: 18934359
	}
	
	public static int calcSimilarityScore() {
		int similarityScore = 0;
		
		int[] buffer = new int[2];
		
		for (int i = 0; i < left.length; i++) {
			
			if ((i >= 1) && (left[i] != left[i - 1])) {
				buffer = searchRight(left[i], buffer[0]);
			} 
				
			similarityScore += (left[i] * buffer[1]);
			
		}
		return similarityScore;
	}
	
	private static int[] searchRight(int num, int start) {
		int[] res = new int[2];
		
		int counter = 0;
		int posRight = 0;
		
		for (int i = start; i < right.length; i++) {
			if (num == right[i]) {
				counter++;
				posRight = i;
			} else if ((num != right[i]) && (counter > 0)) {
				break;
			}
		}
		
		res[0] = posRight;
		res[1] = counter;
		
		return res;
	}
	
	public static int calcDistance() {
		int dist = 0;
		
		for (int i = 0; i < 1000; i++) {
			dist += Math.abs(left[i] - right[i]);
		}

		return dist;
	}
	
	public static void quickSort(int arr[], int start, int end) {
	    if (start < end) {
	        int partitionIndex = partition(arr, start, end);

	        quickSort(arr, start, partitionIndex-1);
	        quickSort(arr, partitionIndex+1, end);
	    }
	}
	
	private static int partition(int arr[], int start, int end) {
	    int pivot = arr[end];
	    int i = (start-1);

	    for (int j = start; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;

	            int swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }

	    int swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;

	    return i+1;
	}
}
