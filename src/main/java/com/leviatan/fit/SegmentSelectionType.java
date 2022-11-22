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


public enum SegmentSelectionType  {
    STARRED((short)0),
    SUGGESTED((short)1),
    INVALID((short)255);

    protected short value;

    private SegmentSelectionType(short value) {
        this.value = value;
    }

    public static SegmentSelectionType getByValue(final Short value) {
        for (final SegmentSelectionType type : SegmentSelectionType.values()) {
            if (value == type.value)
                return type;
        }

        return SegmentSelectionType.INVALID;
    }

    /**
     * Retrieves the String Representation of the Value
     * @return The string representation of the value
     */
    public static String getStringFromValue( SegmentSelectionType value ) {
        return value.name();
    }

    public short getValue() {
        return value;
    }


}
