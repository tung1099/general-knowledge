package com.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

@Slf4j
public class BenchMarkUtils {

    public static StopWatch start(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        return stopWatch;
    }

    public static void end(StopWatch stopWatch, String nameFunction){
        stopWatch.stop();
        log.info("{} takes {} milliseconds", nameFunction, stopWatch.getTotalTimeMillis());
    }
}
