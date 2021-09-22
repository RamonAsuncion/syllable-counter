package com.ramonasuncion;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Syllables {
    public static void main(String[] args) {
        String word = null;
        boolean checkDict = false;
        int syllableCount = 0;

        for (String arg : args) {
            word = arg;
            System.out.println("Debug arguments: " + word);
        }

        // Has one argument and gets the syllable count for the words.
        if (!(args.length == 0)) {
            word = args[0];
            syllableCount = getSyllableCount(word);
        }

        // Has more than one argument.
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.equals("-d")) {
                    checkDict = checkIfWordIsInDictionary(word);
                }
            }
        }
        
        System.out.println("Word: " + word);
        System.out.println("Syllable count: " + syllableCount);
        System.out.println("Is the word in the dict: " + checkDict);
    }

    public static int getSyllableCount(String word) {
        int syllableCount = 0;
        String lowercaseWord = word.toLowerCase();

        if (wordIsEmpty(word)) {
            return 0;
        }

        Pattern vowel = Pattern.compile("[aeiouy]+");
        Matcher vowelMatch = vowel.matcher(lowercaseWord);
        while (vowelMatch.find()){
            syllableCount++;
        }

        Pattern doesNotStartWithY = Pattern.compile("\by");
        Matcher matchRegexForY = doesNotStartWithY.matcher(lowercaseWord);
        while (matchRegexForY.find()) {
            syllableCount++;
        }

        Pattern endsWithE = Pattern.compile("(e\b)");
        Matcher matchEndsWithE = endsWithE.matcher(lowercaseWord);
        while (matchEndsWithE.find()) {
            syllableCount--;
        }

        try {
            InputStream triphthongs = Syllables.class.getResourceAsStream("/triphthongs.txt");
            // File triphthongs = new File("/triphthongs.txt");
            BufferedReader triphthongsFile = new BufferedReader(new InputStreamReader(triphthongs));
            //BufferedReader triphthongsFile = new BufferedReader(new FileReader(triphthongs));

            String contentsFromTriphthongsFile;
            while ((contentsFromTriphthongsFile = triphthongsFile.readLine()) != null) {
                Pattern readFilePattern = Pattern.compile(contentsFromTriphthongsFile);
                Matcher matchFilePattern = readFilePattern.matcher(lowercaseWord);
                while (matchFilePattern.find()) {
                    syllableCount--;
                }
            }
        } catch (IOException ex) {
            System.exit(0);
        }

        try {
            InputStream diphthongs = Syllables.class.getResourceAsStream("/diphthongs.txt");
            BufferedReader diphthongsFile = new BufferedReader(new InputStreamReader(diphthongs));

            String contentsFromDiphthongsFile;

            while ((contentsFromDiphthongsFile = diphthongsFile.readLine()) != null) {
                Pattern readFilePattern = Pattern.compile(contentsFromDiphthongsFile);
                Matcher matchFilePattern = readFilePattern.matcher(lowercaseWord);
                while (matchFilePattern.find()) {
                    syllableCount--;
                }
            }
        } catch (IOException ex) {
            System.exit(0);
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

        if (syllableCount == 0) {
            syllableCount++;
        }

        return syllableCount;
    }

    // Function to check if the word that is being passed into the program is a null or empty.
    private static boolean wordIsEmpty(String word) {
        return word == null || word.length() == 0;
    }

    // Loads up the dictionary txt file into memory and checks if the file contains the word the user passes through
    public static boolean checkIfWordIsInDictionary(String word) {
        String noCaseMatter = word.toLowerCase();
        String currentLine;

        ArrayList<String> captureDictionary = new ArrayList<>();

        try{
            BufferedReader fileReader = new BufferedReader(new FileReader("english.txt"));
            while ((currentLine = fileReader.readLine()) != null) {
                captureDictionary.add(currentLine);
            }
        } catch (Exception e) {
            System.out.println("The english.txt file is missing");
            System.exit(0);
        }

        return captureDictionary.contains(noCaseMatter);
    }
}