import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void ItemAndGetOwner() {
        String itemName = "Kartoffel";
        Item i = new Item(itemName, "Paul");
        assertEquals(i.item, itemName);
        assertEquals(i.getOwner(), "Paul");
    }

    @Test
    void setOwner() {
        String itemName = "Kartoffel";
        Item i = new Item(itemName, "Paul");
        i.setOwner("Peter");
        assertEquals(i.getOwner(), "Peter");
    }

    @Test
    void compareTo() {
        String itemName = "Kartoffel";
        Item i1 = new Item(itemName, "Paul");
        Item i2 = new Item(itemName, "Bert");
        Item i3 = new Item(itemName, "Alfred");

        assertEquals("Alfred".compareTo("Paul"), i3.compareTo(i1) );
        assertEquals("Bert".compareTo("Paul"), i2.compareTo(i1));
        assertEquals("Bert".compareTo("Alfred"), i2.compareTo(i3));
        assertEquals("Bert".compareTo("Bert"), i2.compareTo(i2));
    }

    @Test
    void toString1() {
        String itemName = "Kartoffel";
        Item i = new Item(itemName, "Paul");

        assertEquals(i.toString(), "Paul: Kartoffel");
    }

    @Test
    void equals1() {
        String itemName = "Kartoffel";
        Item i1 = new Item(itemName, "Paul");
        Item i2 = new Item(itemName, "Peter");
        Item i3 = new Item(itemName, "Paul");
        Item i4 = new Item("Brot", "Paul");

        Object o = new Object();

        assertTrue(i1.equals(i1));
        assertTrue(i1.equals(i3));
        assertFalse(i1.equals(i4));
        assertFalse(i1.equals(null));
        assertFalse(i2.equals(o));

    }
}