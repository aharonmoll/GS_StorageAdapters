# GigaSpaces Storage Adapters Example

##### Add the following as a JVM parameter/option:
*   -Dcom.gs.property-storage.aes.passphrase=0b05308c9a00f07044416bad7a51bacd282fc5c0c999551a4ff15c302b268b204df875993770411044fb35953166ee7833c32ca0741e9fec091dfa10138039e8

##### Another option is to use:
*   -Dcom.gs.property-storage.aes.passphrase-path=~/passphrase.txt (passphrase will be inside the file)

##### Add space name and locators as Program Arguments

![Screenshot](./Pictures/Picture1.png)

#### Please run:
    com.mycompany.app.StrorageAdapters.main()
    
#### For more usages examples please review:
    com.gigaspaces.test.storage_adapter.StorageAdapterBasicTest
    
*   The above class can be found in XAP Premium git:

 https://github.com/Gigaspaces/xap-premium/tree/master/xap-tests/xap-tests-datagrid/src/main/java/com/gigaspaces/test/storage_adapter
    
#### To execute on Linux aws machine
* Put runStorageAdapters.sh & GS_StorageAdapters-0.1.jar in CSM home dir (/home/ec2-user)
* Update all relevant parameters to fit your environment.<br>
**Note:**<br>
Current runStorageAdapters.sh sports SSL communication if not needed please remove SSL unnecessary parameters.
*   `./runStorageAdapters.sh`
* Output should be something like:

![Screenshot](./Pictures/Picture2.png)


 



