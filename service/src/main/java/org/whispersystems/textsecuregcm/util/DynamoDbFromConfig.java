package org.whispersystems.textsecuregcm.util;

import org.whispersystems.textsecuregcm.configuration.DynamoDbConfiguration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientAsyncConfiguration;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.client.config.SdkAdvancedAsyncClientOption;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

import java.util.concurrent.Executor;
import java.net.URI;
public class DynamoDbFromConfig {
  private static ClientOverrideConfiguration clientOverrideConfiguration(DynamoDbConfiguration config) {
    System.out.println("Hello");
    return ClientOverrideConfiguration.builder()
        .apiCallTimeout(config.getClientExecutionTimeout())
        .apiCallAttemptTimeout(config.getClientRequestTimeout())
        .build();
  }
  public static DynamoDbClient client(DynamoDbConfiguration config, AwsCredentialsProvider credentialsProvider) {
    System.out.println(config.getRegion());
    System.setProperty("aws.region", "ap-southeast-1");
    System.setProperty("aws.accessKeyId", "AKIAYOGDMWVEPQL7CV5O");
    System.setProperty("aws.secretAccessKey", "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");
    return DynamoDbClientBuilder.standard()
        .withEndpointConfiguration(new EndpointConfiguration(
          "https://dynamodb.ap-southeast-1.amazonaws.com",
          "ap-southeast-1"))
        // .region(Region.AP_SOUTHEAST_1)
        // .endpointOverride(URI.create("https://dynamodb.ap-southeast-1.amazonaws.com"))
        .credentialsProvider(credentialsProvider)
        .overrideConfiguration(clientOverrideConfiguration(config))
        .build();
  }
  public static DynamoDbAsyncClient asyncClient(DynamoDbConfiguration config, AwsCredentialsProvider credentialsProvider, Executor executor) {
    System.setProperty("aws.region", "ap-southeast-1");
    System.setProperty("aws.accessKeyId", "AKIAYOGDMWVEPQL7CV5O");
    System.setProperty("aws.secretAccessKey", "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");
   
    DynamoDbAsyncClientBuilder builder = DynamoDbAsyncClient.builder()
        .region(Region.AP_SOUTHEAST_1).endpointOverride(URI.create("https://dynamodb.ap-southeast-1.amazonaws.com"))
        .credentialsProvider(credentialsProvider)
        .overrideConfiguration(clientOverrideConfiguration(config));
    if (executor != null) {
      builder.asyncConfiguration(ClientAsyncConfiguration.builder()
          .advancedOption(SdkAdvancedAsyncClientOption.FUTURE_COMPLETION_EXECUTOR,
              executor)
          .build());
    }
    return builder.build();
  }
}
