package com.duke.fuzzy_app;

public class Triple<T,U,V>{
    
    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public V getThird() {
        return this.third;
    }

    private final T first;
    private final U second;
    private final V third;


}