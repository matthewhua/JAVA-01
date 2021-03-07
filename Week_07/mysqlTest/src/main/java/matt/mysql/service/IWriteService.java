package matt.mysql.service;

/**
 * @author Matthew
 * @date 2021-03-07 21:38
 */
public interface IWriteService<T> {
    void write(T t);

    void writeDs2(T t);

    void writeDs3(T t);
}
