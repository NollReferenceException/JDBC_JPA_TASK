package overridetech.jdbc.jpa.dao;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String tableId = "users.users";
    private final Connection postgresConnection = Util.getInstance().getPostgresConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            String sql =  "create table if not exists " + tableId +
                    " (id BIGSERIAL PRIMARY KEY, name VARCHAR(255), lastname VARCHAR(255), age smallint)";

            PreparedStatement stmt = postgresConnection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            String sql = "drop table if exists " + tableId;

            PreparedStatement stmt = postgresConnection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sql = "insert into " + tableId + " (name, lastname, age) values (?, ?, ?) ";

            PreparedStatement stmt = postgresConnection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            stmt.close();

            System.out.printf("User с именем – %s добавлен в базу данных %n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            CallableStatement stmt = postgresConnection.prepareCall("call users.RemoveUserById(?)");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            String sql = "select * from " + tableId;
            PreparedStatement stmt = postgresConnection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                users.add(new User(result.getString(2), result.getString(3), result.getByte(4)));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            String sql = "delete from " + tableId;

            PreparedStatement stmt = postgresConnection.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
