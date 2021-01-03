package org.biacode.jcronofy.api.model.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.biacode.jcronofy.api.model.FreeBusyDateModel;

import java.io.IOException;

public class FreeBusyDateDeserializer extends JsonDeserializer<FreeBusyDateModel> {
    @Override
    public FreeBusyDateModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode time = node.get("time");
        JsonNode tzid = node.get("tzid");
        if (time != null) {
            return FreeBusyDateModel.of(time.asText(), tzid.asText());
        } else {
            return FreeBusyDateModel.of(node.asText());
        }
    }
}
