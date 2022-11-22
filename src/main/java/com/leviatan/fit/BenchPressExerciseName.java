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

public class BenchPressExerciseName  {
    public static final int ALTERNATING_DUMBBELL_CHEST_PRESS_ON_SWISS_BALL = 0;
    public static final int BARBELL_BENCH_PRESS = 1;
    public static final int BARBELL_BOARD_BENCH_PRESS = 2;
    public static final int BARBELL_FLOOR_PRESS = 3;
    public static final int CLOSE_GRIP_BARBELL_BENCH_PRESS = 4;
    public static final int DECLINE_DUMBBELL_BENCH_PRESS = 5;
    public static final int DUMBBELL_BENCH_PRESS = 6;
    public static final int DUMBBELL_FLOOR_PRESS = 7;
    public static final int INCLINE_BARBELL_BENCH_PRESS = 8;
    public static final int INCLINE_DUMBBELL_BENCH_PRESS = 9;
    public static final int INCLINE_SMITH_MACHINE_BENCH_PRESS = 10;
    public static final int ISOMETRIC_BARBELL_BENCH_PRESS = 11;
    public static final int KETTLEBELL_CHEST_PRESS = 12;
    public static final int NEUTRAL_GRIP_DUMBBELL_BENCH_PRESS = 13;
    public static final int NEUTRAL_GRIP_DUMBBELL_INCLINE_BENCH_PRESS = 14;
    public static final int ONE_ARM_FLOOR_PRESS = 15;
    public static final int WEIGHTED_ONE_ARM_FLOOR_PRESS = 16;
    public static final int PARTIAL_LOCKOUT = 17;
    public static final int REVERSE_GRIP_BARBELL_BENCH_PRESS = 18;
    public static final int REVERSE_GRIP_INCLINE_BENCH_PRESS = 19;
    public static final int SINGLE_ARM_CABLE_CHEST_PRESS = 20;
    public static final int SINGLE_ARM_DUMBBELL_BENCH_PRESS = 21;
    public static final int SMITH_MACHINE_BENCH_PRESS = 22;
    public static final int SWISS_BALL_DUMBBELL_CHEST_PRESS = 23;
    public static final int TRIPLE_STOP_BARBELL_BENCH_PRESS = 24;
    public static final int WIDE_GRIP_BARBELL_BENCH_PRESS = 25;
    public static final int ALTERNATING_DUMBBELL_CHEST_PRESS = 26;
    public static final int INVALID = Fit.UINT16_INVALID;

    private static final Map<Integer, String> stringMap;

    static {
        stringMap = new HashMap<Integer, String>();
        stringMap.put(ALTERNATING_DUMBBELL_CHEST_PRESS_ON_SWISS_BALL, "ALTERNATING_DUMBBELL_CHEST_PRESS_ON_SWISS_BALL");
        stringMap.put(BARBELL_BENCH_PRESS, "BARBELL_BENCH_PRESS");
        stringMap.put(BARBELL_BOARD_BENCH_PRESS, "BARBELL_BOARD_BENCH_PRESS");
        stringMap.put(BARBELL_FLOOR_PRESS, "BARBELL_FLOOR_PRESS");
        stringMap.put(CLOSE_GRIP_BARBELL_BENCH_PRESS, "CLOSE_GRIP_BARBELL_BENCH_PRESS");
        stringMap.put(DECLINE_DUMBBELL_BENCH_PRESS, "DECLINE_DUMBBELL_BENCH_PRESS");
        stringMap.put(DUMBBELL_BENCH_PRESS, "DUMBBELL_BENCH_PRESS");
        stringMap.put(DUMBBELL_FLOOR_PRESS, "DUMBBELL_FLOOR_PRESS");
        stringMap.put(INCLINE_BARBELL_BENCH_PRESS, "INCLINE_BARBELL_BENCH_PRESS");
        stringMap.put(INCLINE_DUMBBELL_BENCH_PRESS, "INCLINE_DUMBBELL_BENCH_PRESS");
        stringMap.put(INCLINE_SMITH_MACHINE_BENCH_PRESS, "INCLINE_SMITH_MACHINE_BENCH_PRESS");
        stringMap.put(ISOMETRIC_BARBELL_BENCH_PRESS, "ISOMETRIC_BARBELL_BENCH_PRESS");
        stringMap.put(KETTLEBELL_CHEST_PRESS, "KETTLEBELL_CHEST_PRESS");
        stringMap.put(NEUTRAL_GRIP_DUMBBELL_BENCH_PRESS, "NEUTRAL_GRIP_DUMBBELL_BENCH_PRESS");
        stringMap.put(NEUTRAL_GRIP_DUMBBELL_INCLINE_BENCH_PRESS, "NEUTRAL_GRIP_DUMBBELL_INCLINE_BENCH_PRESS");
        stringMap.put(ONE_ARM_FLOOR_PRESS, "ONE_ARM_FLOOR_PRESS");
        stringMap.put(WEIGHTED_ONE_ARM_FLOOR_PRESS, "WEIGHTED_ONE_ARM_FLOOR_PRESS");
        stringMap.put(PARTIAL_LOCKOUT, "PARTIAL_LOCKOUT");
        stringMap.put(REVERSE_GRIP_BARBELL_BENCH_PRESS, "REVERSE_GRIP_BARBELL_BENCH_PRESS");
        stringMap.put(REVERSE_GRIP_INCLINE_BENCH_PRESS, "REVERSE_GRIP_INCLINE_BENCH_PRESS");
        stringMap.put(SINGLE_ARM_CABLE_CHEST_PRESS, "SINGLE_ARM_CABLE_CHEST_PRESS");
        stringMap.put(SINGLE_ARM_DUMBBELL_BENCH_PRESS, "SINGLE_ARM_DUMBBELL_BENCH_PRESS");
        stringMap.put(SMITH_MACHINE_BENCH_PRESS, "SMITH_MACHINE_BENCH_PRESS");
        stringMap.put(SWISS_BALL_DUMBBELL_CHEST_PRESS, "SWISS_BALL_DUMBBELL_CHEST_PRESS");
        stringMap.put(TRIPLE_STOP_BARBELL_BENCH_PRESS, "TRIPLE_STOP_BARBELL_BENCH_PRESS");
        stringMap.put(WIDE_GRIP_BARBELL_BENCH_PRESS, "WIDE_GRIP_BARBELL_BENCH_PRESS");
        stringMap.put(ALTERNATING_DUMBBELL_CHEST_PRESS, "ALTERNATING_DUMBBELL_CHEST_PRESS");
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
