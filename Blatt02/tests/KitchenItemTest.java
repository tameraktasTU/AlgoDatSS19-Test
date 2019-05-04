import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KitchenItemTest {

    @Test
    void testKitchenItem() {
        KitchenItem kT5 = new KitchenItem("Teller", 5);
        KitchenItem kL3P = new KitchenItem("Löffel", 3, "Paul");
        KitchenItem Kc = new KitchenItem(kL3P);

        assertTrue(kL3P.equals(Kc));
        assertEquals(kT5.getQuantity(), 5);
        assertEquals(kT5.item, "Teller");

        assertEquals(kL3P.getQuantity(), 3);
        assertEquals(kL3P.item, "Löffel");

        assertEquals("shared", kT5.getOwner());
    }

    @Test
    void testCopyConstructor() {
        KitchenItem k1 = new KitchenItem("Teller", 5);
        KitchenItem k2 = new KitchenItem(k1);
        assertEquals(k1, k2);
        assertEquals(k1.getOwner(), k2.getOwner());
        assertEquals(k1.item, k2.item);
        k2.setOwner("Peter");
        assertEquals("Peter", k2.getOwner());
        assertEquals("shared", k1.getOwner());
    }

    @Test
    void getAndSetOwner() {
        KitchenItem kL3P = new KitchenItem("Löffel", 3, "Paul");
        KitchenItem Kc = new KitchenItem(kL3P);

        assertEquals("Paul", kL3P.getOwner());
        assertEquals("Paul", Kc.getOwner());
        kL3P.setOwner("Peter");
        assertEquals(kL3P.getOwner(), "Peter");
        assertEquals(Kc.getOwner(), "Paul");
    }

    @Test
    void getQuantity() {
    }

    @Test
    void needed() {
    }
}