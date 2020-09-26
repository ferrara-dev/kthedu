package util;

public class Stopwatch {
    private final Long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }


    public Double elapsedMilli(){
        long now = System.currentTimeMillis();
        return (double) (now - start);
    }
}
