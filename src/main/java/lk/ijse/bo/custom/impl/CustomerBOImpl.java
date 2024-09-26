package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public void save(CustomerDTO customerDTO) throws Exception {
        customerDAO.saveCustomer(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel()));
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        customerDAO.updateCustomer(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel()));
    }

    @Override
    public void delete(CustomerDTO customerDTO) {
        customerDAO.deleteCustomer(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel()));
    }

    @Override
    public CustomerDTO get(int i) {
        Customer customer = customerDAO.get(i);
        return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getTel());
    }
}
