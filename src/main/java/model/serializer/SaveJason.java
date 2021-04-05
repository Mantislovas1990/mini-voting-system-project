package model.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import service.VoteServiceImpl;

import java.io.File;
import java.io.IOException;

public class SaveJason {

    public void saveRecordsToFile(VoteServiceImpl list) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("votingInfo.json"), list.getAllVoters());

    }
}
