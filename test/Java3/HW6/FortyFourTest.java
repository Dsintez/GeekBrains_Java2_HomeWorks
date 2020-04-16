package Java3.HW6;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class FortyFourTest {
    private static FortyFour fortyFour = null;

    private int[] res;
    private int[] array;

    public FortyFourTest(int[] array, int[] res) {
        this.array = array;
        this.res=res;
    }

    @Parameterized.Parameters
    public static Collection abracadabra() {
        return Arrays.asList(new Object[][]{
                        {new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{5, 6, 7}},
                        {new int[]{3, 2, 23, 32, 54, 345, 4}, new int[]{}},
                        {new int[]{4, 2, 4, 4, 9, 2}, new int[]{9, 2}}
                }
        );
    }

    @Test (expected = RuntimeException.class)
    public void fourTestHard() {
        fortyFour.postLastFour(new int[]{0, 0, 0, 0, 0});
    }


    @Test
    public void paramTest() {
        Assert.assertArrayEquals(res, fortyFour.postLastFour(array));
    }

    @Before
    public void init() {
        System.out.println("init fortyFour");
        fortyFour = new FortyFour();
    }

    @After
    public void tearDown() throws Exception {
        fortyFour = null;
    }
}
