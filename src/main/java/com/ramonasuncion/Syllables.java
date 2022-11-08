/* *****************************************
 * Author: Ramon Asuncion
 *
 * Project: Syllables.java
 * Package: com.ramonasuncion
 * Class: Syllables
 *
 * Description:
 *
 * ****************************************
 */
package com.ramonasuncion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main Program
 */
public class Syllables {

    /** Syllable count number */
    private int syllableCount;

    /** Total characters in a word */
    private int totalCharacters;

    /** Total words in a sentence */
    private int totalWords;

    public Syllables() {
        this.syllableCount = 0;
        this.totalCharacters = 0;
        this.totalWords = 0;
    }

    public static void main(String[] args) {
        Syllables syllable = new Syllables();

        String word;
        boolean checkDictionary;

        // Check if there are NO arguments being passed in or more than one.
        if (args.length == 0 || (args.length > 1 && !args[1].equals("-d"))) {
            System.out.println("usage: Syllables <English word>");
            return;
        }

        // Has one argument and gets the syllable count for the words.
        word = SyllablesAlgorithm.removeSpecialCharacters(args[0]);

        // Get the number of syllables.
        syllable.syllableCount = SyllablesAlgorithm.countNumberOfSyllables(word);

        // Get the character count.
        syllable.totalCharacters = word.length();

        // Get the word count.
        syllable.totalWords = word.split(" ").length;

        // Statistics on the word.
        printResults(word,
                syllable.syllableCount,
                syllable.totalCharacters,
                syllable.totalWords);

        // Check if the word is in the English dictionary if the user uses -d flag.
        for (String arg : args) {
            if (arg.equals("-d")) {
                checkDictionary = wordInDictionary(word);
                System.out.print("In dictionary   : ");
                System.out.println(checkDictionary ? "Yes" : "No");
            }
        }
    }

    /**
     * Print result to console.
     *
     * @param word the user chose to use.
     * @param syllableCount the number of syllables
     * @param totalCharacters the number of characters
     * @param totalWords the number of words in a sentence
     */
    private static void printResults(String word, int syllableCount, int totalCharacters, int totalWords) {
        System.out.println("Word            : " + word);
        System.out.println("Syllable count  : " + syllableCount);
        System.out.println("Word count      : " + totalWords);
        System.out.println("Total characters: " + totalCharacters);
    }


    /**
     * Loads up the dictionary txt file into memory and checks if the file contains the word.
     *
     * @param word the user chose to use.
     * @return a string determining if the word is in the English dictionary..
     */
    private static boolean wordInDictionary(String word) {
        String currentLine;
        ArrayList<String> captureDictionary = new ArrayList<>();
        BufferedReader fileReader;

        try {
            fileReader = new BufferedReader(new FileReader("english.txt"));
            while ((currentLine = fileReader.readLine()) != null) {
                captureDictionary.add(currentLine);
            }
        } catch (IOException ex) {
            System.out.println("Could not read the english.txt file.");
            System.exit(0);
        }

        return captureDictionary.contains(word.toLowerCase());
    }
}
