package lt.codeacademy.model.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lt.codeacademy.service.VoteService;

import java.io.File;
import java.io.IOException;

public class SaveJason {

    public void saveRecordsToFile(VoteService list) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("votingInfo.json"), list.getAllVoters());

    }
}
