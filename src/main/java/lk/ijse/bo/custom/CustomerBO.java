package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {

    void save(CustomerDTO customerDTO) throws Exception;

    void update(CustomerDTO customerDTO);

    void delete(CustomerDTO customerDTO);

    CustomerDTO get(int i);
}
