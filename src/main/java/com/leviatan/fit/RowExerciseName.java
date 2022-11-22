///////////////////////////////////////////////////////////////////////////////////
// The following FIT Protocol software provided may be used with FIT protocol
// devices only and remains the copyrighted property of Garmin International, Inc.
// The software is being provided on an "as-is" basis and as an accommodation,
// and therefore all warranties, representations, or guarantees of any kind
// (whether express, implied or statutory) including, without limitation,
// warranties of merchantability, non-infringement, or fitness for a particular
// purpose, are specifically disclaimed.
//
// Copyright 2022 Garmin International, Inc.
///////////////////////////////////////////////////////////////////////////////////
// ****WARNING****  This file is auto-generated!  Do NOT edit this file.
// Profile Version = 21.89Release
// Tag = production/akw/21.89.00-0-g487f2ed0
///////////////////////////////////////////////////////////////////////////////////


package com.leviatan.fit;

import java.util.HashMap;
import java.util.Map;

public class RowExerciseName  {
    public static final int BARBELL_STRAIGHT_LEG_DEADLIFT_TO_ROW = 0;
    public static final int CABLE_ROW_STANDING = 1;
    public static final int DUMBBELL_ROW = 2;
    public static final int ELEVATED_FEET_INVERTED_ROW = 3;
    public static final int WEIGHTED_ELEVATED_FEET_INVERTED_ROW = 4;
    public static final int FACE_PULL = 5;
    public static final int FACE_PULL_WITH_EXTERNAL_ROTATION = 6;
    public static final int INVERTED_ROW_WITH_FEET_ON_SWISS_BALL = 7;
    public static final int WEIGHTED_INVERTED_ROW_WITH_FEET_ON_SWISS_BALL = 8;
    public static final int KETTLEBELL_ROW = 9;
    public static final int MODIFIED_INVERTED_ROW = 10;
    public static final int WEIGHTED_MODIFIED_INVERTED_ROW = 11;
    public static final int NEUTRAL_GRIP_ALTERNATING_DUMBBELL_ROW = 12;
    public static final int ONE_ARM_BENT_OVER_ROW = 13;
    public static final int ONE_LEGGED_DUMBBELL_ROW = 14;
    public static final int RENEGADE_ROW = 15;
    public static final int REVERSE_GRIP_BARBELL_ROW = 16;
    public static final int ROPE_HANDLE_CABLE_ROW = 17;
    public static final int SEATED_CABLE_ROW = 18;
    public static final int SEATED_DUMBBELL_ROW = 19;
    public static final int SINGLE_ARM_CABLE_ROW = 20;
    public static final int SINGLE_ARM_CABLE_ROW_AND_ROTATION = 21;
    public static final int SINGLE_ARM_INVERTED_ROW = 22;
    public static final int WEIGHTED_SINGLE_ARM_INVERTED_ROW = 23;
    public static final int SINGLE_ARM_NEUTRAL_GRIP_DUMBBELL_ROW = 24;
    public static final int SINGLE_ARM_NEUTRAL_GRIP_DUMBBELL_ROW_AND_ROTATION = 25;
    public static final int SUSPENDED_INVERTED_ROW = 26;
    public static final int WEIGHTED_SUSPENDED_INVERTED_ROW = 27;
    public static final int T_BAR_ROW = 28;
    public static final int TOWEL_GRIP_INVERTED_ROW = 29;
    public static final int WEIGHTED_TOWEL_GRIP_INVERTED_ROW = 30;
    public static final int UNDERHAND_GRIP_CABLE_ROW = 31;
    public static final int V_GRIP_CABLE_ROW = 32;
    public static final int WIDE_GRIP_SEATED_CABLE_ROW = 33;
    public static final int INVALID = Fit.UINT16_INVALID;

    private static final Map<Integer, String> stringMap;

