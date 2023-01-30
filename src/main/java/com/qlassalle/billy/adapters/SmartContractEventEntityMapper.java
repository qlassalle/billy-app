package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.SmartContractEventEntity;
import com.qlassalle.billy.domain.model.SmartContractEvent;

import java.util.List;

public class SmartContractEventEntityMapper {
    public static List<SmartContractEvent> map(List<SmartContractEventEntity> smartContractEvents) {
        return smartContractEvents.stream()
                                  .map(sce -> new SmartContractEvent(sce.getEventId(), sce.getCollectionName(),
                                                                     sce.getSmartContract()))
                                  .toList();
    }
}
