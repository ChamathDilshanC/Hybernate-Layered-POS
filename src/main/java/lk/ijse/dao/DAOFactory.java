package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.ItemDAOImpl;

public class DAOFactory {

    public enum DAOType{
        CUSTOMER,ITEM
    }

    public static SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            default:
                return null;
        }
    }
}
