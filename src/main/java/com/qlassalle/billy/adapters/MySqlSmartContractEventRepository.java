package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.SmartContractEventEntity;
import com.qlassalle.billy.domain.model.SmartContractEvent;
import com.qlassalle.billy.ports.SmartContractEventRepository;
import org.springframework.stereotype.Component;

@Component
public class MySqlSmartContractEventRepository implements SmartContractEventRepository {

    private final JpaSmartContractEventRepository smartContractRepository;

    public MySqlSmartContractEventRepository(JpaSmartContractEventRepository smartContractRepository) {
        this.smartContractRepository = smartContractRepository;
    }

    @Override
    public void save(SmartContractEvent smartContract) {
        smartContractRepository.save(toEntity(smartContract));
    }

    private SmartContractEventEntity toEntity(SmartContractEvent smartContract) {
        return new SmartContractEventEntity(smartContract.getEventId(), smartContract.getCollectionName(),
                                            smartContract.getSmartContract());
    }
}
