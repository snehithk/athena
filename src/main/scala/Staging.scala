import java.io.ByteArrayInputStream

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}


object Staging extends App {

  val s3client:AmazonS3 = AmazonS3ClientBuilder
    .standard()
    //.withCredentials(new AWSStaticCredentialsProvider(credentials))
    .withRegion(Regions.US_EAST_1)
    .build();
val bucketname = "course8-aws"
val folder= "assignment8"
  if (s3client.doesBucketExistV2(bucketname))
    {
      println("already exist")
      s3client.deleteBucket(bucketname)
      println("deldeted")
    }

    s3client.createBucket(bucketname)
    //s3client.
  val filepath = "/home/snehith/course8/"
  val folder1 ="assignment1/"
  val meta = new ObjectMetadata()
  meta.setContentLength(0)
  val empty = new ByteArrayInputStream(new Array[Byte](0))
s3client.putObject(bucketname,folder1,empty,meta)

val tripsname = "assignment1/trips/"
val frequenciesname= "assignment1/frequencies/"
  val calendar_datename = "assignment1/calendar_dates/"
  val trips=s3client.putObject(bucketname,tripsname,empty,meta)
  val frequencies = s3client.putObject(bucketname,frequenciesname,empty,meta)
  val calendar_dates = s3client.putObject(bucketname,calendar_datename,empty,meta)
}
