package com.planning.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yxc
 * @since 2020-10-24 9:33
 **/
/*@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1)
@Measurement(iterations = 2)
@Threads(1)
@Fork(1)
@State(Scope.Thread)*/
public class HashSetBenchmark {

    public static class MyState {
        private Set<Employee> employeesSet1 = new HashSet<>();
        private Set<Employee> employeesSet2 = new HashSet<>();
        private Set<Employee> employeesSet3 = new HashSet<>();
        private Set<Employee> employeesSet4 = new HashSet<>();

        private List<Employee> employeeList1 = new ArrayList<>();
        private List<Employee> employeeList2 = new ArrayList<>();

        private long set1Size = 60000;
        private long set2Size = 50000;
        private long set3Size = 50000;
        private long set4Size = 60000;

        private long list1Size = 50000;
        private long list2Size = 60000;

        //@Setup(Level.Trial)
        public void setUp(){
            for (long i = 0; i < set1Size; i++) {
                employeesSet1.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < set2Size; i++) {
                employeesSet2.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < set3Size; i++) {
                employeesSet3.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < set4Size; i++) {
                employeesSet4.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < list1Size; i++) {
                employeeList1.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < list2Size; i++) {
                employeeList2.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }
        }

        //@Benchmark
        public boolean given_sizeOfHashSetGreaterThanSizeOfCollection_when_removeAllFromHashSet_then_goodPerformance(MyState state){
            return state.employeesSet1.removeAll(state.employeeList1);
        }

       // @Benchmark
        public boolean given_sizeOfHashSetSmallerThanSizeOfCollection_when_removeAllFromHashSet_then_badPerformance(MyState state){
            return state.employeesSet2.removeAll(state.employeeList2);
        }

        //@Benchmark
        public boolean given_sizeOfHashSetSmallerThanSizeOfAnotherHashSet_when_removeAllFromHashSet_then_goodPerformance(MyState state){
            return state.employeesSet3.removeAll(state.employeesSet4);
        }

        public static void main(String[] args) throws Exception {
/*            Options options = new OptionsBuilder().include(HashSetBenchmark.class.getSimpleName())
                    .threads(1)
                    .forks(1)
                    .shouldFailOnError(true)
                    .shouldDoGC(true)
                    .jvmArgs("-server")
                    .build();
            new Runner(options).run();*/

            Options options = new OptionsBuilder()
                    .include(HashSetBenchmark.class.getSimpleName())
                    .build();
            new Runner(options).run();
        }

    }

    @Data
    @AllArgsConstructor
    public static class Employee {

        private long id;
        private String name;
    }

}