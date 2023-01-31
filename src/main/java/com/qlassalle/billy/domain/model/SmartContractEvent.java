package com.qlassalle.billy.domain.model;

import java.util.Objects;

public class SmartContractEvent {

    private int eventId;
    private String collectionName;
    private SmartContract smartContract;

    public SmartContractEvent(int eventId, String collectionName, SmartContract smartContract) {
        this.eventId = eventId;
        this.collectionName = collectionName;
        this.smartContract = smartContract;
    }

    public SmartContractEvent() {
    }

    public int getEventId() {
        return eventId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public SmartContract getSmartContract() {
        return smartContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmartContractEvent that = (SmartContractEvent) o;
        return eventId == that.eventId && Objects.equals(collectionName, that.collectionName) && Objects.equals(smartContract, that.smartContract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, collectionName, smartContract);
    }
}
