package com.codebase.framework.ceph;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.StringUtils;

import java.io.File;
import java.util.List;

public class VdianS3UploadDriver {

    public static void main(String[] args) {
        final String bucketName = "vdianmq";
        final String accessKey = "MR64FSOGXC3F0Z050695";
        final String secretKey = "4q3qRhnPyB3YHUAfGeBRddMb8kYcAaWpE4uHVcW0";
        final String endPoint = "http://s3.idcvdian.com";

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setMaxConnections(100);
        clientConfig.setRequestTimeout(1000000);
        clientConfig.setSignerOverride("S3SignerType");
        clientConfig.setProtocol(Protocol.HTTP);

        AmazonS3 s3Client = new AmazonS3Client(credentials, clientConfig);
        s3Client.setEndpoint(endPoint);

        S3ClientOptions s3ClientOptions = new S3ClientOptions();
        s3ClientOptions.setPathStyleAccess(true);
        s3Client.setS3ClientOptions(s3ClientOptions);

        //listBuckets(s3Client);
        createBucket(s3Client, bucketName);
        listBuckets(s3Client);

        File file = new File("/Users/koudai213/Documents/4. Weidian/4.5.1 vdianmq相关文档/2. 设计相关/消息过滤架构图.png");
        String uploadFileName = "vdianmq-" + file.getName();
        uploadFileToBucket(s3Client, bucketName, uploadFileName, file);
//
        deleteFileFromBucket(s3Client, bucketName, uploadFileName);
//
//        listBuckets(s3Client);

        System.out.println("finished");
    }

    private static void deleteFileFromBucket(AmazonS3 s3Client, String bucketName, String fileName) {
        DeleteObjectRequest request = new DeleteObjectRequest(bucketName, fileName);
        s3Client.deleteObject(request);
    }

    private static void createBucket(AmazonS3 s3Client, String bucketName) {
        Bucket bucket = s3Client.createBucket(bucketName);
        System.out.println(bucket.getName());
    }

    private static void uploadFileToBucket(AmazonS3 s3Client, String bucketName, String fileName, File file) {
        PutObjectResult result = s3Client.putObject(bucketName, "vdianmq-" + file.getName(), file);
        ObjectMetadata metadata = result.getMetadata();
        System.out.println("upload\t" + result.getVersionId() + "\t" + result.getExpirationTime() + "\t" +
                metadata.getStorageClass());
    }

    private static void listBuckets(AmazonS3 s3Client) {
        long startTime = System.currentTimeMillis();
        List<Bucket> buckets = s3Client.listBuckets();
        for (Bucket bucket : buckets) {
            listBucketObjects(s3Client, bucket);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time: " + (endTime - startTime));
    }

    private static void listBucketObjects(AmazonS3 s3Client, Bucket bucket) {
        System.out.println(bucket.getName() + "\t" +
                StringUtils.fromDate(bucket.getCreationDate()));
        ObjectListing objects = s3Client.listObjects(bucket.getName());
        for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
            System.out.println("listObject\t" + objectSummary.getKey() + "\t" +
                    objectSummary.getSize() + "\t" +
                    StringUtils.fromDate(objectSummary.getLastModified()) +
                    "\t" + objectSummary.getStorageClass() + "\t" + objectSummary.getClass() +
                    "\t" + objectSummary.getOwner()
            );
        }
    }

}
