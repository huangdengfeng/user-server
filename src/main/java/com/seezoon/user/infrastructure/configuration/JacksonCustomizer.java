package com.seezoon.user.infrastructure.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * jackson configuration
 */
@Configuration
public class JacksonCustomizer {

    /**
     * json trim when deserialize
     *
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonTrimWhenDeserialize() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.deserializerByType(String.class,
                    new StdScalarDeserializer<String>(String.class) {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public String deserialize(JsonParser jsonParser, DeserializationContext ctx)
                                throws IOException {
                            String valueAsString = jsonParser.getValueAsString();
                            if (null != valueAsString) {
                                valueAsString = valueAsString.trim();
                            }
                            return valueAsString;
                        }
                    });
        };
    }
}
