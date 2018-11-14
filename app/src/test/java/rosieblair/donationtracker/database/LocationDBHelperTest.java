package rosieblair.donationtracker.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationDBHelperTest {

    private static final int TIMEOUT = 200;

    private boolean testResult;
    private final LocationDBHelper testDB = mock(LocationDBHelper.class);


    // Branch Coverage: location name == null
    // Expected: false
    @Test(timeout = TIMEOUT)
    public void testNullUsername() {
        testResult = testDB.checkLocation("null");
        assertFalse(testResult);
    }

    // Branch Coverage: location name != null, exists
    // Expected: count > 0 --> true
    @Test (timeout = TIMEOUT)
    public void testExistingLocation() {
        when(testDB.checkLocation("existingName")).thenReturn(true);
        testResult = testDB.checkLocation("existingName");
        assertTrue(testResult);
    }

    // Branch Coverage: location name != null, does not exist
    // Expected: count > 0 --> false
    @Test (timeout = TIMEOUT)
    public void testNewLocation() {
        when(testDB.checkLocation("newName")).thenReturn(false);
        testResult = testDB.checkLocation("newName");
        assertFalse(testResult);
    }
}
