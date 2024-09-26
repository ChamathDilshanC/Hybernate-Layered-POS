package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void saveCustomer(Customer customer) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);

        transaction.commit();
        session.close();
    }

    @Override
    public void updateCustomer(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);

        transaction.commit();
        session.close();
    }

    @Override
    public Customer get(int i) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, i);

        transaction.commit();
        session.close();

        return customer;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(customer);

        transaction.commit();
        session.close();
    }
}
