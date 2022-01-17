package com.planning.collections;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yxc
 * @since 2020-10-24 11:28
 **/
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1)
@Measurement(iterations = 2)
@Threads(1)
@Fork(1)
@State(Scope.Thread)
public class Jackson2UtilBenchmark {

    private static String raw = "[{\"id\":1,\"name\":\"name1\",\"data\":[\"a\",\"b\",\"c\"]," +
            "\"createTime\":\"2019-05-09T01:53:13.396Z\",\"modifyTime\":\"2019-05-10T01:53:13.396Z\"}," +
            "{\"id\":2,\"name\":\"name2\",\"data\":[\"d\",\"e\",\"f\"],\"createTime\":\"2019-05-01T01:53:13.396Z\"," +
            "\"modifyTime\":\"2019-05-02T01:53:13.396Z\"}]";

    private ObjectMapper objectMapper = new ObjectMapper();

    private JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, SimplePojo.class);

    private JavaType javaTypeDate = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, SimplePojoDate.class);

    @Setup(Level.Trial)
    public void setup() {
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Benchmark
    public void parseLocalDateTime(Blackhole bh) throws IOException {
        bh.consume(objectMapper.readValue(raw, javaType));
    }

    @Benchmark
    public void parseDate(Blackhole bh) throws IOException {
        bh.consume(objectMapper.readValue(raw, javaTypeDate));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Jackson2UtilBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}

@Data
class SimplePojo {
    private Integer id;
    private String name;
    private List<String> data;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}

@Data
class SimplePojoDate {
    private Integer id;
    private String name;
    private List<String> data;
    private Date createTime;
    private Date modifyTime;
}