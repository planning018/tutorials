package com.planning.concurrent.threadpool;

import java.util.Random;

/**
 * @author planning
 * @since 2019-10-30 16:59
 **/
public class ThreadUtils {

    public static double[] createArrayOfRandomDoubles() {
        double[] doubles = new double[10001];
        Random random = new Random(10);
        for (int i = 0; i < 10000; i++){
            doubles[i] = random.nextDouble();
        }
        return doubles;
    }
}