package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevenshteinTest extends Levenshtein {

    @Test
    void getSimilarity() {
        assertEquals(0.75,getSimilarity("ab是否是否是d","abc"));
    }
}