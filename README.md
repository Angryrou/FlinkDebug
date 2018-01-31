# Flink Debug For 1.4.0

1. `stream.debug.FileWordCount` and `stream.debug.SocketWindowWordCount`
 can work smoothly in _IntelliJ_ where we set `scala.version` to `2.11.8` and `JDK` to `1.8`

2. When I export the project to a jar package and submit it to local Flink 1.4.0 using [CLI][1], it shows
 `Cannot instantiate user function.`
   - `run_file_word_count.sh` and `run_socket_window_word_count.sh` are the bash code to run `FileWordCount`
   and `SocketWindowWordCount` respectively.
   - the error for `FileWordCount` is as follows:
   ```bash
   org.apache.flink.streaming.runtime.tasks.StreamTaskException: Cannot instantiate user function.
   	at org.apache.flink.streaming.api.graph.StreamConfig.getStreamOperator(StreamConfig.java:235)
   	at org.apache.flink.streaming.runtime.tasks.OperatorChain.createChainedOperator(OperatorChain.java:355)
   	at org.apache.flink.streaming.runtime.tasks.OperatorChain.createOutputCollector(OperatorChain.java:282)
   	at org.apache.flink.streaming.runtime.tasks.OperatorChain.<init>(OperatorChain.java:126)
   	at org.apache.flink.streaming.runtime.tasks.StreamTask.invoke(StreamTask.java:231)
   	at org.apache.flink.runtime.taskmanager.Task.run(Task.java:718)
   	at java.lang.Thread.run(Thread.java:748)
   Caused by: java.lang.ClassCastException: cannot assign instance of stream.debug.FileWordCount$$anonfun$main$1 to field org.apache.flink.streaming.api.scala.DataStream$$anon$4.cleanFun$3 of type scala.Function1 in instance of org.apache.flink.streaming.api.scala.DataStream$$anon$4
   	at java.io.ObjectStreamClass$FieldReflector.setObjFieldValues(ObjectStreamClass.java:2133)
   	at java.io.ObjectStreamClass.setObjFieldValues(ObjectStreamClass.java:1305)
   	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:2251)
   	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2169)
   	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2027)
   	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1535)
   	at java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:2245)
   	at java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2169)
   	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2027)
   	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1535)
   	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:422)
   	at org.apache.flink.util.InstantiationUtil.deserializeObject(InstantiationUtil.java:290)
   	at org.apache.flink.util.InstantiationUtil.readObjectFromConfig(InstantiationUtil.java:248)
   	at org.apache.flink.streaming.api.graph.StreamConfig.getStreamOperator(StreamConfig.java:220)
   	... 6 more
   ```


[1]: https://ci.apache.org/projects/flink/flink-docs-release-1.4/ops/cli.html