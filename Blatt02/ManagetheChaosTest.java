import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ManagetheChaosTest {

    @Test
    void findSpares() {
        List<KitchenItem> inventar = new ArrayList<>();

        KitchenItem tassen = new KitchenItem("tassen", 6);
        tassen.neededquantity = 4;

        KitchenItem teller = new KitchenItem("teller", 5, "Dennis");
        teller.neededquantity = 4;

        KitchenItem paulsGabel = new KitchenItem( "gabel", 1, "Paul");
        paulsGabel.neededquantity = 1;

        inventar.add(tassen);
        inventar.add(teller);
        inventar.add(paulsGabel);

        ManagetheChaos chaos = new ManagetheChaos(inventar);

        assertEquals(
                (tassen.getQuantity() - tassen.neededquantity)
                + (teller.getQuantity() - teller.neededquantity)
                + (paulsGabel.getQuantity() - paulsGabel.neededquantity)
                , chaos.findSpares().size()
                , "Spares size match to given quantities."
        );

        List<KitchenItem> list = new ArrayList<>();
        list.add(tassen);
        list.add(tassen);
        list.add(teller);
        assertEquals(list, chaos.findSpares(), "Spared are equal to given items.");
    }

    @Test
    void putAway() {
        List<KitchenItem> inventar = new ArrayList<>();

        KitchenItem tassen = new KitchenItem("tassen", 6);
        tassen.neededquantity = 2;

        KitchenItem teller = new KitchenItem("teller", 5, "Dennis");
        teller.neededquantity = 2;

        KitchenItem paulsGabel = new KitchenItem( "gabel", 1, "Paul");
        paulsGabel.neededquantity = 1;

        inventar.add(tassen);
        inventar.add(teller);
        inventar.add(paulsGabel);

        ManagetheChaos chaos = new ManagetheChaos(inventar);
        List<KitchenItem> l = new ArrayList<>();
        for (KitchenItem ki : chaos.allItems) {
            l.add(ki);
        }
        chaos.putAway();
        assertEquals(l, chaos.allItems);

        for (Item i : chaos.cubby) {
            assertTrue(i.intheCubby);
        }

        List<KitchenItem> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            KitchenItem item1 = new KitchenItem("Fill", 5);
            item1.neededquantity = 1;
            list.add(item1);
        }
        ManagetheChaos c1 = new ManagetheChaos(list);
        assertThrows(RuntimeException.class, () -> c1.putAway());

        List<KitchenItem> list2 = new ArrayList<>();
        KitchenItem item2 = new KitchenItem("Fill", 16);
        item2.neededquantity = 0;
        list2.add(item2);
        ManagetheChaos c2 = new ManagetheChaos(list2);
        assertEquals(16, c2.findSpares().size());
        assertThrows(RuntimeException.class, () -> c2.putAway());
    }

    @Test
    void putAwaySmart() {
        List<KitchenItem> inventar = new ArrayList<>();

        KitchenItem tassen = new KitchenItem("tassen", 6);
        tassen.neededquantity = 2;

        KitchenItem teller = new KitchenItem("teller", 5, "Dennis");
        teller.neededquantity = 2;

        KitchenItem paulsGabel = new KitchenItem( "gabel", 1, "Paul");
        paulsGabel.neededquantity = 1;

        inventar.add(tassen);
        inventar.add(teller);
        inventar.add(paulsGabel);

        ManagetheChaos chaos = new ManagetheChaos(inventar);
        List<KitchenItem> l = new ArrayList<>();
        for (KitchenItem ki : chaos.allItems) {
            l.add(ki);
        }
        chaos.putAwaySmart();
        assertEquals(l, chaos.allItems);

        for (Item i : chaos.cubby) {
            assertTrue(i.intheCubby);
        }

        List<KitchenItem> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            KitchenItem item1 = new KitchenItem("Fill", 5);
            item1.neededquantity = 1;
            list.add(item1);
        }
        ManagetheChaos c1 = new ManagetheChaos(list);
        assertThrows(RuntimeException.class, () -> c1.putAwaySmart());

        List<KitchenItem> list2 = new ArrayList<>();
        KitchenItem item2 = new KitchenItem("Fill", 16);
        item2.neededquantity = 0;
        list2.add(item2);
        ManagetheChaos c2 = new ManagetheChaos(list2);
        assertEquals(16, c2.findSpares().size());
        assertThrows(RuntimeException.class, () -> c2.putAwaySmart());
    }

    @Test
    void testReplaceable() {
        List<KitchenItem> list1 = new ArrayList<>();
        KitchenItem tasse = new KitchenItem("tasse", 3);
        KitchenItem teller = new KitchenItem("teller", 4, "Dennis");
        KitchenItem paulsgabel = new KitchenItem( "gabel", 2, "Paul");
        paulsgabel.neededquantity = 1;
        tasse.neededquantity = 1;
        teller.neededquantity = 4;
        list1.add(tasse);
        list1.add(teller);
        list1.add(paulsgabel);

        ManagetheChaos chaos = new ManagetheChaos(list1);

        chaos.putAwaySmart();
        assertTrue(chaos.replaceable(tasse));
        assertFalse(chaos.replaceable(teller));
    }

    @Test
    void testReplaceSmart() {
        ManagetheChaos m = new ManagetheChaos(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> m.replace(null));

        List<KitchenItem> list1 = new ArrayList<>();
        KitchenItem tasse = new KitchenItem("tasse", 3);
        KitchenItem teller = new KitchenItem("teller", 4, "Dennis");
        KitchenItem paulsgabel = new KitchenItem( "gabel", 2, "Paul");
        paulsgabel.neededquantity = 1;
        tasse.neededquantity = 1;
        teller.neededquantity = 4;
        list1.add(tasse);
        list1.add(teller);
        list1.add(paulsgabel);

        ManagetheChaos chaos = new ManagetheChaos(list1);

        chaos.putAwaySmart();

        assertEquals(tasse, chaos.replace(tasse));
        assertThrows(RuntimeException.class, () -> chaos.replace(teller));

    }

    @Test
    void testReplaceDumb() {
        ManagetheChaos m = new ManagetheChaos(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> m.replace(null));

        List<KitchenItem> list1 = new ArrayList<>();
        KitchenItem tasse = new KitchenItem("tasse", 3);
        KitchenItem teller = new KitchenItem("teller", 4, "Dennis");
        KitchenItem paulsgabel = new KitchenItem( "gabel", 2, "Paul");
        paulsgabel.neededquantity = 1;
        tasse.neededquantity = 1;
        teller.neededquantity = 4;
        list1.add(tasse);
        list1.add(teller);
        list1.add(paulsgabel);

        ManagetheChaos chaos = new ManagetheChaos(list1);

        chaos.putAway();

        assertEquals(tasse, chaos.replace(tasse));
        assertThrows(RuntimeException.class, () -> chaos.replace(teller));

    }

    @Test
    void main() {
        List<KitchenItem> list1 = new ArrayList<>();
        KitchenItem tasse = new KitchenItem("tasse", 3);
        KitchenItem teller = new KitchenItem("teller", 4, "Dennis");
        KitchenItem paulsgabel = new KitchenItem( "gabel", 2, "Paul");
        paulsgabel.neededquantity = 1;
        tasse.neededquantity = 1;
        teller.neededquantity = 2;
        list1.add(tasse);
        list1.add(teller);
        list1.add(paulsgabel);
        ManagetheChaos chaos = new ManagetheChaos(list1);

        chaos.putAway();

        Stack<Item> cubby = new Stack<>();
        for (int i = 0; i <3 ; i++) {
            KitchenItem filler1 = new KitchenItem("filler1", 3);
            cubby.push(filler1);
        }
        cubby.push(null);
        for (int i = 0; i <2 ; i++) {
            KitchenItem filler2 = new KitchenItem("filler2", 2);
            cubby.push(filler2);
        }
        ManagetheChaos chaosSmart = new ManagetheChaos(list1, cubby);
        chaosSmart.putAwaySmart();
    }
}