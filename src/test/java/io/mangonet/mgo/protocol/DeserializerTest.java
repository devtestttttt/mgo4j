package io.mangonet.mgo.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

public class DeserializerTest {

    protected ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = ObjectMapperFactory.getObjectMapper();
    }
}
