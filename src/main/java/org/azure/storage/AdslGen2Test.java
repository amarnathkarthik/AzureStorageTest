package org.azure.storage;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class AdslGen2Test {
  public static void main(String[] args) throws IOException {
    String storageEndpoint = args[0];
    String sourcePath = args[1];
    String targetPath = args[2];
    Configuration conf = new Configuration();
    conf.set("fs.defaultFS", storageEndpoint);
    conf.set("fs.azure.account.auth.type", "OAuth");
    conf.set("fs.azure.account.oauth.provider.type", "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider");
    /** Replace Service Principals/App Client ID **/
    conf.set("fs.azure.account.oauth2.client.id", "REPLACE_APP_CLIENT_ID");
    /** Replace Service Principals/App Client Secret **/
    conf.set("fs.azure.account.oauth2.client.secret", "REPLACE_APP_CLIENT_SECRET");
    conf.set("fs.azure.account.oauth2.client.endpoint",
        "https://login.microsoftonline.com/658728e7-1632-412a-9815-fe53f53ec58b/oauth2/token");

    FileSystem fs = FileSystem.get(conf);
    Instant start = Instant.now();
    fs.copyFromLocalFile(new Path(sourcePath), new Path(targetPath));
    Instant finish = Instant.now();
    System.out.println("Time Elapsed: " + Duration.between(start, finish).toMillis());

    FileStatus fileStatus = fs.getFileStatus(new Path(targetPath));
    System.out.println(fileStatus.getPath() + " " + fileStatus.getPermission() + " " + fileStatus.getOwner() + " "
        + fileStatus.getGroup());
  }
}
