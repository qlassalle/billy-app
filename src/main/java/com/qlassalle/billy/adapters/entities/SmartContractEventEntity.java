package com.qlassalle.billy.adapters.entities;

import com.qlassalle.billy.domain.model.SmartContract;
import jakarta.persistence.*;

@Entity
@Table(name = "smart_contract_event")
public class SmartContractEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int eventId;

    private String collectionName;

    @Convert(converter = SmartContractConverter.class)
    private SmartContract smartContract;

    public SmartContractEventEntity() {
    }

    public SmartContractEventEntity(int eventId, String collectionName, SmartContract smartContract) {
        this.eventId = eventId;
        this.collectionName = collectionName;
        this.smartContract = smartContract;
    }
}
