package com.mycompany.app.adapters;

import com.gigaspaces.client.storage_adapters.BinaryAdapter;

public class BinaryBase64Adapter extends BinaryAdapter {
    @Override
    public boolean useBase64Wrapper() {
        return true;
    }
}
