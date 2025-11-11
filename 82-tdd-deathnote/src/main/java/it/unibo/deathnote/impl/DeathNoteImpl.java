package it.unibo.deathnote.impl;
import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    @Override
    public String getRule(int ruleNumber) {
        // TODO Auto-generated method stub
        throw new IllegalArgumentException();
    }

    @Override
    public void writeName(String name) {
        // TODO Auto-generated method stub
        throw new NullPointerException();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new IllegalStateException();
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new IllegalStateException();
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new IllegalArgumentException();
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
