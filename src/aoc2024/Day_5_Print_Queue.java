package aoc2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day_5_Print_Queue {
	
	private static HashMap<Integer, ArrayList<Integer>> rules = new HashMap<>();
	private static ArrayList<Integer> keys = new ArrayList<>();
	private static ArrayList<int[]> queues = new ArrayList<>();
	private static ArrayList<int[]> falseQueues = new ArrayList<>();

	public static void main(String[] args) {

		File rulesFile = new File("./src/aoc2024/Day_5_Print_Queue-Rules.txt");
		File queuesFile = new File("./src/aoc2024/Day_5_Print_Queue-Queues.txt");

		loadData(rulesFile);
		loadData(queuesFile);

		int sum = 0;
		int sumAfterUpdate = 0;
		int temp;

		for (int[] a : queues) {

			if ((temp = checkOrder(a)) != 0) {
				sum += temp;
			} else {
				falseQueues.add(a);
			}
		}

		for (int[] a : falseQueues) {

			if ((temp = updateOrder(a)) != 0) {
				sumAfterUpdate += temp;
			}
		}

		// Sum middle page nummers: 4905
		System.out.println("Sum middle page nummers: " + sum);

		// Sum middle page nummers (after update): 6204
		System.out.println("Sum middle page nummers (after update): " + sumAfterUpdate);
	}

	private static int updateOrder(int[] a) {

		HashMap<Integer, List<Integer>> hm = new HashMap<>();
		HashMap<Integer, Integer> deps = new HashMap<>();

		for (int num : a) {

			hm.put(num, new ArrayList<>());
			deps.put(num, 0);
		}

		for (int num : a) {
			List<Integer> dependencies = rules.get(num);

			if (dependencies != null) {
				for (int dep : dependencies) {

					if (hm.containsKey(dep)) {
						hm.get(dep).add(num);
						deps.put(num, deps.get(num) + 1);
					}
				}
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int num : deps.keySet()) {
			if (deps.get(num) == 0) {
				queue.add(num);
			}
		}

		List<Integer> sortedList = new ArrayList<>();
		while (!queue.isEmpty()) {
			int current = queue.poll();
			sortedList.add(current);

			for (int neighbor : hm.get(current)) {
				deps.put(neighbor, deps.get(neighbor) - 1);
				if (deps.get(neighbor) == 0) {
					queue.add(neighbor);
				}
			}
		}

		for (int i = 0; i < a.length; i++) {
			a[i] = sortedList.get(i);
		}

		return a[a.length / 2];
	}

	private static int checkOrder(int[] queue) {

		int okCounter = 0;
		int ruleCounter;

		for (int i = 1; i < queue.length; i++) {

			ruleCounter = 0;

			for (int j = 0; j < i; j++) {
				ruleCounter += findValue(queue[i], queue[j]);
			}

			if (ruleCounter == i) {
				okCounter++;
			}
		}

		if (okCounter == (queue.length - 1)) {
			return queue[queue.length / 2];
		} else {
			return 0;
		}
	}

	private static int findValue(int key, int before) {

		boolean found = false;

		ArrayList<Integer> ruleArrayList = rules.get(key);

		for (int rule : ruleArrayList) {

			if (rule == before) {
				found = true;
				break;
			}
		}

		if (found) {
			return 1;
		} else {
			return 0;
		}
	}

	private static void loadData(File file) {

		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;
			String[] lineArr;

			while ((line = br.readLine()) != null) {

				if (file.getPath().equals("./src/aoc2024/Day_5_Print_Queue-Rules.txt")) {
					lineArr = line.split("\\|");

					int value = Integer.parseInt(lineArr[0]);
					int key = Integer.parseInt(lineArr[1]);

					if (rules.get(key) == null) {
						ArrayList<Integer> values = new ArrayList<>();
						values.add(value);
						keys.add(key);
						rules.put(key, values);
					} else {
						rules.get(key).add(value);
					}
				} else {
					lineArr = line.split("\\,");
					int[] a = new int[lineArr.length];

					for (int i = 0; i < a.length; i++) {
						a[i] = Integer.parseInt(lineArr[i]);
					}
					queues.add(a);
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
