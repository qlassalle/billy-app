package com.qlassalle.billy.adapters;

import com.qlassalle.billy.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEventRepository extends JpaRepository<EventEntity, Integer> {
}
