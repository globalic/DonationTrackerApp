package rosieblair.donationtracker.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Tests checkUserPass method in the UserDatabaseHelper class
 * @author Michelle Yang
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDBHelperTest2 {
    private static final int TIMEOUT = 200;

    private boolean result;
    private final UserDBHelper mockUserDB = mock(UserDBHelper.class);

    @Test (timeout = TIMEOUT)
    public void checkIfUserNull() {
        result = mockUserDB.checkUserPass(null, "not null");
        assertFalse(result);
    }

    @Test (timeout = TIMEOUT)
    public void checkIfPassNull() {
        result = mockUserDB.checkUserPass("not null", null);
        assertFalse(result);
    }

    @Test (timeout = TIMEOUT)
    public void checkIfPassForUser() {
        when(mockUserDB.checkUserPass("Bob", "correctpw")).thenReturn(true);
        result = mockUserDB.checkUserPass("Bob", "correctpw");
        assertTrue(result);
    }

    @Test (timeout = TIMEOUT)
    public void checkIfPassNotForUser() {
        when(mockUserDB.checkUserPass("Bob", "incorrectpw")).thenReturn(false);
        result = mockUserDB.checkUserPass("Bob", "incorrectpw");
        assertFalse(result);
    }
}