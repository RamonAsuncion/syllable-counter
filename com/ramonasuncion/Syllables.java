package com.ramonasuncion;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: A GUI with JavaFX.
// TODO: Make multiple catch exceptions for FileReading
// TODO: Eventually I can make a regex that finds all the matches I need to compile once.
// TODO: Remove the special characters from the string.
// TODO: Only one word is allowed to be used with the parameter -d

public class Syllables {
    public static void main( String[] args ) {
        String word;
        boolean checkDict;
        int syllableCount = 0;
        int totalCharacters = 0;
        int totalWords = 0;

        // Check if there are NO arguments being passed in.
        if (args.length == 0){
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
        System.out.println("Syllable count: " + syllableCount);
        System.out.println("Word: " + word);
        System.out.println("Word count: " + totalWords);
        System.out.println("Total characters: " + totalCharacters);
    }

    public static int getSyllableCount( String word ) {
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

    public static String[] removeSpecialCharacters( String word ) {
        String newWord = word.replaceAll("[^a-zA-Z0-9]", " ");
        String[] strArray;
        strArray = newWord.split(" ");
        return strArray;
    }

    // Function to check if the word that is being passed into the program is a null or empty.
    private static boolean wordEmpty( String word ) {
        return word == null || word.length() == 0;
    }

    // Loads up the dictionary txt file into memory and checks if the file contains the word the user passes through
    private static boolean wordInDictionary( String word ) {
        String currentLine;
        ArrayList<String> captureDictionary = new ArrayList<>();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("english.txt"));
            while ((currentLine = fileReader.readLine()) != null) {
                captureDictionary.add(currentLine);
            }
        }
        catch (IOException ex) {
            System.out.println("Could not read the english.txt file.");
            System.exit(0);
        }

        return captureDictionary.contains(word.toLowerCase());
    }
}