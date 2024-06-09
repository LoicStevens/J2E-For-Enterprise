package auth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import auth.model.User;

public class UserDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/jobos1?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_USER_SQL = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id, username, email, password, role FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET username = ?, email = ?, password = ?, role = ? WHERE id = ?;";


    public UserDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to create the database connection.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("MySQL JDBC Driver not found.");
        }
        return connection;
    }

 // Insert user
    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            if (connection != null) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getRole());
      
                preparedStatement.executeUpdate();
            } else {
                System.err.println("Connection is null, cannot insert user.");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


    // Select user by id
    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            if (connection != null) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    user = new User(id, username, email, password, role);
                }
            } else {
                System.err.println("Connection is null, cannot select user.");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    // Select all users
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            if (connection != null) {
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    users.add(new User(id, username, email, password, role));
                }
            } else {
                System.err.println("Connection is null, cannot select all users.");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    // Delete user
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            if (connection != null) {
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
            } else {
                System.err.println("Connection is null, cannot delete user.");
            }
        }
        return rowDeleted;
    }

    // Update user
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            if (connection != null) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getRole());
                statement.setInt(5, user.getId());
                rowUpdated = statement.executeUpdate() > 0;
            } else {
                System.err.println("Connection is null, cannot update user.");
            }
        }
        return rowUpdated;
    }
    public User checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;

        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }


    // Handle SQL exceptions
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

 // Select user by email
    public User selectUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                user = new User(id, username, email, password, role);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

}

