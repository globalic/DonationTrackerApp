package rosieblair.donationtracker.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 * Tests checkEmail method in UserDBHelper class
 * @author Michelle Tzeng
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDBHelperTest3 {

    private static final int TIMEOUT = 200;

    private boolean testResult;
    private final UserDBHelper testDB = mock(UserDBHelper.class);


    // Branch Coverage: username == null
    // Expected: false
    @Test (timeout = TIMEOUT)
    public void testNullEmail() {
        testResult = testDB.checkEmail("null");
        assertFalse(testResult);
    }

    // Branch Coverage: username != null, exists
    // Expected: count > 0 --> true
    @Test (timeout = TIMEOUT)
    public void testExistingEmail() {
        when(testDB.checkEmail("existingEmail")).thenReturn(true);
        testResult = testDB.checkEmail("existingEmail");
        assertTrue(testResult);
    }

    // Branch Coverage: username != null, does not exist
    // Expected: count > 0 --> false
    @Test (timeout = TIMEOUT)
    public void testNewEmail() {
        when(testDB.checkEmail("newEmail")).thenReturn(false);
        testResult = testDB.checkEmail("newEmail");
        assertFalse(testResult);
    }
}