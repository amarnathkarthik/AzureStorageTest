# Utility to copy data to Azure Storage ADLS Gen2 using Hadoop-Azure library

# Service Principal (SPN)

Utility requires to provide actual Service Principal and please make sure SPN has correct IAM role or POSIX permission
to RWX on the storage.

# Replace Azure credential

```
/** Replace Service Principals/App Client ID **/
conf.set("fs.azure.account.oauth2.client.id", "REPLACE_APP_CLIENT_ID");
/** Replace Service Principals/App Client Secret **/
conf.set("fs.azure.account.oauth2.client.secret", "REPLACE_APP_CLIENT_SECRET");
``` 

# Replace Azure Tenent ID

```
/** Replace Tenant ID **/
conf.set("fs.azure.account.oauth2.client.endpoint", "https://login.microsoftonline.com/<REPLACE_TENANT_ID>/oauth2/token");
```

# Generate executable Jar
Executable jar file will be generated and available under build/libs
```
./gradlew clean build
```

# Run utility
```
java -jar "STORAGE_ACCOUNT_NAME" "SOURCE_PATH" "DESTINATION_PATH"
```

Ex:
```
java -jar "abfs://container@teststrorage.dfs.core.windows.net/" "SOURCE_PATH" "DESTINATION_PATH"
```