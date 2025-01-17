package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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


        System.out.println("\n ================ TEST5 ===============");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update Completed");

        System.out.println("\n ================ TEST6 ===============");
        System.out.print("Enter id for deleste test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete Completed");

        sc.close();

    }
}