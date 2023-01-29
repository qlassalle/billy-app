package com.qlassalle.billy.adapters;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEventRepository extends JpaRepository<EventEntity, Integer> {
}
