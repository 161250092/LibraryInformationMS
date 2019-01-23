package cn.tycoding.entity;

public interface Observable {
    void attach(Administrator admin);
    void detach(Administrator admin);
    void notifyObservers();
}
