package org.launchcode.codeevents.models;

public enum EventType {

    CONFERENCE("Conference"), //so each of these is somehow a call to the constructor? Since it takes display name as an argument?
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

