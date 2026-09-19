package academy.utility;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Util {
	
	
	private static String bucketName = "images-0";
	private static Regions regions = Regions.US_WEST_2;
	
	
	public static void uploadFile(String fileName, InputStream inputStream)
            throws AmazonServiceException, SdkClientException, IOException {
     
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
		           .withRegion(regions).build();

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(inputStream.available());
		
		PutObjectRequest request = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
		s3Client.putObject(request);
    }

}
