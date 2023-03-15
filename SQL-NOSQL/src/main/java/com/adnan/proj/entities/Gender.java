package com.adnan.proj.entities;

public enum Gender {

    MALE("Male"), FEMALE("Female"), OTHERS("Other");


    private String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Gender getEnumFromString(String str) {

        for(Gender l: Gender.values()) {

            if(str.equalsIgnoreCase(l.text)) {
                return l;
            }

        }

        return null;
    }

//    MALE,FEMALE,OTHERS;

}
