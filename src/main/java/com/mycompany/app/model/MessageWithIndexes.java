package com.mycompany.app.model;

import com.gigaspaces.annotation.pojo.SpaceIndex;

public class MessageWithIndexes extends Message {

    @SpaceIndex
    public String getPayloadBinary() {
        return super.getPayloadBinary();
    }

    @SpaceIndex
    public String getPayloadBinaryBase64() {
        return super.getPayloadBinaryBase64();
    }

    @SpaceIndex
    public String getPayloadZip() {
        return super.getPayloadZip();
    }

    @SpaceIndex
    public String getPayloadZipBase64() {
        return super.getPayloadZipBase64();
    }
}
