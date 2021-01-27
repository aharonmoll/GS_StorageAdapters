package com.mycompany.app.adapters;

import com.gigaspaces.client.storage_adapters.AesCbcAdapter;

public class AesBase64Adapter extends AesCbcAdapter {
    @Override
    public boolean useBase64Wrapper() {
        return true;
    }

    @Override
    public boolean supportsEqualsMatching() {
        return true;
    }

    @Override
    protected byte[] generateInitializationVector(int length) {
        return new byte[length];
    }
}
