/* *****************************************
 * Author: Ramon Asuncion
 *
 * Project: SyllablesAlgorithm.java
 * Package: com.ramonasuncion
 * Class: SyllablesAlgorithm
 *
 * Description:
 *
 * ****************************************
 */
package com.ramonasuncion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyllablesAlgorithm {

    /** A list of triphtongs */
    private static final String[] TRIPHTHONGS = new String[]{
            "eau", "iou", "ieu"
    };

    /** A list of diphthongs */
    private static final String[] DIPHTHONGS = new String[]{
            "aa", "ai", "au", "ay", "ea", "ee", "ei", "eu", "ey",
            "ie", "io", "oa", "oe", "oi", "oo", "ou", "oy", "ue", "ui"
    };

    /** A list of consonants */
    private static final char[] CONSONANT = new char[] {
                'b', 'c', 'd', 'f', 'g', 'j', 'k', 'l', 'm', 'n',
            'p', 'q', 's', 't', 'v', 'x', 'z', 'h', 'r', 'w', 'y'
    };

    /**
     *  Check if the user is passing in an empty word.
     *
     * @param word the user chose to use.
     * @return a boolean if the word is empty or not.
     */
    private static boolean wordEmpty(String word) {
        return word == null || word.length() == 0;
    }

    /**
     * Get the syllable count from the sentence.
     *
     * @param word the user chose to use.
     * @return number of syllables.
     */
    public static int countNumberOfSyllables(String word) {
        int syllableCount = 0;

        // Checks if there is no words being based in.
        if (wordEmpty(word)) return 0;

        // Make the word lowercase.
        word = word.toLowerCase();

        // Check how many vowels are in the words.
        Pattern vowels = Pattern.compile("[aeiouy]+");
        Matcher vowelMatch = vowels.matcher(word);
        while (vowelMatch.find()) {
            syllableCount++;
        }

        // Check if the word ends with e (silent) subtract a syllable.
        if (word.endsWith("e") || word.charAt(word.length() - 2) == 'e') {
            syllableCount--;
        }

        // Y makes the sound of a vowel add a syllable.
        if (word.contains("y") && !word.startsWith("y")) {
            syllableCount++;
        }

        // The word contains a triphthong subtract a syllable..
        for (String triphthong : TRIPHTHONGS) {
            if (word.contains((triphthong))) {
                syllableCount--;
            }
        }

        // The word contains a diphthong subtract a syllable.
        for (String diphthong : DIPHTHONGS) {
            if (word.contains((diphthong))) {
                syllableCount--;
            }
        }

        // If the word ends with le or les, add 1 only if the letter before the le is a consonant
        if (word.endsWith("le") || word.endsWith("les")) {
            if (compareConsonant(word, word.length() - 2) ||
                    compareConsonant(word, word.length() - 3)) {
                syllableCount++;
            }
        }

        // Word starts with io, add one to the syllable count.
        if (word.startsWith("io")) {
            syllableCount++;
        }

        // If the word ends with ee or ie, add 1 to the syllable count.
        if (word.endsWith("ee") || word.endsWith("ie")) {
            syllableCount++;
        }

        // If the syllable counter is 0 equal the syllable count to 1.
        if (syllableCount <= 0) syllableCount = 1;

        return syllableCount;
    }


    /**
     * Helper function to remove the special characters from a word.
     *
     * @param word the user chose to use.
     * @return the new string without characters.
     */
    public static String removeSpecialCharacters(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }


    /**
     * Helper function to compare a specified character to the constants.
     *
     * @param word the user chose to use.
     * @param index the specified index of the character.
     * @return a boolean if the match was found.
     */
    private static boolean compareConsonant(String word, int index) {
        // Check if there is a word.
        if (word == null || word.length() == 0) { return false; }

        // Get the character value based on the specified index.
        char character = word.charAt(index);

        // Check if the character is in the constant array.
        for (char consonant : CONSONANT) {
            if (character == consonant) {
                return true;
            }
        }
        // Default: return value of false.
        return false;
    }
}

