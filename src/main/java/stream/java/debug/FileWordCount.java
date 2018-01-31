package stream.java.debug;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.curator.org.apache.curator.shaded.com.google.common.collect.ArrayListMultimap;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.List;

/**
 * Created by kawhi on 31/01/2018.
 */
public class FileWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.fromElements(
                "AA,1",
                "AA,1",
                "AA,1",
                "AA,1",
                "AA,1",
                "AA,1",
                "AA,1",
                "AA,1",
                "CC,1",
                "CC,1")
                .map(new MapFunction<String, WordWithCount>() {
                    @Override
                    public WordWithCount map(String value) throws Exception {
                        String[] tmp = value.split(",");
                        return new WordWithCount(tmp[0], Long.parseLong(tmp[1]));
                    }
                })
                .keyBy("word")
                .sum("count")
                .print();

        env.execute("FileWordCount-java");

    }

    /**
     * Data type for words with count.
     */
    public static class WordWithCount {

        public String word;
        public long count;

        public WordWithCount() {
        }

        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }
}
