/*Kiet Sam, APCS, Febuary 5, 2012 
The behavior of this program is to process a file of answers to the
Keirsey Temperament Sorter. It converts the various A and B answers 
for each person into a sequence of B-percentages and then into a 
four-letter personality type.*/
import java.util.*;
import java.io.*;
//import java.text.*;
public class PersonalityTest {
	/*This is the main method, as you might know this is where all the *magic* happens
	and in this method it introduces what the program does and it asks for the name of
	the input file and then it'll ask what the person wants to name the output file.
	Afterwards the program will read the input file for the name of the person and the 
	70 character answers from the file which will be sent to a method called countsOfAB
	which will calculate the percentage of B's for each area the percentage will then be
	passed to a method called toLetters that will be used to calculate the corresponding 
	letters and return it in a string. The output will be stored in the specified "named"
	file that the viewer inputted from the early question about what they want to name the 
	output file.*/
	public static void main (String[]args) throws FileNotFoundException{
		Scanner console = new Scanner (System.in);
		System.out.println("This program processes a file of answers to the");
		System.out.println("Keirsey Temperament Sorter. It converts the");
		System.out.println("various A and B answers for each person into");
		System.out.println("a sequence of B-percentages and then into a");
		System.out.println("four-letter personality type.");
		System.out.println();
		System.out.print("input file name? ");
		Scanner input = new Scanner(new File(console.nextLine()));
		System.out.print("output file name? ");
		PrintStream output = new PrintStream(console.nextLine());
		while (input.hasNextLine()){
			String name = input.nextLine();
			String answers = input.nextLine();
			int[]stats = countsOfAB(answers);			
			output.println(name + ": " + Arrays.toString(stats) + " = " + toLetters(stats));
		}
	}
	/*This method translates the given 70 letter characters into
	a length-4 array containing the percentages of each specific
	area of the Keirsey test which will then be returned to the
	main method.*/
	public static int[] countsOfAB(String answers){
		answers = answers.toUpperCase();
		int[]bAnswers = new int[4];
		int length = answers.length();
		int counter = 1;
		double[]questionCount = {10,20,20,20};
		for (int x = 0; x < length; x++){
			if (counter == 8){
				counter = 1;
			}
			if (answers.charAt(x) == 'B'){
				if (counter == 1){
					bAnswers[0]++;
				}
				else if (counter == 2 || counter == 3){
					bAnswers[1]++;
				}
				else if (counter == 4 || counter == 5){
					bAnswers[2]++;
				}
				else if (counter == 6 || counter == 7){
					bAnswers[3]++;
				}
			}
			else if (answers.charAt(x) == '-'){
				if (counter == 1){
					questionCount[0]--;
				}
				else if (counter == 2 || counter == 3){
					questionCount[1]--;
				}
				else if (counter == 4 || counter == 5){
					questionCount[2]--;
				}
				else {
					questionCount[3]--;
				}
			}
			counter++;
		}
		int[]stats = new int[4];
		for (int x = 0; x < 4; x++){
			stats[x] = (int)Math.round(bAnswers[x]*100/questionCount[x]);
		}
		return stats;
	}
	/*This method translates the array of percentages (as stated in the comment block
	of "countsOfAB" into a 4 character string of the person's corresponding letter type
	for each of the 4 categories which will be then returned to the main method in order 
	to cocatenized the string into a specific format for an output file.*/
	public static String toLetters(int[]answers){
		String letters = "";
		String[] aAnswers = {"E", "S", "T", "J"};
		String[] bAnswers2 = {"I", "N", "F", "P"};
		for (int x = 0; x < 4; x++){
			if (answers[x] > 50){
				letters += bAnswers2[x];
			}
			else if (answers[x] < 50){
				letters += aAnswers[x];
			}
			else {
				letters += "X";
			}
		}
		return letters;
	}
}