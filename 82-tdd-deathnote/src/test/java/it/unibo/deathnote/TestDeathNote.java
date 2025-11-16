package it.unibo.deathnote;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.JUnitUseExpected") //required by the test
class TestDeathNote {
    //new implementation
    private final DeathNote newImp = new DeathNoteImpl();

    /* 1 */
    @Test
    void testDnFirstRule() {
        final String msg = "Not null or blank";
        //Rule 0
        try {
            newImp.getRule(0);
            fail("It should throw an IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage(), msg);
            assertFalse(e.getMessage().isBlank(), msg);
        }
        //Rule 1
        try {
            newImp.getRule(-1);
            fail("It should throw an IllegalArgumentException"); 
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage(), msg);
            assertFalse(e.getMessage().isBlank(), msg);
        }
    }

    /* 2 */
    @Test
    void testDnSecondRule() {
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            assertNotNull(newImp.getRule(i), "Rule number not null");
            assertFalse(newImp.getRule(i).isBlank(), "Rule number not blank");
        }
    }

    /* 3 */
    @Test
    void testDnThirdRule() {
        final String name1 = "Giorgio";
        final String name2 = "Paolo";
        assertFalse(newImp.isNameWritten(name1), name1 + "is not written yet");
        newImp.writeName(name1);
        assertTrue(newImp.isNameWritten(name1), name1 + "is written now");
        assertFalse(newImp.isNameWritten(name2), name2 + "is not written yet");
        assertFalse(newImp.isNameWritten(""), "Empty string should do nothing");
    }

    /* 4 */
    @Test
    @SuppressWarnings("PMD.EmptyCatchBlock") //required by the test
    void testDnFourthRule() throws InterruptedException {
        final String name1 = "Franco";
        final String name2 = "Pippo";
        final String cause1 = "investito";
        final String cause2 = "Karting accident";
        final String baseCause = "heart attack";
        try {
            newImp.writeDeathCause(cause1);
            fail("Should throw IllegalStateException");
        } catch (final IllegalStateException e) {
            // Correct exception
        }
        newImp.writeName(name1);
        assertEquals(baseCause, newImp.getDeathCause(name1), baseCause);
        newImp.writeName(name2);
        assertTrue(newImp.writeDeathCause(cause2), "cause" + cause2);
        assertEquals(cause2, newImp.getDeathCause(name2), name2 + "cause should be" + cause2);
        Thread.sleep(100);
        assertFalse(newImp.writeDeathCause(cause1), "Cause should not be rewritten");
        assertEquals(cause2, newImp.getDeathCause(name2), name2 + "cause should be" + cause2);
    }

    /* 5 */
    @Test
    @SuppressWarnings("PMD.EmptyCatchBlock") //required by the test
    void testDnFifthRUle() throws InterruptedException {
        final String name1 = "Pippo";
        final String name2 = "Franco";
        final String detail1 = "inciampa";
        final String detail2 = "ran for too long";
        final String detail3 = "walked for too long";
        try {
            newImp.writeDetails(detail1);
            fail("Should throw IllegalStateException");
        } catch (final IllegalStateException e) {
            // Correct exception
        }
        newImp.writeName(name1);
        newImp.writeDeathCause("running");
        assertEquals("", newImp.getDeathDetails(name1), "details should be empty");
        assertTrue(newImp.writeDetails(detail2), "Detail = " + detail2);
        assertEquals(detail2, newImp.getDeathDetails(name1), name1 + "details should be " + detail2);
        newImp.writeName(name2);
        newImp.writeDeathCause("died of boredom");
        final int time = 6100;
        Thread.sleep(time);
        assertFalse(newImp.writeDetails(detail3), "details should not be changed");
        assertEquals("", newImp.getDeathDetails(name2), "detail didn't change");
    }
}
