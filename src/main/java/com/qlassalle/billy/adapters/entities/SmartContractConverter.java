package com.qlassalle.billy.adapters.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlassalle.billy.domain.model.SmartContract;
import jakarta.persistence.AttributeConverter;

public class SmartContractConverter implements AttributeConverter<SmartContract, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(SmartContract attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize smart contract into db", e);
        }
    }

    @Override
    public SmartContract convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, SmartContract.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to deserialize smart contract from db", e);
        }
    }
}
