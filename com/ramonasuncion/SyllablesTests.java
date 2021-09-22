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
        int n = Syllables.getSyllableCount(null);
        assertEquals(0, n);
    }

    @Test
    void emptyWordTest() {
        int n = Syllables.getSyllableCount("");
        assertEquals(0, n);
    }

    @Test
    void displaySyllablesForTestWordsTest() {
        for (String word : TEST_WORDS) {
            System.out.printf("%-12s => %2d %n", word, Syllables.getSyllableCount(word));
        }
    }

    @Test
    void allWordsTest() {
        assertEquals(1, Syllables.getSyllableCount("cake"));
        assertEquals(1, Syllables.getSyllableCount("pie"));
        assertEquals(1, Syllables.getSyllableCount("one"));
        assertEquals(2, Syllables.getSyllableCount("apple"));
        assertEquals(3, Syllables.getSyllableCount("banana"));
        assertEquals(2, Syllables.getSyllableCount("cookie"));
        assertEquals(2, Syllables.getSyllableCount("doughnut"));
        assertEquals(2, Syllables.getSyllableCount("eggplant"));
        assertEquals(2, Syllables.getSyllableCount("brownie"));
        assertEquals(1, Syllables.getSyllableCount("red"));
        assertEquals(2, Syllables.getSyllableCount("yellow"));
        assertEquals(1, Syllables.getSyllableCount("blue"));
        assertEquals(1, Syllables.getSyllableCount("green"));
        assertEquals(2, Syllables.getSyllableCount("purple"));
        assertEquals(2, Syllables.getSyllableCount("orange"));
        assertEquals(2, Syllables.getSyllableCount("violet"));
        assertEquals(2, Syllables.getSyllableCount("cyan"));
        assertEquals(3, Syllables.getSyllableCount("magenta"));
        assertEquals(4, Syllables.getSyllableCount("alligator"));
        assertEquals(4, Syllables.getSyllableCount("independence"));
        assertEquals(4, Syllables.getSyllableCount("ordinary"));
        assertEquals(5, Syllables.getSyllableCount("intimidating"));
        assertEquals(5, Syllables.getSyllableCount("generosity"));
        assertEquals(2, Syllables.getSyllableCount("ion"));
        assertEquals(2, Syllables.getSyllableCount("onion"));
    }
}
