package model.dao.model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartamentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    
    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO department\n" +
                    "(Name)\n" +
                    "VALUES\n" +
                    "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,department.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            }else {
                throw new DbException("Insert failed");
            }
        }catch (SQLException e) {
            throw new DbException("Insert failed");
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE department\n" +
                    "SET Name = ?\n" +
                    "WHERE Id = ?");
            st.setString(1,department.getName());
            st.setInt(2,department.getId());

            st.executeUpdate();
        }catch (SQLException e) {
            throw new DbException("Update failed");
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM department\n" +
                    "WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e) {
            throw new DbException("Delete failed");
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE department.Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if (rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                return department;
            }
            return null;
        }catch (SQLException e) {
            throw new DbException("Find failed");
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT  * FROM  department ORDER BY  Name");
            rs = st.executeQuery();
            List<Department> departments = new ArrayList<>();
            
            while (rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));
                departments.add(department);
            }
            return departments;
        }catch (SQLException e) {
            throw new DbException("Find failed");
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
}
