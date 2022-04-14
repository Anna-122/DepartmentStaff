package edu.goncharova.dao;

import edu.goncharova.exception.CustomSqlException;
import edu.goncharova.model.Department;
import edu.goncharova.model.Employee;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import static edu.goncharova.connection.MySqlConnection.connection;

public class EmployeeDAO implements DAO<Employee, String> {

    @Override
    public Employee save(Employee employee) {
        String sql = employee.getId() != null
                ? "UPDATE employees SET employee_name = ?, employee_surname = ?, employee_date_birthday = ?, employee_phone_number = ?, employee_email = ?, department_id = ? WHERE employee_id = ?"
                : "INSERT INTO employees (employee_name, employee_surname,employee_date_birthday,employee_phone_number,employee_email, department_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmployeeSurname());
            ps.setDate(3, new java.sql.Date(employee.getEmployeeBirthDate().getTime()));
            ps.setString(4, employee.getEmployeePhoneNumber());
            ps.setString(5, employee.getEmployeeEmail());
            ps.setInt(6, employee.getDepartmentId());
            if (employee.getId() != null) {
                ps.setInt(7, employee.getId());
            }
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                employee.setId(generatedKeys.getInt(1));

            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return employee;
    }

    @Override
    public Employee getByName(String email) {
        String sql = "SELECT * FROM employees WHERE employee_email = ?";
        try (PreparedStatement preparedStatement = connection().prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return toModel(resultSet);
                }
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return null;
    }

    public Employee getByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM employees WHERE employee_phone_number = ?";
        try (PreparedStatement preparedStatement = connection().prepareStatement(sql)) {
            preparedStatement.setString(1, phoneNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return toModel(resultSet);
                }
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return null;
    }

    public List<Employee> read() {
        String sql = "SELECT * FROM employees";
        try (Statement preparedStatement = connection().createStatement()) {
            try (ResultSet resultSet = preparedStatement.executeQuery(sql)) {
                List<Employee> list = new ArrayList<>();
                while (resultSet.next()){
                    list.add(toModel(resultSet));
                }
                return list;
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
    }

    public List<Employee> read(Integer departmentId) {
        String sql = "SELECT * FROM employees WHERE department_id = ?";
        try (PreparedStatement preparedStatement = connection().prepareStatement(sql)) {
            preparedStatement.setInt(1, departmentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Employee> list = new ArrayList<>();
                while (resultSet.next()){
                    list.add(toModel(resultSet));
                }
                return list;
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
    }

    @Override
    public boolean delete(Employee employee) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (PreparedStatement statement = connection().prepareStatement(sql)) {
            statement.setInt(1, employee.getId());
            if (statement.executeUpdate() != 1) return false;
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return true;
    }

    @Override
    public Employee getById(Integer id) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return toModel(resultSet);
                }
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return null;
    }

    private Employee toModel(ResultSet resultSet) throws SQLException{
        return new Employee(resultSet.getInt("employee_id"),
                resultSet.getString("employee_name"),
                resultSet.getString("employee_surname"),
                resultSet.getString("employee_phone_number"),
                resultSet.getString("employee_email"),
                resultSet.getInt("department_id"),
                resultSet.getDate("employee_date_birthday"));
    }
}
