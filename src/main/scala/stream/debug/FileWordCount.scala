package stream.debug

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

/**
  * Created by kawhi on 31/01/2018.
  *
  * A pipeline version of stream "word count".
  *
  */


object FileWordCount {

  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)

//    val conf = new Configuration()
//    conf.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true)
    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    val env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)
    env.setParallelism(1)

    val filePath = params.get("file-input")

    val srcData = env.fromCollection {
      List(
        "AA,1",
        "AA,1",
        "AA,1",
        "AA,1",
        "AA,1",
        "AA,1",
        "AA,1",
        "AA,1",
        "CC,1",
        "CC,1"
      )
    }

    srcData
      .map(x => {
        val tmp = x.split(",")
        println(x)
        NumWithCount(tmp(0), tmp(1).toLong)
      })
      .keyBy("word").sum(1)
      .print()

    env.execute("FileWordCount")
  }

  /** Data type for words with count */
  case class NumWithCount(word: String, count: Long)
}
