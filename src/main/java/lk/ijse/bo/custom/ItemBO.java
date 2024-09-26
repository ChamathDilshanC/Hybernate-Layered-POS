package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;

public interface ItemBO extends SuperBO {

    void save(ItemDTO itemDTO);

    void update(ItemDTO itemDTO);

    void delete(ItemDTO itemDTO);

    ItemDTO get(int i);
}
