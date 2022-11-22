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

public class FitBaseType  {
    public static final short ENUM = 0;
    public static final short SINT8 = 1;
    public static final short UINT8 = 2;
    public static final short SINT16 = 131;
    public static final short UINT16 = 132;
    public static final short SINT32 = 133;
    public static final short UINT32 = 134;
    public static final short STRING = 7;
    public static final short FLOAT32 = 136;
    public static final short FLOAT64 = 137;
    public static final short UINT8Z = 10;
    public static final short UINT16Z = 139;
    public static final short UINT32Z = 140;
    public static final short BYTE = 13;
    public static final short SINT64 = 142;
    public static final short UINT64 = 143;
    public static final short UINT64Z = 144;
    public static final short INVALID = Fit.UINT8_INVALID;

    private static final Map<Short, String> stringMap;

    static {
        stringMap = new HashMap<Short, String>();
        stringMap.put(ENUM, "ENUM");
        stringMap.put(SINT8, "SINT8");
        stringMap.put(UINT8, "UINT8");
        stringMap.put(SINT16, "SINT16");
        stringMap.put(UINT16, "UINT16");
        stringMap.put(SINT32, "SINT32");
        stringMap.put(UINT32, "UINT32");
        stringMap.put(STRING, "STRING");
        stringMap.put(FLOAT32, "FLOAT32");
        stringMap.put(FLOAT64, "FLOAT64");
        stringMap.put(UINT8Z, "UINT8Z");
        stringMap.put(UINT16Z, "UINT16Z");
        stringMap.put(UINT32Z, "UINT32Z");
        stringMap.put(BYTE, "BYTE");
        stringMap.put(SINT64, "SINT64");
        stringMap.put(UINT64, "UINT64");
        stringMap.put(UINT64Z, "UINT64Z");
    }


    /**
     * Retrieves the String Representation of the Value
     * @return The string representation of the value, or empty if unknown
     */
    public static String getStringFromValue( Short value ) {
        if( stringMap.containsKey( value ) ) {
            return stringMap.get( value );
        }

        return "";
    }

    /**
     * Retrieves a value given a string representation
     * @return The value or INVALID if unkwown
     */
    public static Short getValueFromString( String value ) {
        for( Map.Entry<Short, String> entry : stringMap.entrySet() ) {
            if( entry.getValue().equals( value ) ) {
                return entry.getKey();
            }
        }

        return INVALID;
    }

}
