package lt.codeacademy.model.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.codeacademy.entities.Voter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadJason {

    public List<Voter> loadRecordsFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(new File("votingInfo.json"), new TypeReference<>() {
        });
    }
}
