package jp.ne.sakura.uhideyuki.jatcoder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrTest {
    @Test
    void zAlgorithm() {
        final int[] z = Str.zAlgorithm("aaabaaaab");
        final int[] expected = new int[]{9, 2, 1, 0, 3, 4, 2, 1, 0};
        assertArrayEquals(expected, z);
    }
}
