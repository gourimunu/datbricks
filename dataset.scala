// Databricks notebook source
val dataset = Seq(1,2,3,45).toDS()

// COMMAND ----------

dataset.show()

// COMMAND ----------

case class Person(name: String, age: Int)

val personDS = Seq(Person("Gouri", 40), Person("Ram", 30), Person("Divakr", 30)).toDS()
personDS.show()

// COMMAND ----------

case class Itemmaster(VendItemID: String, ItemId: String, ModelSeries: String, Model: String, Color: String, Price: Int)
val hsDS = Seq(Itemmaster("fdgfgdfg","gfdfdfg", "ダミーeee", "dfgdfg", "STD", 30000),
              Itemmaster("1dfePhone","FeaturePhone", "ダdfミーee", "Phone", "STD", 30000),
              Itemmaster("fdeaturePhone","FeatfurePhone", "ダfdgee", "Phone", "STD", 30000)).toDS()
hsDS.show()


// COMMAND ----------

val rdd = sc.parallelize(Seq((1, "Spark"), (2, "ETL"),(3, "C#") ))
val ds = rdd.toDS()
ds.show()




// COMMAND ----------

case class Company(name: String, foundingYear: Int, numEmployees: Int, address: String)
val inputSeq = Seq(Company("ABC", 1998, 310, "ksd saih sau"), Company("XYZ", 1983, 904, "wqiuewbw w qw"), Company("NOP", 2005, 83,"wewqi wqiu wqiu"))
val df = sc.parallelize(inputSeq).toDF()
val companyDS = df.as[Company]
companyDS.show()


// COMMAND ----------

val inputSeq = Seq(("ABC", 1998, 310, "ksd saih sau"), ("XYZ", 1983, 904, "wqiuewbw w qw"), ("NOP", 2005, 83,"wewqi wqiu wqiu"))
val df = sc.parallelize(inputSeq).toDF()
val companyDS = df.as[(String, Int,Int,String)]
companyDS.show()

// COMMAND ----------


