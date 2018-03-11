package works

import org.apache.spark.sql.SparkSession
import com.crealytics.spark.excel

object excelToHive {

     def main(args: Array[String]): Unit = {
       System.setProperty("hadoop.home.dir", "S:\\Hadoop")
       val spark = SparkSession.builder()
         .appName("ExcelToHive")
         .master("local")
         // .config("hive.metastore.uris","thrift://hpchd1.hpc.ford.com:9083")
         // .enableHiveSupport()
         .getOrCreate()
       import spark.sqlContext.implicits._
       val df = spark.read
         .format("com.crealytics.spark.excel")
         .option("sheetName", "Sheet1")
         .option("useHeader", "true")
         .option("treatEmptyValuesAsNulls", "false")
         .option("inferSchema", "true")
         .option("addColorColumns", "true")
         .option("startColumn", 0)
         .option("endColumn", 99)
         .option("timestampFormat", "MM-dd-yyyy HH:mm:ss")
         .option("maxRowsInMemory", 20)
         .option("excerptSize", 10)
         .load("C:\\Users\\dell\\Downloads\\Character_Repository\\Character_Repository\\cupid.xlsx")

       df.show()
     }
}
