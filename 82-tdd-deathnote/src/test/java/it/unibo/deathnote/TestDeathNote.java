package it.unibo.deathnote;

import it.unibo.deathnote.impl.DeathNoteImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TestDeathNote {

    @Test
    void TestDeathNote() throws InterruptedException{
        
        final DeathNoteImpl newImp1 = new DeathNoteImpl();
        /*TODO Multiple test for section */
        /* 1 */
        try {
            newImp1.getRule(0);
        } catch (Exception e) {
            assertEquals(new IllegalArgumentException(), e);
        }

        try {
            newImp1.getRule(-1);
        } catch (Exception e) {
            assertEquals(new IllegalArgumentException(), e);
        }

        /* 2 */
        for(int i = 0; i < newImp1.RULES.size(); i++){
            assertNotNull(newImp1.getRule(i));
        }

        /* 3 */
        assertEquals(false, newImp1.isNameWritten("pippo"));
        newImp1.writeName("pippo");
        assertTrue(newImp1.isNameWritten("pippo"));
        assertFalse(newImp1.isNameWritten("paolo"));
        assertFalse( newImp1.isNameWritten(""));

        /* 4 */
        final DeathNoteImpl newImp2 = new DeathNoteImpl();

        try {
            newImp2.writeDeathCause("investito");
        } catch (Exception e) {
            assertEquals(new IllegalStateException(), e);
        }

        newImp2.writeName("Franco");
        assertEquals("heart attack", newImp2.getDeathCause("Franco"));
        
        newImp2.writeName("Pippo");
        assertTrue(newImp2.writeDeathCause("karting accident"));
        assertEquals("karting accident", newImp2.getDeathCause("Pippo"));

        Thread.sleep(100);

        newImp2.writeDeathCause("investito");
        assertEquals("karting accident", newImp2.getDeathCause("Pippo"));

        /* 5 */
        final DeathNoteImpl newImp3 = new DeathNoteImpl(); 

        try {
            newImp3.writeDetails("inciampa");
        } catch (Exception e) {
            assertEquals(new IllegalArgumentException(), e);
        }

        newImp3.writeName("Pippo");
        assertEquals("", newImp3.getDeathDetails("Pippo"));
        assertTrue(newImp3.writeDetails("ran for too long"));
        assertEquals("ran for too long", newImp3.getDeathDetails("Pippo"));

        newImp3.writeName("Franco");

        Thread.sleep(6100);

        newImp3.writeDetails("walked");
        assertEquals("", newImp3.getDeathDetails("Franco"));
    }
}