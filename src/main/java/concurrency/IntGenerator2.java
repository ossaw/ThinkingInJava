package concurrency;

public abstract class IntGenerator2 {
    private volatile boolean cancel = false;

    abstract int next();

    public void cancel() {
        cancel = true;
    }

    public boolean isCancel() {
        return cancel;
    }

}
