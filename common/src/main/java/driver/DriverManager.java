package driver;

public interface DriverManager<T> {

    void startDriver();

    void stopDriver();

    T getDriver();

}
