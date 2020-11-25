package org.edu.nisith.serialization;

/**
 * Created by c0244782 on 23/11/2020.
 */

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.edu.nisith.pojo.TransactionRecordFormat;

import java.io.IOException;

public class TransactionDeserializationSchema implements DeserializationSchema<TransactionRecordFormat>{
    private static final long serialVersionUID = 1L;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TransactionRecordFormat deserialize(byte[] message) throws IOException {
        return objectMapper.readValue(message, TransactionRecordFormat.class);
    }

    @Override
    public boolean isEndOfStream(TransactionRecordFormat nextElement) {
        return false;
    }

    @Override
    public TypeInformation<TransactionRecordFormat> getProducedType() {
        return TypeInformation.of(TransactionRecordFormat.class);
    }
}
