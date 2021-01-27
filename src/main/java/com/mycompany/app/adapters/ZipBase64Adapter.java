package com.mycompany.app.adapters;

import com.gigaspaces.client.storage_adapters.ZipAdapter;

public class ZipBase64Adapter extends ZipAdapter {
    @Override
    public boolean useBase64Wrapper() {
        return true;
    }
}
