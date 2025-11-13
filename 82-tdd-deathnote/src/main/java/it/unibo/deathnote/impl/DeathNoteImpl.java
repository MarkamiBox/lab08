package it.unibo.deathnote.impl;
import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {
    private static class DeathInfo{
        long time;
        String cause;
        String details;
        
        DeathInfo(long time, String cause, String details){
            this.time = time;
            this.cause = cause;
            this.deatils = deatils;
        }
    }
    final Map<String, DeathInfo> deathnote;
    final lastName;
    
    @Override
    public String getRule(final int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.Size()){
            throw new IllegalArgumentException();
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(final String name) {
        if(name == null){
            throw new NullPointerException();
        }
        long time = System.currentTimeMillis();
        deathnote.put(name, new DeathInfo(time, "", ""));
        lastName = name;
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(lastName == "" || cause == null){
            throw new IllegalStateException();
        }
        long actualTime = System.currentTimeMillis();
        long settedTime = deathnote.get(lastName).time;
        if((settedTime+40) < actualTime){
            deathnote.get(lastName).cause = cause;
            return true
        }
        return false
    }

    @Override
    public boolean writeDetails(String details) {
        if(lastName == "" || details == null){
            throw new IllegalStateException();
        }
        long actualTime = System.currentTimeMillis();
        long settedTime = deathnote.get(lastName).time;
        if((settedTime+640) < actualTime){
            deathnote.get(lastName).detail = details;
            return true
        }
    }

    @Override
    public String getDeathCause(String name) {
        if(deathnote.isNameWritten(name)){
            throw new IllegalArgumentException();
        }
        string cause = deathnote.get(name).cause;
        if(cause == ""){
            return "heart attack";
        }
        else{
            return cause;
        }
    }

    @Override
    public String getDeathDetails(String name) {
        if(deathnote.isNameWritten(name)){
            throw new IllegalArgumentException();
        }
        string detail = deathnote.get(name).deatil;
        return detail;
    }

    @Override
    public boolean isNameWritten(String name) {
        return deathnote.cotainsKey(name);
    }
    
}
