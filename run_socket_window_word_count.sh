#!/usr/bin/env bash
../flink-1.4.0/bin/flink run \
    -c stream.debug.SocketWindowWordCount \
    ./target/flink-stream-debug-1.0-SNAPSHOT.jar \
    --port 9000

