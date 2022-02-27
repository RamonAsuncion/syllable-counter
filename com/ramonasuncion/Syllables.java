package com.ramonasuncion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main Program
 */
public class Syllables {

    /** Syllable count number */
    private final int syllableCount;

    /** Characters count number */
    private final int totalCharacters;

    public Syllables() {
        this.syllableCount = 0;
        this.totalCharacters = 0;
    }

    /**
     * @return the number of syllables.
     */
    public int getSyllableCount() { return syllableCount; }

    /**
     * @return the number of characters.
     */
    public int getTotalCharacters() { return totalCharacters; }


    public void main(String[] args) {
        Syllables syllable = new Syllables();

        String word;
        boolean checkDict;
        int totalCharacters = 0;
        int totalWords = 0;

        // Check if there are NO arguments being passed in.
        if (args.length == 0) {
            System.out.println("usage: Syllables <English word>");
            System.exit(0);
        }

        // Has one argument and gets the syllable count for the words.
        word = args[0];

        // Check if the word is in the dictionary if the user uses -d.
        for (String arg : args) {
            System.out.println(arg);
            if (arg.equals("-d")) {
                checkDict = wordInDictionary(word);
                System.out.println("Is word in dictionary? " + checkDict);
            }
        }

        // Debugging variables.
        printResults(word, syllable.getSyllableCount(), syllable.getTotalCharacters(), totalWords);
    }

    /**
     * Print result to console.
     *
     * @param word the sentence
     * @param syllableCount the number of syllables
     * @param totalCharacters the number of characters
     * @param totalWords the number of words in a sentence
     */
    private static void printResults(String word, int syllableCount, int totalCharacters, int totalWords) {
        System.out.println("Syllable count: " + syllableCount);
        System.out.println("Word: " + word);
        System.out.println("Word count: " + totalWords);
        System.out.println("Total characters: " + totalCharacters);
    }

    /**
     * Get the syllable count from the sentence.
     *
     * @param word
     * @return
     */
    public static int syllableCount(String word) {
        // Checks if there is no words being based in.
        if (wordEmpty(word)) {
            return 0;
        }

        int syllableCount = 0;
        String lowercaseWord = word.toLowerCase();

        // Check how many vowels are in the words.
        Pattern vowel = Pattern.compile("[aeiouy]+");
        Matcher vowelMatch = vowel.matcher(lowercaseWord);
        while (vowelMatch.find()) {
            syllableCount++;
        }

        // Check if the word ends with e.
        Pattern endsWithE = Pattern.compile("e\b");
        Matcher matchEndsWithE = endsWithE.matcher(lowercaseWord);
        while (matchEndsWithE.find()) {
            syllableCount--;
        }

        // If the word does not start with y.
        Pattern doesNotStartWithY = Pattern.compile("\by");
        Matcher matchRegexForY = doesNotStartWithY.matcher(lowercaseWord);
        while (matchRegexForY.find()) {
            syllableCount++;
        }



        // TODO: I can make triphthongs into a regex pattern.
        try {
            String contentsFromTriphthongsFile;
            BufferedReader triphthongsFile = new BufferedReader(new FileReader("triphthongs.txt"));
            while ((contentsFromTriphthongsFile = triphthongsFile.readLine()) != null) {

                // TODO: Change the variable name since already using it below.
                Pattern triphthongsPattern = Pattern.compile(contentsFromTriphthongsFile);
                Matcher triphthongsMatch = triphthongsPattern.matcher(lowercaseWord);
                while (triphthongsMatch.find()) {
                    syllableCount--;
                }
            }
        } catch (IOException ex) {
            System.out.print(ex);
        }

        try {
            String contentsFromDiphthongsFile;
            BufferedReader diphthongsFile = new BufferedReader(new FileReader("diphthongs.txt"));
            while ((contentsFromDiphthongsFile = diphthongsFile.readLine()) != null) {
                Pattern diphthongsPattern = Pattern.compile(contentsFromDiphthongsFile);
                Matcher diphthongsMatch = diphthongsPattern.matcher(lowercaseWord);
                while (diphthongsMatch.find()) {
                    syllableCount--;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Pattern le = Pattern.compile("(le\\b)");
        Matcher matchLe = le.matcher(lowercaseWord);

        while (matchLe.find()) {
            String endle = lowercaseWord.substring(lowercaseWord.lastIndexOf("le") - 1);

            if (!(endle.startsWith("a") || endle.startsWith("e") || endle.startsWith("i") || endle.startsWith("o") || endle.startsWith("u"))) {
                syllableCount++;
            }
        }

        Pattern les = Pattern.compile("(les\\b)");
        Matcher matchLes = les.matcher(lowercaseWord);

        String lesBeforeConsonant;

        while (matchLes.find()) {
            lesBeforeConsonant = lowercaseWord.substring(lowercaseWord.lastIndexOf("les") - 1);

            if (!(lesBeforeConsonant.startsWith("a") || lesBeforeConsonant.startsWith("e") || lesBeforeConsonant.startsWith("i") || lesBeforeConsonant.startsWith("o") || lesBeforeConsonant.startsWith("u"))) {
                syllableCount++;
            }
        }

        Pattern iO = Pattern.compile("(\\bio)");
        Matcher startsWithIO = iO.matcher(lowercaseWord);

        while (startsWithIO.find()) {
            syllableCount++;
        }

        Pattern eeie = Pattern.compile("(ee\\b|ie\\b)");
        Matcher endsWithEEorIE = eeie.matcher(lowercaseWord);

        while (endsWithEEorIE.find()) {
            syllableCount++;
        }

        // If the syllable counter is 0 equal the syllable count to 0.
        if (syllableCount <= 0) {
            syllableCount = 1;
        }

        return (syllableCount);
    }


    /**
     * Remove the special characters in a sentence.
     *
     * @param word
     * @return a list of the words.
     */
    private static String[] removeSpecialCharacters(String word) {
        return word.replaceAll("[^a-zA-Z0-9]", " ").split(" ");
    }


    /**
     *  Check if the user is passing in an empty word.
     *
     * @param word that
     * @return a boolean if the word is empty or not.
     */
    private static boolean wordEmpty(String word) {
        return word == null || word.length() == 0;
    }

    /**
     * Loads up the dictionary txt file into memory and checks if the file contains the word.
     *
     * @param word
     * @return a boolean if the word is in the dictionary file.
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