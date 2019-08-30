import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FindPairs {

	public static void main(String[] args) throws FileNotFoundException{
		File arrayFile = new File("src\\the_array.txt");
		ArrayList<Integer> numberArray = parseText(arrayFile);
		ArrayList<Pair> allPairs = findAllPairs(numberArray, 10);
		ArrayList<Pair> allUniquePairs = findUniquePairs(numberArray, 10);
		ArrayList<Pair> allPairsNoRepeats = findPairsNoRepeats(numberArray, 10);
		
		System.out.println("Condition 1:");
		for(Pair i : allPairs){
			System.out.println(i);
		}
		System.out.println();
		
		System.out.println("Condition 2:");
		for(Pair i : allUniquePairs){
			System.out.println(i);
		}
		System.out.println();
		
		System.out.println("Condition 3:");
		for(Pair i : allPairsNoRepeats){
			System.out.println(i);
		}
	}
	
	/*
	 * Given a text file of numbers separated only by commas, add each number to the array list,
	 * sort the array, and return the array
	 */
	private static ArrayList<Integer> parseText(File document) throws FileNotFoundException {
		Scanner documentScanner = new Scanner(document);
		ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
		String numberDelimiter = ",";
		
		documentScanner.useDelimiter(numberDelimiter);
		while(documentScanner.hasNext()) {
			listOfIntegers.add(Integer.parseInt(documentScanner.next()));
		}
		documentScanner.close();
		Collections.sort(listOfIntegers);
		
		return listOfIntegers;
	}
	
	/*
	 * Corresponds to condition 1 on the prompt; finds all potential pairs that sum to the sum
	 * 
	 * This only checks the numbers that are greater than the current number you're looking for
	 * to save time as well as take advantage of the fact that we sorted the array
	 */
	private static ArrayList<Pair> findAllPairs(ArrayList<Integer> list, int sum){
		int numLookingFor = sum;
		ArrayList<Pair> listOfPairs = new ArrayList<Pair>();
		
		for(int i = 0; i < list.size(); i++){
			numLookingFor -= list.get(i);
			
			for(int j = list.size() - 1; j >= 0; j--){
				if(list.get(j) < numLookingFor)
					break;
				else if(list.get(j) == numLookingFor && i != j){
					Pair successfulSum = new Pair(list.get(i), list.get(j));
					listOfPairs.add(successfulSum);
				}
			}
			numLookingFor = sum;
		}
		
		return listOfPairs;
	}
	
	/*
	 * Corresponds to condition 2 on the prompt; takes out duplicate pairs
	 * 
	 * As before, checks against the number you are looking for to save time
	 * Also checks if the entry before is the same number to take care of duplicates
	 */
	private static ArrayList<Pair> findUniquePairs(ArrayList<Integer> list, int sum){
		int numLookingFor = sum;
		ArrayList<Pair> listOfPairs = new ArrayList<Pair>();
		
		for(int i = 0; i < list.size(); i++){
			numLookingFor -= list.get(i);
			
			for(int j = list.size() - 1; j >= 0; j--){
				if(i > 0){
					if(j < list.size() - 1) {
						if(list.get(j) < numLookingFor || list.get(i) == list.get(i - 1))
							break;
						else if(i != j && list.get(j) == numLookingFor && list.get(j) != list.get(j + 1)){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
					else {
						if(list.get(j) < numLookingFor || list.get(i) == list.get(i - 1))
							break;
						else if(i != j && list.get(j) == numLookingFor){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
				}
				else{
					if(j < list.size() - 1) {
						if(list.get(j) < numLookingFor)
							break;
						else if(i != j && list.get(j) == numLookingFor && list.get(j) != list.get(j + 1)){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
					else{
						if(list.get(j) < numLookingFor)
							break;
						else if(i != j && list.get(j) == numLookingFor){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
				}
			}
			numLookingFor = sum;
		}
		
		return listOfPairs;
	}
	
	/*
	 * Corresponds to condition 3 on the prompt; removes reverse pairs that are the same
	 * 
	 * Given all of the previous constraints, we now added that in the second loop, j < i
	 * This makes sure the i iterator is only "looking ahead" and not backwards for pairs
	 */
	private static ArrayList<Pair> findPairsNoRepeats(ArrayList<Integer> list, int sum){
		int numLookingFor = sum;
		ArrayList<Pair> listOfPairs = new ArrayList<Pair>();
		
		for(int i = 0; i < list.size(); i++){
			numLookingFor -= list.get(i);
			
			for(int j = list.size() - 1; j >= 0; j--){
				if(i > 0){
					if(j < list.size() - 1) {
						if(list.get(j) < numLookingFor || list.get(i) == list.get(i - 1) || j < i)
							break;
						else if(i != j && list.get(j) == numLookingFor && list.get(j) != list.get(j + 1)){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
					else {
						if(list.get(j) < numLookingFor || list.get(i) == list.get(i - 1))
							break;
						else if(i != j && list.get(j) == numLookingFor){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
				}
				else{
					if(j < list.size() - 1) {
						if(list.get(j) < numLookingFor)
							break;
						else if(i != j && list.get(j) == numLookingFor && list.get(j) != list.get(j + 1)){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
					else{
						if(list.get(j) < numLookingFor)
							break;
						else if(i != j && list.get(j) == numLookingFor){
							Pair successfulSum = new Pair(list.get(i), list.get(j));
							listOfPairs.add(successfulSum);
						}
					}
				}
			}
			numLookingFor = sum;
		}
		
		return listOfPairs;
	}
}
