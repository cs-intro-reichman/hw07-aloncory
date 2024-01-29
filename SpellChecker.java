
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}
	// Returns the input string , excluding it's first character
	public static String tail(String str) {
		return str.substring(1);
	}

    // This recursive function accepts two strings as input. 
	// Returns the minimum edit distance between these two words, as an integer.
	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase(); // Lowercases the word
		word2 = word2.toLowerCase(); // Lowercases the word
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} else { 
			return (1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))), levenshtein(tail(word1), tail(word2))));
		}
	}

	// Reads a file that contains the 3,000 most frequently used words in English , and saves the words in an array
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000]; // Building an array of 3,000 strings
		In in = new In(fileName); // Reads the file of the dictionary
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine(); // Reads each word from the file, and stores it in the array
		}
		return dictionary; // Returns the dictionary array
	}

	// Takes a string as an input and determine its presence in the dictionary
	// Returns true if the word is found within the dictionary array, and false if it is not
	public static boolean existInDictionary(String word, String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equals(word)) {
				return true;
			}
		}
		return false;
	}

    // Receives a word, a threshold value for distance, and a dictionary as inputs.
    // It returns the word from the dictionary that most closely resembles the given word
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase(); // Lowercases the given word
		// This loop calculates the edit distance between the given word and each word in the dictionary. 
        // Identifies the word with the minimum distance, checks whether this distance exceeds the given threshold, and proceeds with
        // the appropriate action based on this evaluation.
		if (existInDictionary(word, dictionary)) {
			return word;
		}
		for (String value: dictionary) {
			int min = levenshtein(word, value);
			if ((min <= threshold) && (value.length() <= word.length())) {
				return value;
			}
		}
		// If the smallest distance found is greater than the threshold value, this indicates that no word in the dictionary is sufficiently similar to the given
        // word. In this case, the function returns the original word. 
		return word;
	}

}
