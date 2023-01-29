package com.qlassalle.billy.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.qlassalle.billy.domain.model.SmartContractEvent;

import java.util.List;

public class JsonParser {

    public List<SmartContractEvent> parse(String json) {
        try {
            var mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            return mapper.readValue(json, new TypeReference<List<SmartContractEvent>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Unable to deserialize the following json: " + json, e);
        }
    }
}
