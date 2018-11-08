package rosieblair.donationtracker.database;

import org.junit.Before;
import org.junit.Test;
import android.content.Context;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LocationDBHelperTest {
    private static final int TIMEOUT = 200;
    @Before
    public void setUp() throws Exception {
        LocationDBHelper locDBhelper = new LocationDBHelper(getContext());
    }

    @Test
    public void getLocationByName() {
    }
}