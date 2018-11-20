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

val wordsDS = sc.parallelize(Seq("Gouri Prasad Mahapatra", "Bhwani Prasad Mahapatra")).toDS()
val groupDS = wordsDS.flatMap(_.toLowerCase.split(" "))
            .filter(_ != "")
            .groupBy("value")
val countDS = groupDS.count()
countDS.show()



// COMMAND ----------

case class Employee(name: String, age: Int, departmentId: Int, salary: Double)
case class Department(id: Int, name: String)

case class Record(name: String, age: Int, salary: Double, departmentId: Int, departmentName: String)
case class ResultSet(departmentId: Int, departmentName: String, avgSalary: Double)

val employeeDataSet1 = sc.parallelize(Seq(Employee("Max", 22, 1, 100000.0), Employee("Adam", 33, 2, 93000.0), Employee("Eve", 35, 2, 89999.0), Employee("Muller", 39, 3, 120000.0))).toDS()
val employeeDataSet2 = sc.parallelize(Seq(Employee("John", 26, 1, 990000.0), Employee("Joe", 38, 3, 115000.0))).toDS()
val departmentDataSet = sc.parallelize(Seq(Department(1, "Engineering"), Department(2, "Marketing"), Department(3, "Sales"))).toDS()

val employeeDataset = employeeDataSet1.union(employeeDataSet2)

def averageSalary(key: (Int, String), iterator: Iterator[Record]): ResultSet = {
  val (total, count) = iterator.foldLeft(0.0, 0.0) {
      case ((total, count), x) => (total + x.salary, count + 1)
  }
  ResultSet(key._1, key._2, total/count)
}

val averageSalaryDataset = employeeDataset.joinWith(departmentDataSet, $"departmentId" === $"id", "inner")
                                          .map(record => Record(record._1.name, record._1.age, record._1.salary, record._1.departmentId, record._2.name))
                                          .filter(record => record.age > 25)
                                          .groupBy($"departmentId", $"departmentName")
                                          .avg()

averageSalaryDataset.show()

// COMMAND ----------

import org.apache.spark.sql.functions._

val wordsDataset = sc.parallelize(Seq("Spark I am your father", "May the spark be with you", "Spark I am your father")).toDS()
val result = wordsDataset
              .flatMap(_.split(" "))               // Split on whitespace
              .filter(_ != "")                     // Filter empty words
              .map(_.toLowerCase())
              .toDF()                              // Convert to DataFrame to perform aggregation / sorting
              .groupBy($"value")                   // Count number of occurrences of each word
              .agg(count("*") as "numOccurances")
              .orderBy($"numOccurances" desc)      // Show most common words first
result.show()

// COMMAND ----------


