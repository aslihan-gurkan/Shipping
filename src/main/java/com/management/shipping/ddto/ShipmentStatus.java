package com.management.shipping.ddto;

public enum ShipmentStatus {

	CREATED (1),
	LOADEDINTOBAG (2),
	LOADED (3),
	UNLOADED (4);

    private final int value;

    ShipmentStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
