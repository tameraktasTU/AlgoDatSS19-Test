
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class RowOfBowlsTest {
    int getDiffFromSequence(Iterable<Integer> seq, int[] bowls) {
        int a = 0, b = 0;
        boolean p = true;

        for (int i : seq) {
            if(p) a += bowls[i];
            else b += bowls[i];

            p = !p;
        }
        return a - b;
    }

    @Test
    void maxGain5000Elements() {
        int k = 5000;
        int[] bowls = new int[k];
        for (int i = 0; i < k; i++) bowls[i] = (int)Math.random();

        RowOfBowls row = new RowOfBowls();

        assertTimeout(ofMillis(1000), () -> {
            row.maxGain(bowls);
        });
    }

    @Test
    void maxGainRecursive(){
        RowOfBowls row = new RowOfBowls();

        int[] bowls = {4, 7, 2, 3};
        int[] bowls2 = {3, 4, 1, 2, 8, 5};
        int[] bowls3 = {15, 332, 4, 232, 5, 7, 3, 7, 8, 9, 2, 54, 2, 5, 3, 7, 9, 2, 5, 3, 8, 9, 0, 4, 1, 3, 68, 9};
        int[] bowls4 = {45, 19992, 239, 949493, 923932, 8458385, 18238238, 95951};
        int[] bowls5 = {3, 4, 1, 2, 8, 5, 4, 7, 8, 5, 4, 8, 4, 3, 8, 6, 45, 8, 345};

        assertEquals(row.maxGainRecursive(bowls),4);
        assertEquals(row.maxGainRecursive(bowls2),1);
        assertEquals(row.maxGainRecursive(bowls3),550);
        assertEquals(row.maxGainRecursive(bowls4),9638633);
        assertEquals(row.maxGainRecursive(bowls5),308);
    }
    @Test
    void maxGain(){
        RowOfBowls row = new RowOfBowls();

        int[] bowls = {4, 7, 2, 3};
        int[] bowls2 = {3, 4, 1, 2, 8, 5};
        int[] bowls3 = {15, 332, 4, 232, 5, 7, 3, 7, 8, 9, 2, 54, 2, 5, 3, 7, 9, 2, 5, 3, 8, 9, 0, 4, 1, 3, 68, 9};
        int[] bowls4 = {45, 19992, 239, 949493, 923932, 8458385, 18238238, 95951};
        int[] bowls5 = {3, 4, 1, 2, 8, 5, 4, 7, 8, 5, 4, 8, 4, 3, 8, 6, 45, 8, 345};

        assertEquals(row.maxGain(bowls),4);
        assertEquals(row.maxGain(bowls2),1);
        assertEquals(row.maxGain(bowls3),550);
        assertEquals(row.maxGain(bowls4),9638633);
        assertEquals(row.maxGain(bowls5),308);
    }

    @Test
    void optimalSequence(){
        RowOfBowls row = new RowOfBowls();
        int[] bowls = {19, 8, 2, 8, 7, 0, 15, 4, 11, 19, 3, 6, 6, 12, 12, 4, 16, 15, 3, 4};
        int[] bowls2 = {3, 4, 1, 2, 8, 5};
        int[] bowls3 = {10, 34, 0, 123, 95, 12, 444, 666, 69, 1234, 77, 66, 0, 1, 2, 3, 4, 1, 1, 1, 100, 3, 9, 6, 1, 6, 9, 5, 9};
        int result = row.maxGain(bowls);

        Iterable<Integer> list = row.optimalSequence();
        int actualResult = getDiffFromSequence(list, bowls);
        assertEquals(20, result);
        assertEquals(result, actualResult);


        int result2 = row.maxGain(bowls2);
        list = row.optimalSequence();
        int actualResult2 = getDiffFromSequence(list, bowls2);
        assertEquals(1, result2);
        assertEquals(result2, actualResult2);


        int result3 = row.maxGain(bowls3);
        list = row.optimalSequence();
        int actualResult3 = getDiffFromSequence(list, bowls3);
        assertEquals(-1331, result3);
        assertEquals(result3, actualResult3);
    }
}

