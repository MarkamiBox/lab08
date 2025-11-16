package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;
import it.unibo.deathnote.api.DeathNote;

/**
 * Implementation of the DeathNote interface.
 */
public final class DeathNoteImpl implements DeathNote {
    private final Map<String, DeathInfo> deathnote = new HashMap<>();
    private String lastName = "";

    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("This rule doesn't exist");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    @SuppressWarnings("PMD.AvoidThrowingNullPointerException") //required by the interface
    public void writeName(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        final long time = System.currentTimeMillis();
        deathnote.put(name, new DeathInfo(time, "", ""));
        lastName = name;
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if ("".equals(lastName) || cause == null) {
            throw new IllegalStateException();
        }
        final DeathInfo infoname = deathnote.get(lastName);
        if (!"".equals(infoname.getCause())) {
             return false;
        }
        final long actualTime = System.currentTimeMillis();
        final long settedTime = infoname.getTime();
        final int time = 40;
        if (actualTime <= settedTime + time) {
            infoname.setCause(cause);
            infoname.setTime(actualTime);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(final String details) {
        if ("".equals(lastName) || details == null) {
            throw new IllegalStateException();
        }
        final DeathInfo infoname = deathnote.get(lastName);
        if (!"".equals(infoname.getDetails())) {
             return false;
        }
        final long actualTime = System.currentTimeMillis();
        final long settedTime = infoname.getTime();
        final int time = 6040;
        if (actualTime <= settedTime + time) {
            infoname.setDetails(details);
            return true;
        }
        return false;
    }

    @Override
    public boolean isNameWritten(final String name) {
        return deathnote.containsKey(name);
    }

    @Override
    public String getDeathCause(final String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException();
        }
        final String cause = deathnote.get(name).cause;
        if ("".equals(cause)) {
            return "heart attack";
        } else {
            return cause;
        }
    }

    @Override
    public String getDeathDetails(final String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException();
        }
        return deathnote.get(name).getDetails();
    }

    /* Internal class that contains infos */
    private static class DeathInfo {
        private long time;
        private String cause;
        private String details;

        DeathInfo(final long time, final String cause, final String details) {
            this.time = time;
            this.cause = cause;
            this.details = details;
        }

        public long getTime() {
            return time;
        }

        public void setTime(final long newTime) {
            this.time = newTime;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(final String newCause) {
            this.cause = newCause;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(final String newDetails) {
            this.details = newDetails;
        }
    }
}
