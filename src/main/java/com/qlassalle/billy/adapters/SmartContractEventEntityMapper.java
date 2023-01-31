package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.SmartContractEventEntity;
import com.qlassalle.billy.domain.model.SmartContractEvent;

import java.util.List;

public class SmartContractEventEntityMapper {
    public static List<SmartContractEvent> map(List<SmartContractEventEntity> smartContractEvents) {
        return smartContractEvents.stream()
                                  .map(sce -> new SmartContractEvent(sce.getId(), sce.getEventId(),
                                                                     sce.getCollectionName(),
                                                                     sce.getSmartContract()))
                                  .toList();
    }

    public static List<SmartContractEventEntity> toEntity(List<SmartContractEvent> smartContractEvents) {
        return smartContractEvents.stream()
                                  .map(sce -> new SmartContractEventEntity(sce.getEventId(),
                                                                     sce.getCollectionName(),
                                                                     sce.getSmartContract()))
                                  .toList();
    }
}
