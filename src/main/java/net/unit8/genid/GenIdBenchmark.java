package net.unit8.genid;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.UUID;

public class GenIdBenchmark {
    private static Flake flake = new Flake();
    private static TomcatStandardSessionId tomcat = new TomcatStandardSessionId();
    private static JettySessionId jetty = new JettySessionId();


    @Benchmark
    public String uuidv4() {
        return UUID.randomUUID().toString();
    }

    @Benchmark
    public String uuidv1() {
        return new com.groupon.uuid.UUID().toString();
    }

    @Benchmark
    public String tomcat() {
        return tomcat.generateSessionId();
    }

    @Benchmark
    public String jetty() {
        return jetty.generateSessionId();
    }

    @Benchmark
    public String flake() {
        return flake.generateId();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(GenIdBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .forks(1)
                .mode(Mode.Throughput)
                .build();
        new Runner(opt).run();
    }
}
