package it.unibo.deathnote;

import it.unibo.deathnote.impl.DeathNoteImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TestDeathNote {
        
        final DeathNoteImpl newImp = new DeathNoteImpl();
    
        /* 1 */
        @Test
        void TestDNFirstRule(){
            try {
                newImp.getRule(0);  
                newImp.getRule(-1); 
            } catch (IllegalArgumentException e){
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isBlank());
            }
        } 

        /* 2 */
        @Test
        void TestDNSecondRule(){
            for(int i = 0; i < newImp.RULES.size(); i++){
                assertNotNull(newImp.getRule(i));
                assertFalse(newImp.getRule(i).isBlank());
            }
        }
        
        /* 3 */
        @Test
        void TestDNThirdRule(){
            assertFalse(newImp.isNameWritten("pippo"));
            newImp.writeName("pippo");
            assertTrue(newImp.isNameWritten("pippo"));
            assertFalse(newImp.isNameWritten("paolo"));
            assertFalse(newImp.isNameWritten(""));
        }
    
        /* 4 */
        @Test
        void TestDNFourthRule(){
            try {
                newImp.writeDeathCause("investito");
            } catch (IllegalStateException e)
                
            }
            newImp.writeName("Franco");
            assertEquals("heart attack", newImp.getDeathCause("Franco"));
            newImp.writeName("Pippo");
            assertTrue(newImp.writeDeathCause("karting accident"));
            assertEquals("karting accident", newImp.getDeathCause("Pippo"));
            Thread.sleep(100);
            newImp.writeDeathCause("investito");
            assertEquals("karting accident", newImp.getDeathCause("Pippo"));
        }
    
        /* 5 */
        @Test
        void TestDNFifthRUle(){
            try {
                newImp.writeDetails("inciampa");
            } catch (IllegalArgumentException e) {
            }
            newImp.writeName("Pippo");
            assertEquals("", newImp.getDeathDetails("Pippo"));
            assertTrue(newImp.writeDetails("ran for too long"));
            assertEquals("ran for too long", newImp.getDeathDetails("Pippo"));
            newImp.writeName("Franco");
            Thread.sleep(6100);
            newImp.writeDetails("walked");
            assertEquals("", newImp.getDeathDetails("Franco"));
        }
}