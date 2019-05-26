import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenomicsTest {

    @Test
    void optBottomUp() {
        String[] dict = {"AGT", "CA", "CAG", "GTC", "TC", "TCA", "TCC"};
        String[] dict2 = {"ab", "aa", "bb", "bbb", "aba", "bbba", "aaaabb", "a", "aaababbbaaababbbabaaa", "aababbbabaaaaabbbababbb", "aaababbbaaababbbabaaaaabbbababbb", "b"};
        String[] dict3 = {"x", "xx", "xd"};
        long k = Genomics.optBottomUp("CAGTCCAGTCAGTC", dict);
        long k2 = Genomics.optBottomUp("CAGTCCAGBTCAGTC", dict);
        long k3 = Genomics.optBottomUp("CAGTCCAGBTCAGTCB", dict);
        long k4 = Genomics.optBottomUp("aaababbbaaababbbabaaaaabbbababbb", dict2);
        long k5 = Genomics.optBottomUp("xxxxxdxxxdxxxxxxdxxxdxxxxxxxxdxxxxxxxxdxxxxxxxxxdxxxxxxxxxx", dict3);

        assertEquals(4, k);
        assertEquals(0, k2);
        assertEquals(0, k3);
        assertEquals(25541, k4);
        assertEquals(16800, k5);
    }
}