    static {
        stringMap = new HashMap<Integer, String>();
        stringMap.put(BARBELL_STRAIGHT_LEG_DEADLIFT_TO_ROW, "BARBELL_STRAIGHT_LEG_DEADLIFT_TO_ROW");
        stringMap.put(CABLE_ROW_STANDING, "CABLE_ROW_STANDING");
        stringMap.put(DUMBBELL_ROW, "DUMBBELL_ROW");
        stringMap.put(ELEVATED_FEET_INVERTED_ROW, "ELEVATED_FEET_INVERTED_ROW");
        stringMap.put(WEIGHTED_ELEVATED_FEET_INVERTED_ROW, "WEIGHTED_ELEVATED_FEET_INVERTED_ROW");
        stringMap.put(FACE_PULL, "FACE_PULL");
        stringMap.put(FACE_PULL_WITH_EXTERNAL_ROTATION, "FACE_PULL_WITH_EXTERNAL_ROTATION");
        stringMap.put(INVERTED_ROW_WITH_FEET_ON_SWISS_BALL, "INVERTED_ROW_WITH_FEET_ON_SWISS_BALL");
        stringMap.put(WEIGHTED_INVERTED_ROW_WITH_FEET_ON_SWISS_BALL, "WEIGHTED_INVERTED_ROW_WITH_FEET_ON_SWISS_BALL");
        stringMap.put(KETTLEBELL_ROW, "KETTLEBELL_ROW");
        stringMap.put(MODIFIED_INVERTED_ROW, "MODIFIED_INVERTED_ROW");
        stringMap.put(WEIGHTED_MODIFIED_INVERTED_ROW, "WEIGHTED_MODIFIED_INVERTED_ROW");
        stringMap.put(NEUTRAL_GRIP_ALTERNATING_DUMBBELL_ROW, "NEUTRAL_GRIP_ALTERNATING_DUMBBELL_ROW");
        stringMap.put(ONE_ARM_BENT_OVER_ROW, "ONE_ARM_BENT_OVER_ROW");
        stringMap.put(ONE_LEGGED_DUMBBELL_ROW, "ONE_LEGGED_DUMBBELL_ROW");
        stringMap.put(RENEGADE_ROW, "RENEGADE_ROW");
        stringMap.put(REVERSE_GRIP_BARBELL_ROW, "REVERSE_GRIP_BARBELL_ROW");
        stringMap.put(ROPE_HANDLE_CABLE_ROW, "ROPE_HANDLE_CABLE_ROW");
        stringMap.put(SEATED_CABLE_ROW, "SEATED_CABLE_ROW");
        stringMap.put(SEATED_DUMBBELL_ROW, "SEATED_DUMBBELL_ROW");
        stringMap.put(SINGLE_ARM_CABLE_ROW, "SINGLE_ARM_CABLE_ROW");
        stringMap.put(SINGLE_ARM_CABLE_ROW_AND_ROTATION, "SINGLE_ARM_CABLE_ROW_AND_ROTATION");
        stringMap.put(SINGLE_ARM_INVERTED_ROW, "SINGLE_ARM_INVERTED_ROW");
        stringMap.put(WEIGHTED_SINGLE_ARM_INVERTED_ROW, "WEIGHTED_SINGLE_ARM_INVERTED_ROW");
        stringMap.put(SINGLE_ARM_NEUTRAL_GRIP_DUMBBELL_ROW, "SINGLE_ARM_NEUTRAL_GRIP_DUMBBELL_ROW");
        stringMap.put(SINGLE_ARM_NEUTRAL_GRIP_DUMBBELL_ROW_AND_ROTATION, "SINGLE_ARM_NEUTRAL_GRIP_DUMBBELL_ROW_AND_ROTATION");
        stringMap.put(SUSPENDED_INVERTED_ROW, "SUSPENDED_INVERTED_ROW");
        stringMap.put(WEIGHTED_SUSPENDED_INVERTED_ROW, "WEIGHTED_SUSPENDED_INVERTED_ROW");
        stringMap.put(T_BAR_ROW, "T_BAR_ROW");
        stringMap.put(TOWEL_GRIP_INVERTED_ROW, "TOWEL_GRIP_INVERTED_ROW");
        stringMap.put(WEIGHTED_TOWEL_GRIP_INVERTED_ROW, "WEIGHTED_TOWEL_GRIP_INVERTED_ROW");
        stringMap.put(UNDERHAND_GRIP_CABLE_ROW, "UNDERHAND_GRIP_CABLE_ROW");
        stringMap.put(V_GRIP_CABLE_ROW, "V_GRIP_CABLE_ROW");
        stringMap.put(WIDE_GRIP_SEATED_CABLE_ROW, "WIDE_GRIP_SEATED_CABLE_ROW");
    }


    /**
     * Retrieves the String Representation of the Value
     * @return The string representation of the value, or empty if unknown
     */
    public static String getStringFromValue( Integer value ) {
        if( stringMap.containsKey( value ) ) {
            return stringMap.get( value );
        }

        return "";
    }

    /**
     * Retrieves a value given a string representation
     * @return The value or INVALID if unkwown
     */
    public static Integer getValueFromString( String value ) {
        for( Map.Entry<Integer, String> entry : stringMap.entrySet() ) {
            if( entry.getValue().equals( value ) ) {
                return entry.getKey();
            }
        }

        return INVALID;
    }

}
