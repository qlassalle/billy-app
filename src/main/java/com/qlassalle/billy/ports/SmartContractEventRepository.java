package com.qlassalle.billy.ports;

import com.qlassalle.billy.domain.model.SmartContractEvent;

public interface SmartContractEventRepository {

    void save(SmartContractEvent smartContract);
}
