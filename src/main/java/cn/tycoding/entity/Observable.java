package cn.tycoding.entity;

public interface Observable {
    public void Attach(Administrator admin);
    public void Detach(Administrator admin);
    public void Notify();
}
