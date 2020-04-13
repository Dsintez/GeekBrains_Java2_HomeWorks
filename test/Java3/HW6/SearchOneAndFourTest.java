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
public class SearchOneAndFourTest {
    private static SearchOneAndFour searchOneAndFour = null;

    private int[] array;
    private boolean res;

    public SearchOneAndFourTest(int[] array, boolean res) {
        this.array = array;
        this.res = res;
    }

    @org.junit.runners.Parameterized.Parameters
    public static Collection abracadabra() {
        return Arrays.asList(new Object[][]{
            {new int[]{1, 2, 3, 4, 5, 6, 7}, true},
            {new int[]{3, 2, 23, 32, 54, 345, 4}, false},
            {new int[]{4, 2, 4, 4, 9, 2}, false}
        });
    }

    @Test
    public void paramTest() {
        Assert.assertEquals(res, searchOneAndFour.search(array));
    }

    @Before
    public void init() {
        System.out.println("init searchOneAndFour");
        searchOneAndFour = new SearchOneAndFour();
    }

    @After
    public void tearDown() throws Exception {
        searchOneAndFour = null;
    }
}
