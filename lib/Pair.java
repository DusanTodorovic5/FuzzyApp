package lib;

public class Pair<T,U> {
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    private final T first;
    private final U second;
}
