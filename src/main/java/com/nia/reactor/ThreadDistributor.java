package com.nia.reactor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程分发器
 */
public class ThreadDistributor {

    private static ThreadPoolExecutor threadPoolExecutor;

    public void start(){
        threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public void distribute(Runnable runnable){
        threadPoolExecutor.execute(runnable);
    }

    public void close() {
        threadPoolExecutor.shutdown();
    }
}
