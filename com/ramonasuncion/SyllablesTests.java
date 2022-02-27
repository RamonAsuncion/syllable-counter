// This test was not created by me.
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
        int n = Syllables.syllableCount(null);
        assertEquals(0, n);
    }

    @Test
    void emptyWordTest() {
        int n = Syllables.syllableCount("");
        assertEquals(0, n);
    }

    @Test
    void displaySyllablesForTestWordsTest() {
        for (String word : TEST_WORDS) {
            System.out.printf("%-12s => %2d %n", word, Syllables.syllableCount(word));
        }
    }

    @Test
    void allWordsTest() {
        assertEquals(1, Syllables.syllableCount("cake"));
        assertEquals(1, Syllables.syllableCount("pie"));
        assertEquals(1, Syllables.syllableCount("one"));
        assertEquals(2, Syllables.syllableCount("apple"));
        assertEquals(3, Syllables.syllableCount("banana"));
        assertEquals(2, Syllables.syllableCount("cookie"));
        assertEquals(2, Syllables.syllableCount("doughnut"));
        assertEquals(2, Syllables.syllableCount("eggplant"));
        assertEquals(2, Syllables.syllableCount("brownie"));
        assertEquals(1, Syllables.syllableCount("red"));
        assertEquals(2, Syllables.syllableCount("yellow"));
        assertEquals(1, Syllables.syllableCount("blue"));
        assertEquals(1, Syllables.syllableCount("green"));
        assertEquals(2, Syllables.syllableCount("purple"));
        assertEquals(2, Syllables.syllableCount("orange"));
        assertEquals(2, Syllables.syllableCount("violet"));
        assertEquals(2, Syllables.syllableCount("cyan"));
        assertEquals(3, Syllables.syllableCount("magenta"));
        assertEquals(4, Syllables.syllableCount("alligator"));
        assertEquals(4, Syllables.syllableCount("independence"));
        assertEquals(4, Syllables.syllableCount("ordinary"));
        assertEquals(5, Syllables.syllableCount("intimidating"));
        assertEquals(5, Syllables.syllableCount("generosity"));
        assertEquals(2, Syllables.syllableCount("ion"));
        assertEquals(2, Syllables.syllableCount("onion"));
    }
}
