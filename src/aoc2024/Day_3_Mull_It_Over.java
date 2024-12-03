package aoc2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_3_Mull_It_Over {
	
	private static Pattern mulPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
	private static Pattern doPattern = Pattern.compile("do\\(\\)");
	private static Pattern mulDontPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|don't\\(\\)");
	
	public static void main(String[] args) {
		
		File f = new File("./src/aoc2024/Day_3_Mull_It_Over-Input.txt");
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);	        

	        int res = 0;
	        int resDoDont = 0;
	        int pos = 0;
	        
	        boolean mulInst = true;
	        
			String line;
			String text;
			String lastMatch;
			StringBuilder sb = new StringBuilder();
			
			Matcher doMatcher;
			Matcher mulDontMatcher;
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			
			text = sb.toString();
				
			res += findAllMul(mulPattern.matcher(text));

			doMatcher = doPattern.matcher(text);
			
			do {
				if (mulInst) {
					
					mulDontMatcher = mulDontPattern.matcher(text);
					
					while (mulDontMatcher.find(pos)) {
						
						lastMatch = mulDontMatcher.group();
						
						if (lastMatch.equals("don't()")) {
							pos = mulDontMatcher.end();
							break; 
						} else {
							int z1 = Integer.parseInt(mulDontMatcher.group(1));
							int z2 = Integer.parseInt(mulDontMatcher.group(2));

							resDoDont += z1 * z2;
							
							pos = mulDontMatcher.end();
						}	
					}						
				}
				
				if (doMatcher.find(pos)) {
					mulInst = true;
					pos = doMatcher.end();
				}
			} while (!doMatcher.hitEnd());		
		
			// Sum: 183788984
			System.out.println("Sum: " + res);
			
			// Sum (do / don't): 62098619
			System.out.println("Sum (do / don't): " + resDoDont);
			
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	public static int findAllMul(Matcher mulMatcher) {

		int res = 0;

		while (mulMatcher.find()) {
			int z1 = Integer.parseInt(mulMatcher.group(1));
			int z2 = Integer.parseInt(mulMatcher.group(2));
			res += z1 * z2;
		}
		return res;
	}
}
