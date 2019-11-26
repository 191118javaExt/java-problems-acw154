package com.revature.eval.java.core;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		//Create a new array based on the string
		String[] s = string.split("");
		// Instantiate a new array
		String[] array = new String[s.length];
		int count = 0;
		// Loop through the string in reverse order
		for(int i=s.length-1; i>=0; i--) { 
			// Set the value of the array equal to the string value at index i
			array[count] = s[i];
			count++;
		}
		// Concatenate elements of the string array
		return String.join("", array);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// Instantiate char array from String phrase
		char[] splitString = phrase.toCharArray();
		String acro = "";
		if(phrase.isEmpty()) {
			// Check edge case in which argument is an empty string
			return "";
		}
		if(Character.isLetter(splitString[0])){
			// The first character of the phrase, if a letter, should always be part of the acronym
			acro += splitString[0];
		}
		for(int i=1; i < splitString.length; i++) {
			// Check for a space behind every letter and add that letter to the acronym
			if(!Character.isLetter(splitString[i-1]) && Character.isLetter(splitString[i])) {
				acro += splitString[i];
			}
		}
		//acro = String.join("", acro).replace(" ", "");
		// Capitalize acronym string
		return acro.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			double one = getSideOne();
			double two = getSideTwo();
			double three = getSideThree();
			// Return true if all sides are equivalent
			if(one == two && two == three) {
				return true;
			}
				return false;			
		}

		public boolean isIsosceles() {
			// TODO Write an implementation for this method declaration
			double one = getSideOne();
			double two = getSideTwo();
			double three = getSideThree();
			// Check if all sides are equivalent
			if(this.isEquilateral()) {
				return true;
			}
			// Check if at least two sides are equivalent
			if(one == two || one == three || two == three) {
				return true;
			}
			return false;
			
		}

		public boolean isScalene() {
			// Check if at least two sides are equivalent
			if(!this.isIsosceles()) {
				return true;
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// AEIOULNRSTDGBCMPFHVWYKJXQZ
		// Create dictionary and place every letter with its corresponding value
		HashMap<String, Integer> scrabbleScores = new HashMap<String, Integer>();
		int total = 0;
		String[] lettersUsed = string.toUpperCase().split("");
		String[] scrabbleLetters = "AEIOULNRSTDGBCMPFHVWYKJXQZ".split("");
		for(int i=0;i<scrabbleLetters.length;i++) {
			if(i<10) {
				scrabbleScores.put(scrabbleLetters[i], 1);
			} else if(i<12) {
				scrabbleScores.put(scrabbleLetters[i], 2);
			} else if (i<16) {
				scrabbleScores.put(scrabbleLetters[i], 3);
			} else if (i < 21) {
				scrabbleScores.put(scrabbleLetters[i], 4);
			} else if (i == 21) {
				scrabbleScores.put(scrabbleLetters[i], 5);
			} else if (i < 24) {
				scrabbleScores.put(scrabbleLetters[i], 8);
			} else {
				scrabbleScores.put(scrabbleLetters[i], 10);
			}
		}
		// Loop through the string and compute the score based on the dictionary values
		for(int j = 0; j < lettersUsed.length; j++) {
			total += scrabbleScores.get(lettersUsed[j]);
		}
		return total;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// Check if input string is a valid phone number
		if(string.matches(".*[!\"#$%&'*+,/:;?@\\^_`{|}~].*"))
	    {
			throw new IllegalArgumentException("Phone Number must not have any nonnumeric characters");
	    }
		char[] phoneNum = string.toCharArray();
		String cleaned = "";
		// Change reference of char array phoneNum to a new char array that does not include the 
		// country code '1'
		if(phoneNum[0] == '1') {
			phoneNum = Arrays.copyOfRange(phoneNum, 1, phoneNum.length);
		}
		// Remove all dashes, dots, etc.
		for (int i = 0; i < phoneNum.length; i++ ) {
			if (Character.isDigit(phoneNum[i])) {
				cleaned += phoneNum[i];
			}
		}
		// Check if the cleaned phone number has appropriate length
		if(cleaned.length() > 11) {
			throw new IllegalArgumentException("Phone Number must be 10 digits or less");
		}
		// Return the concatenated and cleaned phone number string
		return String.join("", cleaned);
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// Split the string by words and eliminate white space
		String[] words = string.split("\\W+");
		// Instantiate a new Dictionary with string:number pairings
		Map<String, Integer> countedWords = new HashMap<String, Integer>();
		for(int i=0; i<words.length; i++) {
			// For each word, place the word in the dictionary if it does not already exist
			Integer check = countedWords.putIfAbsent(words[i], 1);
			// Otherwise, increment the int count of the word
			if(check != null) {
				countedWords.replace(words[i], check + 1);
			}
		}
		return countedWords;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {			
			// Cast the generic t object into an Integer object
			Integer search;
			if(!sortedList.contains(t)) {
				return -1;
			}
			if(t instanceof String) {
				String s = (String) t;
				search = Integer.parseInt(s);
			} else {
				search = (Integer) t;
			}
			// Initialize ArrayList of Integers of casted T values from sortedList<T>
			List<Integer> newList = new ArrayList<Integer>();
			for(int i=0; i <sortedList.size(); i++) {
				Integer temp;
				if(sortedList.get(i) instanceof String) {
					String stringTemp = (String) sortedList.get((i));
					temp = Integer.parseInt(stringTemp);
				} else {
					temp = (Integer) sortedList.get(i);
				}
				newList.add(temp);
			} 
			// Sort the new list 
			Collections.sort(newList);
			
			//Binary Search 
			int start = 0;
			int end = newList.size()-1;
			int mid = 0;
			
			while(start <= end) {
				// Get the middle index of the ArrayList
				mid = (start + end) / 2;
				// Compare value at start index to the value of the middle index of ArrayList
				if (search > newList.get(mid)) {
					// Branch into upper half of ArrayList
					start = mid + 1;
				} else if(search < newList.get(mid)){
					// Branch into lower half of arrayList
					end = mid - 1;
				} else {
					// Return the index of the middle value of the ArrayList when it is equal
					// to the value of the start index of ArrayList
					return mid;
				}
			}
			// Statement could be removed if everything after the contains check was wrapped around
			// an else block
			return -1;
			// return Collections.binarySearch(newList, search); 
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// TODO Write an implementation for this method declaration
		// Initialize String array by splitting input string by word
		String[] stringWords = string.split("\\W+");
		StringBuilder[] words = new StringBuilder[stringWords.length];
		// Fill StringBuilder array with each word in the stringWords array
		for (int i=0; i<words.length; i++) {
			words[i] = new StringBuilder(stringWords[i]);
		}
		// Transform words to PigLatin version 
		for (StringBuilder word: words){
			if(word.substring(0,1).matches("[aeiouAEIOU]")) {
				// Special case for vowel at beginning of word
				word.append("ay");
			} else if (word.substring(0, 1).equals("q".toLowerCase())){
				// Special case for 'q' character at beginning of word
				word.append(word.subSequence(0, 2) + "ay");
				word.delete(0, 2);
			} else {
				for (int j=0; j<word.length(); j++) {
					if(word.substring(j, j+1).matches("[aeiouAEIOU]")) {
						// Append from beginning of word (in consonant case) to first vowel
						word.append(word.subSequence(0, j) + "ay");
						word.delete(0, j);
						break;
					}
				}
			}
		}
		// Return the words as a string separated by spaces
		return String.join(" ", words);
		
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		int total = 0;
		// ArrayList<Integer> digits = new ArrayList<Integer>();
		/**do {
			digits.add(input % 10);
			input / 10;
		} while (input > 0);
		*/
		// Map each digit of the input to an int array 
		int[] digits = Integer.toString(input).chars().map(c -> c-'0').toArray();
		if(digits.length == 2){
			// 2 digit numbers are not Armstrong numbers
			return false;
		}
		for(int i=0; i<digits.length; i++) {
			// Sum the value of each digit of the original input to the power of 
			// the number of digits
			total += Math.pow(digits[i], digits.length);
		}
		return input == total;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> factors = new ArrayList<Long>();
		// Loop to the value of l starting from 2
		for(long i = 2; i <= l; i++) {
			while (l % i == 0) {
				// Adds all numbers that can cleanly divide l by i 
				factors.add(i);
				// Divides the value of l by i to decrease the amount of numbers needed to
				// be checked (decrements loop length) while ensuring uniqueness of vals
				// added to the ArrayList
				l /= i;
			}
		}
		return factors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}
		
		//Probably should mention that this is ROT13
		public String rotate(String string) {
			char[] ch = string.toCharArray();
			String rtn = "";
			/**StringBuilder rotAlpha = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
			int[] rotIndex = new int[26];
			for(int i =0; i< rotIndex.length; i++) {
				rotIndex[i] = i;
			}
			Map<Integer, Character> updatedROTAlpha = new HashMap<Integer, Character>();
			for(int j=0; j<rotIndex.length; j++) {
				updatedROTAlpha.put(rotIndex[j], rotAlpha.charAt(j));
			}
			*/
			// Loop through each character of the input string 
			for(char c: ch) {
				if(!Character.isLetter(c)) {
					// Non-alphabetical characters will not be changed by the cipher
					rtn +=c;
				}
				if(c >= 'a' && c <= 'z'){
					// If the character is between the values of 'a' and 'z'
					// the cipher key can be added to correctly shift
		            c = (char)(c + key);
		            if(c > 'z'){
		            	// otherwise, in the case that c > 'z' 
		            	// the character must have 26 subtracted from it
		                c = (char)(c - 'z' + 'a' - 1);
		            }
		            rtn += c;
		        }
		        else if(c >= 'A' && c <= 'Z'){
		        	// If the character is between the values of 'A' and 'Z'
					// the cipher key can be added to correctly shift 
		            c = (char)(c + key);
		            
		            if(c > 'Z'){
		            	// otherwise, in the case that c > 'Z' 
		            	// the character must have 26 subtracted from it
		                c = (char)(c - 'Z' + 'A' - 1);
		            }
		            rtn += c;
		        }			
			}
			return rtn;
		}
	}

	

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		if (i <= 0) {
			// Throw exception if input value is less than zero
			throw new IllegalArgumentException("Argument must be greater than zero");
		}
		int n = 1;
		int count = 0;
		int j;
		while(count < i) {
			// Increment n through each loop
			n+=1;
			for (j=2; j<=n; j++) {
				if(n % j == 0) {
					// Will break out of the loop and increment n without incrementing count
					break;
				}
			}
			if(j == n) {
				// If a prime factor has been found, increment count
				count+=1;
			}
		}
		
		return n;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// Separate each word in the string 
			string = string.replaceAll("\\W+", "");
			char[] ch = string.toCharArray();
			String rtn = "";
			int count = 0;
			// If Character.isDigit we add the character
			// if character.isLetter we add the letter after transforming
			// In either case, we increment a count variable and whenever count % 5 == 0,
			// we add a space character " "
			for (char c: ch) {
				if(Character.isLetter(c)) {
					if(Character.isUpperCase(c)) {
						rtn += Character.toLowerCase((char) ('A' + ('Z' - c)));
						count++;
					} else {
						rtn += (char) ('a' + ('z' - c));
						count++;
					}
				}
				if(Character.isDigit(c)) {
					rtn += c;
					count++;
				}
				if(count % 5 == 0 && ch[ch.length-1] != c) {
					rtn += " ";
					count = 0;
				}
			}
			return rtn;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// The atbash cipher is symmetrical and can be used to encode and decode
			// using the same method 
			String rtn = encode(string);
			// remove the spaces from the encoded string
			rtn = rtn.replaceAll(" ", "");
			return rtn;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// Initialize StringBuilder object by removing all dash symbols
		StringBuilder sb = new StringBuilder(Pattern.compile("-").matcher(string).replaceAll(""));
		// ISBNs must contain 10 characters and the first 9 must be numbers
		if (sb.length() != 10 || !sb.substring(0, sb.length()-1).matches("[0-9]+")) {
			return false;
		} else {
			int total = 0;
			// Check that the last character of the string is a number or an 'X' character
			if(Character.isDigit(sb.charAt(sb.length()-1)) || sb.charAt(sb.length()-1) == 'X') {
				// Calculate the value of all of the digits multiplied by 10 minus their index
				for(int i=0; i < sb.length(); i++) {
					if(sb.charAt(i) == 'X') {
						// If the character is an 'X', increment total by 10
						total += 10;
					} else {
						total += (Integer.parseInt(String.valueOf(sb.charAt(i))) * (sb.length() - i));
					}
				}
			if( total % 11 == 0) {
				// The total value must be divisible by 11 cleanly 
				return true;
			}
			}
		}
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		if(string.isEmpty()) {
			// An empty string is not a Pangram
			return false;
		}
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			// Check if every character of the alphabet (case-insensitive) 
            if ((string.indexOf(ch) < 0) && (string.indexOf((char)(ch + 32)) < 0))
            	// If the character does not exist within the string then return false
            	// Checks for both upper and lower case characters
                return false;
		}
        return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		long gigasecond = 1000000000L;
		LocalDateTime adjDate;
		// Method expects the Temporal given object to be either a LocalDateTime or
		// LocalDate object
		if(given instanceof LocalDateTime) {
			// Cast Temporal as LocalDateTime object if given is an instanceof LocalDateTime
			// LocalDateTime has yrs, mths, days, hour, min, sec
			adjDate = (LocalDateTime) given;
		} else {
			// LocalDateTime reference variable is assigned a LocalDate object with
			// a specified hours, min, sec 
			adjDate = ((LocalDate) given).atTime(0, 0, 0);
		}
		// Add a gigasecond to the LocalDateTime objects
		return adjDate.plusSeconds(gigasecond);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		int total = 0;
		Arrays.sort(set);
		Set<Integer> setOfVals = new HashSet<Integer>();
		int count = 0;
		// Loop through the integer array set
		for(int j=0; j<set.length; j++) {
			// Loop starting from a set element, incrementing by the value of that element,
			// terminating the loop when the index is greater than or equal to the original 
			// value
			for(int n=set[j]; n < i; n += set[j]) {
				// Add the value to a set
				setOfVals.add(n);
			}
		}
		// Return the sum of the set elements
		return setOfVals.stream().mapToInt(Integer::intValue).sum();
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		if(string.length() <= 1) {
			// Luhn formula only works on serial numbers with length > 1
			return false;
		} else {
			int total = 0;
			// Remove all whitespace characters from the input string
			String[] isbn = string.replaceAll("\\s+", "").split("");
			int[] arr = new int[isbn.length];
			try {
				// Attempt to cast all of the strings in the string array to int and place
				// them in the int array
				for(int i=0; i<isbn.length;i++) {
					arr[i] = Integer.parseInt(isbn[i]);
				}
			} catch (NumberFormatException e) {
				System.out.println("Cannot convert non-numerical string to int");
				return false;
			}
			// Loop through the int array and increment a total value by the calculated 
			// values
			for(int j=arr.length-1; j >= 0; j--) {
				if(((arr.length - 1 - j) % 2 == 1)) {
					// Get every second number starting from the right
					if((arr[j] * 2) > 9){
						// If doubling that value causes it to go above 9, subtract 
						// 9 from that number
						total += ((arr[j] * 2) - 9);
					} else {
						// Otherwise increment total by the number * 2
						total += arr[j] * 2;
					}
				} else {
					// Add the other numbers to the total
					total += arr[j] ;
				}	
			}
			if (total % 10 == 0) {
				// If the total calculated is divisible by 10, cleanly, then the serial 
				// number is valid by the Luhn formula
				return true;
			}
		}
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		// Remove all punctuation and white space
		String[] words = string.split("([.,!?:;'\\\"]|\\s)");
		try {
			// The 3rd word in the sentence is the first value
			int valOne = Integer.parseInt(words[2]);
			// The last word in the sentence is the second value
			int valTwo = Integer.parseInt(words[words.length-1]);
			// The 4th word is the operator
			switch(words[3]) {
				case "plus": return valOne + valTwo;
				case "minus": return valOne - valTwo;
				case "divided": return valOne / valTwo;
				case "multiplied": return valOne * valTwo;
			}
				
		} catch(NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 0;
	}
	
}
