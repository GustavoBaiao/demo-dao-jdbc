package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n ================ TEST2 ===============");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller s : list) {
            System.out.println(s);
        }
        System.out.println("\n ================ TEST3 ===============");
        List<Seller> list2 = sellerDao.findAll();
        for (Seller s : list2) {
            System.out.println(s);
        }

        System.out.println("\n ================ TEST4 ===============");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(),4000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inseted! new id = " + newSeller.getId());


    }
}