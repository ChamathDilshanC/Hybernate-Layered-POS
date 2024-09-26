package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Item;

public interface ItemDAO extends SuperDAO {

    void saveItem(Item item);

    void updateItem(Item item);

    void deleteItem(Item item);

    Item get(int i);
}
