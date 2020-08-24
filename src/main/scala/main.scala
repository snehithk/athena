object main extends S3Staging {
val create = new Queries
  create.Staging()
  create.athena()
  create.upload()
  create.Insert()
}
