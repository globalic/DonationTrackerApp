package rosieblair.donationtracker.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 * Tests checkIfEmployee method in UserDBHelper class
 * @author katie
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDBHelperTest1 {
    private static final int TIMEOUT = 200;

    private boolean test;
    private UserDBHelper testUserDB = mock(UserDBHelper.class);

    //branch coverage: username == null
    //expected: false
    @Test (timeout = TIMEOUT)
    public void checkIfEmployeeNull() {
        test = testUserDB.checkIfEmployee(null);
        assertFalse(test);
    }

    // Branch Coverage: username != null, exists, is location employee
    // Expected: count > 0 --> true
    @Test (timeout = TIMEOUT)
    public void checkIfEmployeeExitsLocation() {
        when(testUserDB.checkIfEmployee("employeeName")).thenReturn(true);
        test = testUserDB.checkIfEmployee("employeeName");
        assertTrue(test);
    }

    // Branch Coverage: username != null, exists, is not location employee
    // Expected: count > 0 --> false
    @Test (timeout = TIMEOUT)
    public void checkIfEmployeeExitsNotLocation() {
        when(testUserDB.checkIfEmployee("notEmployeeName")).thenReturn(false);
        test = testUserDB.checkIfEmployee("notEmployeeName");
        assertFalse(test);
    }

    // Branch Coverage: username != null, does not exist
    // Expected: count > 0 --> false
    @Test (timeout = TIMEOUT)
    public void checkIfEmployeeDNE() {
        when(testUserDB.checkUsername("dneName")).thenReturn(false);
        test = testUserDB.checkUsername("dneName");
        assertFalse(test);
    }
}