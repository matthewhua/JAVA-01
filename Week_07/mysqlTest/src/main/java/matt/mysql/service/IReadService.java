package matt.mysql.service;

import java.util.List;

/**
 * @author Matthew
 * @date 2021-03-07 21:38
 */
public interface IReadService<T> {
    public List<T> readFromSlaves();

    public List<T> readFromDs2();

    List<T> readFromMain();
}
