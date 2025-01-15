package model.dao;

import db.DB;
import model.dao.model.dao.impl.DepartamentDaoJDBC;
import model.dao.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao(){
        return new DepartamentDaoJDBC(DB.getConnection());
    }
}
