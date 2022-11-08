/* *****************************************
 * Author: Ramon Asuncion
 *
 * Project: SyllablesTest.java
 * Package: com.ramonasuncion
 * Class: SyllablesTest
 *
 * Description:
 * Test if the syllables algorithm is working as intended.
 * ****************************************
 */

package com.ramonasuncion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SyllablesTest {
    private final String[] TEST_WORDS = new String[]{
            "cake", "pie", "one", "apple", "banana", "cookie", "doughnut", "eggplant", "brownie",
            "red", "yellow", "blue", "green", "purple", "orange", "violet", "cyan", "magenta",
            "alligator", "independence", "ordinary", "intimidating", "generosity", "ion", "onion"
    };

    @Test
    void nullWordTest() {
        int n = SyllablesAlgorithm.countNumberOfSyllables(null);
        assertEquals(0, n);
    }

    @Test
    void emptyWordTest() {
        int n = SyllablesAlgorithm.countNumberOfSyllables("");
        assertEquals(0, n);
    }

    @Test
    void displaySyllablesForTestWordsTest() {
        for (String word : TEST_WORDS) {
            System.out.printf("%-12s => %2d %n", word, SyllablesAlgorithm.countNumberOfSyllables(word));
        }
    }

    @Test
    void allWordsTest() {
        assertEquals(1, SyllablesAlgorithm.countNumberOfSyllables("cake"));
        assertEquals(1, SyllablesAlgorithm.countNumberOfSyllables("pie"));
        assertEquals(1, SyllablesAlgorithm.countNumberOfSyllables("one"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("apple"));
        assertEquals(3, SyllablesAlgorithm.countNumberOfSyllables("banana"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("cookie"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("doughnut"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("eggplant"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("brownie"));
        assertEquals(1, SyllablesAlgorithm.countNumberOfSyllables("red"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("yellow"));
        assertEquals(1, SyllablesAlgorithm.countNumberOfSyllables("blue"));
        assertEquals(1, SyllablesAlgorithm.countNumberOfSyllables("green"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("purple"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("orange"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("violet"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("cyan"));
        assertEquals(3, SyllablesAlgorithm.countNumberOfSyllables("magenta"));
        assertEquals(4, SyllablesAlgorithm.countNumberOfSyllables("alligator"));
        assertEquals(4, SyllablesAlgorithm.countNumberOfSyllables("independence"));
        assertEquals(4, SyllablesAlgorithm.countNumberOfSyllables("ordinary"));
        assertEquals(5, SyllablesAlgorithm.countNumberOfSyllables("intimidating"));
        assertEquals(5, SyllablesAlgorithm.countNumberOfSyllables("generosity"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("ion"));
        assertEquals(2, SyllablesAlgorithm.countNumberOfSyllables("onion"));
    }
}
