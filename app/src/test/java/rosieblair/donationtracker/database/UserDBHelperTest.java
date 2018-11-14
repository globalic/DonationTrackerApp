package rosieblair.donationtracker.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 * Tests checkUsername method in UserDBHelper class
 * @author Sabrina Wilson
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDBHelperTest {

    private static final int TIMEOUT = 200;

    private boolean testResult;
    private final UserDBHelper testDB = mock(UserDBHelper.class);


    // Branch Coverage: username == null
    // Expected: false
    @Test (timeout = TIMEOUT)
    public void testNullUsername() {
        testResult = testDB.checkUsername("null");
        assertFalse(testResult);
    }

    // Branch Coverage: username != null, exists
    // Expected: count > 0 --> true
    @Test (timeout = TIMEOUT)
    public void testExistingUsername() {
        when(testDB.checkUsername("existingName")).thenReturn(true);
        testResult = testDB.checkUsername("existingName");
        assertTrue(testResult);
    }

    // Branch Coverage: username != null, does not exist
    // Expected: count > 0 --> false
    @Test (timeout = TIMEOUT)
    public void testNewUsername() {
        when(testDB.checkUsername("newName")).thenReturn(false);
        testResult = testDB.checkUsername("newName");
        assertFalse(testResult);
    }
}