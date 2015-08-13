package no.swact.action.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.stereotype.Service;

@Service
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        this.registerModule(new JSR310Module());
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                false);
    }
}