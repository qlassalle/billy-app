package com.qlassalle.billy.adapters;

import com.qlassalle.billy.adapters.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEventRepository extends JpaRepository<EventEntity, Integer> {
}
