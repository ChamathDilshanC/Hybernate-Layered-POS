package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Customer;

public interface CustomerDAO extends SuperDAO {

    void saveCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer);

    void updateCustomer(Customer customer);

    Customer get(int i);
}
