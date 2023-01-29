package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.SmartContractEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSmartContractEventRepository extends JpaRepository<SmartContractEventEntity, Long> {

}
