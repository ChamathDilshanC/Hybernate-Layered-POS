package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public void save(ItemDTO itemDTO) {
        itemDAO.saveItem(new Item(itemDTO.getItemId(), itemDTO.getModel(), itemDTO.getUnitPrice(), itemDTO.getQty()));
    }

    @Override
    public void update(ItemDTO itemDTO) {
        itemDAO.updateItem(new Item(itemDTO.getItemId(), itemDTO.getModel(), itemDTO.getUnitPrice(), itemDTO.getQty()));
    }

    @Override
    public void delete(ItemDTO itemDTO) {
        itemDAO.deleteItem(new Item(itemDTO.getItemId(), itemDTO.getModel(), itemDTO.getUnitPrice(), itemDTO.getQty()));
    }

    @Override
    public ItemDTO get(int i) {
        Item item = itemDAO.get(i);
        return new ItemDTO(item.getItemId(), item.getModel(), item.getUnitPrice(), item.getQty());
    }
}
