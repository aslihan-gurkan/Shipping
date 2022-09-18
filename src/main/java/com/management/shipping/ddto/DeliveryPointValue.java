package com.management.shipping.ddto;

public enum DeliveryPointValue {

	BRANCH (1),
	DISTRIBUTION_CENTER (2),
	TRANSFER_CENTER (3);

    private final int value;

    DeliveryPointValue(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
