package util.stopwatch;

public enum StopwatchFactory {
    NANO(){
        @Override
        public Stopwatch getStopwatch() {
            return new NanoStopwatch();
        }
    },
    MILLI(){
        @Override
        public Stopwatch getStopwatch() {
            return new MilliStopwatch();
        }
    },
    SECOND(){
        @Override
        public Stopwatch getStopwatch() {
            return new SecondStopwatch();
        }
    };

    public abstract Stopwatch getStopwatch();
}
