package algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevenshteinTest extends Levenshtein {

    @Test
    void getSimilarity() {
        assertEquals(0.75,getSimilarity("<p>2341234</p>","<p>我是</p>"));
    }
}