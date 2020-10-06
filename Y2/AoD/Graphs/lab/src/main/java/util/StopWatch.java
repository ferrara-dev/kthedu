package util;

public class StopWatch {
    private Long startTime;

    public StopWatch(){
        startTime = System.currentTimeMillis();
    }

    public double elapsedTime(){
        Long stop = System.currentTimeMillis();
        return stop - startTime;
    }
}
