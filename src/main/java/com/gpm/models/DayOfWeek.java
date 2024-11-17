package com.gpm.models;

public enum DayOfWeek {

    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private final int value;

    // Constructor
    DayOfWeek(int value) {  
        this.value = value;
    }

    // Method to get the integer value of the day
    public int getValue() {
        return this.value;
    }

    // Static method to validate if a given value is within valid range
    public static boolean isValid(int value) {
        return value >= 1 && value <= 6;
    }

    // Static method to convert an integer to a DayOfWeek
    public static DayOfWeek fromValue(int value) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getValue() == value) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid day value: " + value);
    }
}
