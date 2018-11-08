package rosieblair.donationtracker.database;

import org.junit.Before;
import org.junit.Test;
//import android.content.Context;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;

import rosieblair.donationtracker.model.Location;

import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import static android.support.test.InstrumentationRegistry.getContext;
//import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


import static org.junit.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class LocationDBHelperTest {
//    private static final int TIMEOUT = 200;
//    private LocationDBHelper locDBhelper;
    @Before
    public void setUp() throws Exception {
//        locDBhelper = new LocationDBHelper(getContext());
    }

    @Test
    public void getLocationByName() {
        String test = null;
//        assertNull(getLocationByName((String)test),null);
        LocationDBHelper locDBhelper = mock(LocationDBHelper.class);
        assertNull(locDBhelper.getLocationByName(test));

        Location l1 = new Location();
        l1.setName("Loc 1");
        locDBhelper.addLocation(l1);
        System.out.println(locDBhelper.getLocationByName("Loc 1"));
        assertEquals(locDBhelper.getLocationByName("Loc 1"),l1);


    }
}