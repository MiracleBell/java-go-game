package io.deeplay.intership.connection;

import io.deeplay.intership.dto.request.BaseDtoRequest;
import io.deeplay.intership.dto.response.BaseDtoResponse;
import io.deeplay.intership.json.converter.JSONConverter;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerStreamConnector {
    private final Logger logger = Logger.getLogger(ClientStreamConnector.class);
    private final JSONConverter jsonConverter = new JSONConverter();
    private final DataOutputStream writer;
    private final DataInputStream reader;

    public ServerStreamConnector(DataOutputStream writer, DataInputStream reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public void sendResponse(final BaseDtoResponse dtoResponse) throws IOException {
        try {
            writer.writeUTF(jsonConverter.getJsonFromObject(dtoResponse));
            writer.flush();
        } catch (IOException ex) {
            logger.error("Unknown IOException");
            throw ex;
        }
    }

    public BaseDtoRequest getRequest() throws IOException {
        try {
            final String fromServer = reader.readUTF();
            return jsonConverter.getObjectFromJson(fromServer, BaseDtoRequest.class);
        } catch (IOException ex) {
            logger.error(ex);
            throw ex;
        }
    }
}
