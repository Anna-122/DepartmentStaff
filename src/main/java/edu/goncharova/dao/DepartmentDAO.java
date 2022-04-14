package edu.goncharova.dao;

import edu.goncharova.exception.CustomSqlException;
import edu.goncharova.model.Department;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static edu.goncharova.connection.MySqlConnection.connection;

public class DepartmentDAO implements DAO<Department, String> {

    @SneakyThrows
    @Override
    public Department save(Department department) {
        String sql = department.getId() != null ? "UPDATE departments SET department_name = ? WHERE department_id = ? "
                : "INSERT INTO departments (department_name) VALUES (?)";
        try (PreparedStatement ps = connection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, department.getDepartmentName());
            if (department.getId() != null) {
                ps.setInt(2, department.getId());
            }
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                department.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return department;
    }

    @Override
    public Department getByName(String s) {
        String sql = "SELECT * FROM departments WHERE department_name = ?";
        try (PreparedStatement preparedStatement = connection().prepareStatement(sql)) {
            preparedStatement.setString(1, s);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return toModel(resultSet);
                }
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return null;
    }


    public List<Department> getAll() {
        String sql = "SELECT * FROM departments";
        try (Statement preparedStatement = connection().createStatement()) {
            try (ResultSet resultSet = preparedStatement.executeQuery(sql)) {
                List<Department> list = new ArrayList<>();
                while (resultSet.next()) {
                  list.add(toModel(resultSet));
                }
                return list;
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
    }

    @Override
    public boolean delete(Department department) {
        String sql = "DELETE FROM departments WHERE department_id = ?";
        try (PreparedStatement statement = connection().prepareStatement(sql)) {
            statement.setInt(1, department.getId());
            return statement.executeUpdate() != 1;
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
    }

    @Override
    public Department getById(Integer id) {
        String sql = "SELECT * FROM departments WHERE department_id = ?";
        try (PreparedStatement preparedStatement = connection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return toModel(resultSet);
                }
            }
        } catch (SQLException sqlException) {
            throw new CustomSqlException(sqlException);
        }
        return null;
    }

    private Department toModel(ResultSet resultSet) throws SQLException {
        return new Department(
                resultSet.getInt("department_id"),
                resultSet.getString("department_name"));
    }
}
