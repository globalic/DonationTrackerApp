package rosieblair.donationtracker.database;

import static org.mockito.Mockito.mock;
import rosieblair.donationtracker.model.Item;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import rosieblair.donationtracker.model.Location;

//import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class ItemDBHelperTest {

    @Test
    public void getLocationInventory() {
        ItemDBHelper itemDB = mock(ItemDBHelper.class);
        LocationDBHelper locationDB = mock(LocationDBHelper.class);
        Location l1 = new Location();
        locationDB.addLocation(l1);
        l1.setKey(2);
        Item i1 = new Item();
        Item i2 = new Item();

        i1.setItemKey(2);
        i2.setItemKey(2);

        itemDB.addItem(i1);
        itemDB.addItem(i2);

        List<Item> empty = new ArrayList<>();
        List<Item> full = new ArrayList<>();
        full.add(i1);
        full.add(i2);
        System.out.println(full);
        System.out.println(itemDB.itemList());
        //when(itemDB.getLocationInventory(1)).thenReturn(empty);
        //when(itemDB.getLocationInventory(2)).thenReturn(full);

        assertEquals(full, itemDB.getLocationInventory(2));
        assertEquals(empty, itemDB.getLocationInventory(1));
        //assertNull(itemDB.getLocationInventory(20));
        //assertNull(itemDB.getLocationInventory(21));
    }
}