package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public void saveItem(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(item);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateItem(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(item);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteItem(Item item) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(item);

        transaction.commit();
        session.close();
    }

    @Override
    public Item get(int i) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, i);

        transaction.commit();
        session.close();

        return item;
    }
}
