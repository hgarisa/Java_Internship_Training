package Deep_Threading.Cache_Locality;

import java.util.concurrent.ThreadLocalRandom;
/**
 * CACHE LOCALITY DEMO (row-major vs "column-major" access in Java)
 * ----------------------------------------------------------------
 * Key points about Java int[][] layout:
 * - int[][] is NOT a single flat block. It's an array of int[] rows.
 * - m[i] is a separate int[] object (stored elsewhere in memory).
 * - Row-wise traversal touches contiguous elements inside one int[] (good locality).
 * - Column-wise traversal jumps across many different int[] objects (poor locality).
 *
 * What we do here:
 * 1) Build a ROWS x COLS matrix and fill it with small random ints.
 * 2) Sum all elements row-by-row (cache-friendly).
 * 3) Sum all elements "column-by-column" (cache-hostile for Java's layout).
 * 4) Compare timings printed in a single REPORT_NOTE line.
 *
 * Tips:
 * - Run the program a couple of times; JIT warm-up can change numbers.
 * - Increase ROWS/COLS if your machine is very fast to make the gap clearer.
 */
public class CacheLocality
{
    // Matrix dimensions (tweak to see larger effects)
    static final int ROWS = 9_000, COLS = 512;

    public static void main(String[] args) {
        // 1) Allocate the matrix (array of ROWS references, each pointing to a COLS-length int[]).
        int[][] m = new int[ROWS][COLS];

        // 2) Initialize: fill each row with small random values.
        //    This loop itself is row-wise (i outside, j inside), which is cache-friendly.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                m[i][j] = ThreadLocalRandom.current().nextInt(10);
            }
        }

        // 3) Measure a row-wise sum:
        //    - Inner loop walks along a single int[] row.
        //    - Elements are contiguous in memory -> CPU prefetch works well -> fewer cache misses.
        long rowMs = time(() -> sumRow(m));

        // 4) Measure a "column-wise" sum:
        //    - We fix a column j and iterate i over rows.
        //    - Each step touches a *different* int[] (m[i]) -> jumps around memory.
        //    - This causes more cache misses and TLB pressure -> usually slower.
        long colMs = time(() -> sumCol(m));


        System.out.printf("REPORT_NOTE: CacheLocality rowMs=%d colMs=%d%n", rowMs, colMs);
    }

    /**
     * Utility to time a Runnable.
     * Starts a wall-clock timer, runs the code, returns elapsed milliseconds.
     */
    static long time(Runnable r) {
        long t = System.currentTimeMillis();
        r.run();
        return System.currentTimeMillis() - t;
    }

    /**
     * Row-wise summation:
     * Outer loop = rows (i), inner loop = columns within that row (j).
     * Access pattern: m[i][0], m[i][1], m[i][2], ... (contiguous in the same int[]).
     * Good spatial locality -> fast on modern CPUs.
     */
    static long sumRow(int[][] m) {
        long s = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                s += m[i][j];
            }
        }
        return s; // value unused; computed to prevent JIT from removing the loops
    }

    /**
     * "Column-wise" summation (in quotes because Java isn't column-major):
     * Outer loop = columns (j), inner loop = rows (i).
     * Access pattern for fixed j: m[0][j], m[1][j], m[2][j], ...
     * Each step dereferences a different row object m[i] (pointer chasing),
     * so we bounce across memory and defeat cache prefetch as well as locality.
     * This typically takes noticeably longer than sumRow(...).
     */
    static long sumCol(int[][] m) {
        long s = 0;
        int C = m[0].length;           // number of columns in each row
        for (int j = 0; j < C; j++) {  // fix a column
            for (int i = 0; i < m.length; i++) {
                s += m[i][j];          // jump to a different row object each iteration
            }
        }
        return s; // value unused; computed to prevent JIT from removing the loops
    }
}
