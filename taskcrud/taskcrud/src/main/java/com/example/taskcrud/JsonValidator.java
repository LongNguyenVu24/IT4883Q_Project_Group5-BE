package com.example.taskcrud;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonValidator {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonNode schema;

    public JsonValidator(String schemaJson) throws IOException {
        this.schema = objectMapper.readTree(schemaJson);
    }

    public boolean validate(String json) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(json);
        return schema != null && schema.equals(jsonNode);
    }
}
