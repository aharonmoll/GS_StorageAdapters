/*
 * Copyright (c) 2008-2016, GigaSpaces Technologies, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mycompany.app;

import com.mycompany.app.model.Message;
import com.mycompany.app.model.MessageWithIndexes;
import org.openspaces.core.*;
import org.openspaces.core.space.*;

import java.util.function.Supplier;

public class StrorageAdapters {
    private static Supplier<Message> messageFactory=initMessageFactory("pojo");
    private static Message[] entries=null;
    private static GigaSpace gigaSpace;
    public static void main(String[] args) {
        String spaceName = args.length == 0 ? null : args[0];
        String locators = args.length == 1 ? "localhost" : args[1];
        GigaSpace gigaSpace = getOrCreateSpace(spaceName,locators);
        System.out.println("Connected to space " + gigaSpace.getName());

        // Your code goes here, for example:
        entries = generateData(10);
        System.out.println("Writing " + entries.length + " entries of type " + messageFactory.get().getClass().getSimpleName());
        gigaSpace.writeMultiple(entries);
        System.out.println("Entries in space: " + gigaSpace.count(null));
        testReadById(gigaSpace);
        System.out.println("Program completed successfully");
        System.exit(0);
    }

    public static GigaSpace getOrCreateSpace(String spaceName, String locators) {
        if (spaceName == null) {
            System.out.println("Space name not provided - creating an embedded space...");
            return new GigaSpaceConfigurer(new EmbeddedSpaceConfigurer("mySpace")).create();
        } else {
            System.out.printf("Connecting to space %s...%n", spaceName);
            try {
                return new GigaSpaceConfigurer(new SpaceProxyConfigurer(spaceName).lookupLocators(locators)).create();
            } catch (CannotFindSpaceException e) {
                System.err.println("Failed to find space: " + e.getMessage());
                throw e;
            }
        }
    }

    private static Message[] generateData(int numOfEntries) {
        Message[] entries = new Message[numOfEntries];
        for (int i=0 ; i < numOfEntries ; i++) {
            entries[i] = messageFactory.get()
                    .setId(i)
                    .setPayload("payload-" + i)
                    .setPayloadBinary("payload-binary-" + i)
                    .setPayloadBinaryBase64("payload-binary-base64-" + i)
                    .setPayloadZip("payload-zip-" + i)
                    .setPayloadZipBase64("payload-zip-base64-" + i)
                    .setPayloadAesBase64("payload-aes-base64-" + i)
                    .setPayloadNotMatchable("payload-non-matchable-" + i);
        }
        return entries;
    }

    private static void testReadById(GigaSpace gigaSpace) {
        System.out.println("Testing readById");
        for (int i=0 ; i < entries.length ; i++) {
            Message result = gigaSpace.readById(Message.class, i);
            System.out.println("readById-" + i+" "+
                                result.getPayload()+" "+
                                result.getPayloadBinary()+" "+
                                result.getPayloadBinaryBase64() +" "+
                                result.getPayloadZip()+" "+
                                result.getPayloadZipBase64()+" "+
                                result.getPayloadAesBase64()+" "+
                                result.getPayloadNotMatchable());
        }
    }

    private static Supplier<Message> initMessageFactory(String entryType) {
        switch (entryType) {
            case "pojo": return Message::new;
            case "pojoWithIndexes": return MessageWithIndexes::new;
            default: throw new IllegalArgumentException("Unsupported entry type: " + entryType);
        }
    }
}
