package cn.tycoding.util;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public URL executeStrategy(long bookId){
        return strategy.getBookURL(bookId);
    }
}
