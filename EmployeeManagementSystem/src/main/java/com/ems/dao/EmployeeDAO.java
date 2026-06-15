package com.ems.dao;

import com.ems.model.Employee;
import com.ems.utility.DBConnection;

import java.sql.*;
import java.util.*;

public class EmployeeDAO {

    // ADD EMPLOYEE
    public boolean addEmployee(Employee emp) {
        boolean status = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO employees(name,email,department,designation,salary,joining_date) VALUES (?,?,?,?,?,?)"
             )) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setString(4, emp.getDesignation());
            ps.setDouble(5, emp.getSalary());
            ps.setDate(6, new java.sql.Date(emp.getJoiningDate().getTime()));

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // GET EMPLOYEES (PAGINATION)
    public List<Employee> getEmployees(int start, int total) {

        List<Employee> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM employees LIMIT ?,?"
             )) {

            ps.setInt(1, start);
            ps.setInt(2, total);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee e = new Employee();

                e.setEmpId(rs.getInt("emp_id")); // ✅ FIXED
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setDepartment(rs.getString("department"));
                e.setDesignation(rs.getString("designation"));
                e.setSalary(rs.getDouble("salary"));
                e.setJoiningDate(rs.getDate("joining_date"));
                System.out.println("Employee Found: " + rs.getString("name"));
                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // GET BY ID
    public Employee getEmployeeById(int id) {

        Employee emp = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM employees WHERE emp_id=?"
             )) {

        	System.out.println("Searching Employee ID = " + id);
        	
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	
            	 System.out.println("Employee Found ID = " + rs.getInt("emp_id"));

                emp = new Employee();

                emp.setEmpId(rs.getInt("emp_id")); // ✅ FIXED
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department"));
                emp.setDesignation(rs.getString("designation"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setJoiningDate(rs.getDate("joining_date"));
            }

            else {
                System.out.println("NO EMPLOYEE FOUND FOR ID = " + id);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }

    // UPDATE
    public boolean updateEmployee(Employee emp) {

        boolean status = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "UPDATE employees SET name=?, email=?, department=?, designation=?, salary=?, joining_date=? WHERE emp_id=?"
             )) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setString(4, emp.getDesignation());
            ps.setDouble(5, emp.getSalary());
            ps.setDate(6, new java.sql.Date(emp.getJoiningDate().getTime()));

            ps.setInt(7, emp.getEmpId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // DELETE
    public boolean deleteEmployee(int id) {

        boolean status = false;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "DELETE FROM employees WHERE emp_id=?"
             )) {

            ps.setInt(1, id);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public int getEmployeeCount() {
        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                    "SELECT COUNT(*) FROM employees");

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                count = rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    
    public int getDepartmentCount() {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
            con.prepareStatement(
            "SELECT COUNT(DISTINCT department) FROM employees");

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                count = rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    
    public double getAverageSalary() {

        double avg = 0;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
            con.prepareStatement(
            "SELECT AVG(salary) FROM employees");

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                avg = rs.getDouble(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return avg;
    }
    
    public List<Employee> getRecentEmployees() {
        List<Employee> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM employees ORDER BY emp_id DESC LIMIT 5";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();

                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setDepartment(rs.getString("department"));
                e.setDesignation(rs.getString("designation"));
                e.setSalary(rs.getDouble("salary"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Employee> searchEmployees(String keyword) {

        List<Employee> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM employees WHERE name LIKE ? OR department LIKE ?")) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee e = new Employee();

                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setDepartment(rs.getString("department"));
                e.setDesignation(rs.getString("designation"));
                e.setSalary(rs.getDouble("salary"));
                e.setJoiningDate(rs.getDate("joining_date"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Employee> getSortedEmployees(String sort, String order, int start, int total) {

        List<Employee> list = new ArrayList<>();

        // safety (VERY IMPORTANT)
        if (sort == null || sort.isEmpty()) sort = "emp_id";
        if (order == null || order.isEmpty()) order = "asc";

        // allow only safe columns
        if (!sort.equals("name") && !sort.equals("department") && !sort.equals("salary")) {
            sort = "emp_id";
        }

        String sql = "SELECT * FROM employees ORDER BY " + sort + " " + order + " LIMIT ?,?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, total);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee e = new Employee();

                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setDepartment(rs.getString("department"));
                e.setDesignation(rs.getString("designation"));
                e.setSalary(rs.getDouble("salary"));
                e.setJoiningDate(rs.getDate("joining_date"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Employee> searchSortedEmployees(String keyword, String sort, String order) {

        List<Employee> list = new ArrayList<>();

        if (sort == null || sort.isEmpty()) sort = "emp_id";
        if (order == null || order.isEmpty()) order = "asc";

        if (!sort.equals("name") && !sort.equals("department") && !sort.equals("salary")) {
            sort = "emp_id";
        }

        String sql = "SELECT * FROM employees WHERE name LIKE ? OR department LIKE ? " +
                     "ORDER BY " + sort + " " + order;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee e = new Employee();

                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setDepartment(rs.getString("department"));
                e.setDesignation(rs.getString("designation"));
                e.setSalary(rs.getDouble("salary"));
                e.setJoiningDate(rs.getDate("joining_date"));

                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    

    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT emp_id, name, email, department, designation, salary FROM employees");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Employee emp = new Employee();

                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department"));
                emp.setDesignation(rs.getString("designation"));
                emp.setSalary(rs.getDouble("salary"));

                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}