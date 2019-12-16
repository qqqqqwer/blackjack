package Tests;

import PSP.Player;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void BetShouldReturnTrueWithCorrectNumber() {
        Boolean actual = new Player().bet(10);
        Assert.assertEquals(true, actual);
    }

    @Test
    public void BetShouldReturnFalseWithTooBigNumber() {
        Boolean actual = new Player().bet(1000);
        Assert.assertEquals(false, actual);
    }

    @Test
    public void BetShouldReturnFalseWithNegativeNumber() {
        Boolean actual = new Player().bet(-8);
        Assert.assertEquals(false, actual);
    }
}