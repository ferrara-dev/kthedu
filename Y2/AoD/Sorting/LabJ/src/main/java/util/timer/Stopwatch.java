package util.timer;


/**
 * Class responsible for measuring elapsed time.
 * The stopwatch is started at initiation
 */
public class Stopwatch {
    private static final double NANO_TO_MILLI_FACTOR = 0.000001;
    private final Long start;
    private final TimeUnit timeUnit;

    public Stopwatch(TimeUnit timeUnit){
        this.timeUnit = timeUnit;
        this.start = System.nanoTime();
    }

    public Stopwatch(){
        this.timeUnit = TimeUnit.NANOSECONDS;
        this.start = System.nanoTime();
    }

    public Double elapsedTime() {
        long now = System.nanoTime();
        double elapsedTime = (double) (now - start);

        if(timeUnit.equals(TimeUnit.NANOSECONDS))
            return elapsedTime*NANO_TO_MILLI_FACTOR;
        else
            return elapsedTime;
    }
}
