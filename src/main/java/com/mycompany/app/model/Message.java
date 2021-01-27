package com.mycompany.app.model;

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpacePropertyStorageAdapter;
import com.gigaspaces.client.storage_adapters.AesGcmAdapter;
import com.gigaspaces.client.storage_adapters.BinaryAdapter;
import com.gigaspaces.client.storage_adapters.ZipAdapter;
import com.mycompany.app.adapters.AesBase64Adapter;
import com.mycompany.app.adapters.BinaryBase64Adapter;
import com.mycompany.app.adapters.ZipBase64Adapter;

import java.util.Objects;

public class Message {

    private Integer id;
    private String payload;
    private String payloadBinary;
    private String payloadBinaryBase64;
    private String payloadZip;
    private String payloadZipBase64;
    private String payloadAesBase64;
    private String payloadNotMatchable;

    @Override
    public String toString() {
        return String.format("Message [id=%s]", id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Message))
            return false;
        Message other = (Message) obj;
        if (!Objects.equals(this.id, other.id))
            return false;
        if (!Objects.equals(this.payload, other.payload))
            return false;
        if (!Objects.equals(this.payloadBinary, other.payloadBinary))
            return false;
        if (!Objects.equals(this.payloadBinaryBase64, other.payloadBinaryBase64))
            return false;
        if (!Objects.equals(this.payloadZip, other.payloadZip))
            return false;
        if (!Objects.equals(this.payloadZipBase64, other.payloadZipBase64))
            return false;
        if (!Objects.equals(this.payloadAesBase64, other.payloadAesBase64))
            return false;
        if (!Objects.equals(this.payloadNotMatchable, other.payloadNotMatchable))
            return false;
        return true;
    }

    @SpaceId
    public Integer getId() {
        return id;
    }
    public Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPayload() {
        return payload;
    }
    public Message setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    @SpacePropertyStorageAdapter(BinaryAdapter.class)
    public String getPayloadBinary() {
        return payloadBinary;
    }
    public Message setPayloadBinary(String payloadBinary) {
        this.payloadBinary = payloadBinary;
        return this;
    }

    @SpacePropertyStorageAdapter(BinaryBase64Adapter.class)
    public String getPayloadBinaryBase64() {
        return payloadBinaryBase64;
    }
    public Message setPayloadBinaryBase64(String payloadBinaryBase64) {
        this.payloadBinaryBase64 = payloadBinaryBase64;
        return this;
    }

    @SpacePropertyStorageAdapter(ZipAdapter.class)
    public String getPayloadZip() {
        return payloadZip;
    }
    public Message setPayloadZip(String payloadZip) {
        this.payloadZip = payloadZip;
        return this;
    }

    @SpacePropertyStorageAdapter(ZipBase64Adapter.class)
    public String getPayloadZipBase64() {
        return payloadZipBase64;
    }
    public Message setPayloadZipBase64(String payloadZipBase64) {
        this.payloadZipBase64 = payloadZipBase64;
        return this;
    }

    @SpacePropertyStorageAdapter(AesBase64Adapter.class)
    public String getPayloadAesBase64() {
        return payloadAesBase64;
    }
    public Message setPayloadAesBase64(String payloadAesBase64) {
        this.payloadAesBase64 = payloadAesBase64;
        return this;
    }

    @SpacePropertyStorageAdapter(AesGcmAdapter.class)
    public String getPayloadNotMatchable() {
        return payloadNotMatchable;
    }
    public Message setPayloadNotMatchable(String payloadNotMatchable) {
        this.payloadNotMatchable = payloadNotMatchable;
        return this;
    }
}
