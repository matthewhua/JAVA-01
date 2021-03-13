package matthew.service;

import java.util.List;
import java.util.Map;

/**
 * @author Matthew
 * @date 2021-03-13 21:27
 */
public interface OrderService {

    String testLocalTransactionSucceed();
    String testLocalTransactionFailed();
    String testDistributeTransactionSucceed();
    String testDistributeTransactionFailed();
    void addOrder(Map<String, Object> orderMap);
    List<Map<String,Object>> getByProductId(int productId);
    List<Map<String,Object>> getAll();
    void removeAll();
}
