

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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


	// This recursive function receives two inputs: a hashtag (as a String) and a dictionary (an array of String).
    // It prints each word embedded within the hashtag on a separate line.
	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		hashtag = hashtag.toLowerCase(); // Converts the hashtag prefix to lowercase
		// This loop incrementally analyzes prefixes of the hashtag, starting with the first character and
		// gradually extending the length of the prefix.
        for (int i = 1; i <= N; i++) {
			String prefix = hashtag.substring(0, i);
			// Checks if the prefix is a valid word
			if (existInDictionary(prefix, dictionary)) {
				System.out.println(prefix); // Prints the founded word 
				breakHashTag(hashtag.substring(prefix.length()), dictionary); // Recursive call , this time with the hashtag prefix minus the found word
				break; // Stops the loop (This command ensures that the function indeed "cuts" the hashtag and only the words that embedded in the hashtag are printed)
			}
        }
    }

}
