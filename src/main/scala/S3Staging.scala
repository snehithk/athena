import java.io.{ByteArrayInputStream, File}
import java.sql.{Connection, DriverManager, Statement}

import Staging.{bucketname, calendar_datename, empty, folder1, frequenciesname, meta, s3client, tripfile, tripsname}
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}

trait S3Staging extends App {
  val s3client: AmazonS3 = AmazonS3ClientBuilder
    .standard()
    //.withCredentials(new AWSStaticCredentialsProvider(credentials))
    .withRegion(Regions.US_EAST_1)
    .build()
  val driverName: String = "com.simba.athena.jdbc.Driver"
  Class.forName(driverName)
  val connection: Connection = DriverManager
    .getConnection("jdbc:awsathena://AwsRegion=us-east-1;S3OutputLocation=s3://course8-aws/;" +
      "AwsCredentialsProviderClass=com.simba.athena.amazonaws.auth.profile.ProfileCredentialsProvider;" +
      "AwsCredentialsProviderArguments=default;")
  val stmt: Statement = connection.createStatement()

  val bucketname = "course8_aws11"
  val folder = "assignment8"
  val tripsname = "assignment1/trips/"
  val frequenciesname = "assignment1/frequencies/"
  val calendar_datename = "assignment1/calendar_dates/"
  val tripfile = "assignment1/trips/trips.txt"
  val frequencyfile= "assignment1/frequenciesname/frequencies.txt"
  val calendardatesfile ="assignment1/calenda_dates/calendar_dates.txt"
  val tloc= "/home/snehith/Documents/stm/trips.txt"
  val floc ="/home/snehith/Documents/stm/frequencies.txt"
  val caloc = "//home/snehith/Documents/stm/calendar_dates.txt"

  def Staging() {

    s3client.createBucket(bucketname)
    val meta = new ObjectMetadata()
    meta.setContentLength(0)
    val empty = new ByteArrayInputStream(new Array[Byte](0))
    s3client.putObject(bucketname, folder, empty, meta)
    val trips = s3client.putObject(bucketname, tripsname, empty, meta)
    val frequencies = s3client.putObject(bucketname, frequenciesname, empty, meta)
    val calendar_dates = s3client.putObject(bucketname, calendar_datename, empty, meta)


  }
  def upload(): Unit ={
    s3client.putObject(bucketname,tripfile,new File(tloc))
    s3client.putObject(bucketname,tripfile,new File(floc))
    s3client.putObject(bucketname,tripfile,new File(caloc))
  }

}
