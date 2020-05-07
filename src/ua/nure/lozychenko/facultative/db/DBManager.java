package ua.nure.lozychenko.facultative.db;

import ua.nure.lozychenko.facultative.DBException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBManager {
    private static DBManager instance;

    private String connectionUrl;

    private DBManager() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("app.properties"));
            connectionUrl = properties.get("connection.url").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException, NamingException {
        InitialContext initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/facultativeDB");
        return ds.getConnection();
    }

    public static ResultSet query(AutoCloseable[] toClose, String query) throws DBException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.createStatement();

            toClose = new AutoCloseable[]{statement, connection};

            return statement.executeQuery(query);
        } catch (SQLException | NamingException e) {
            throw new DBException(e);
        }
    }

    public static ResultSet query(AutoCloseable[] toClose, String query, Object[] params) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    if (params[i] instanceof Long) {
                        preparedStatement.setLong(i + 1, (Long) params[i]);
                    } else if (params[i] instanceof String) {
                        preparedStatement.setString(i + 1, (String) params[i]);
                    } else if (params[i] instanceof Boolean) {
                        preparedStatement.setBoolean(i + 1, (Boolean) params[i]);
                    } else if (params[i] instanceof long[]) {
                        preparedStatement.setArray(i + 1, (Array) params[i]);
                    }
                }
            }

            toClose = new AutoCloseable[]{preparedStatement, connection};

            return preparedStatement.executeQuery();
        } catch (SQLException | NamingException e) {
            throw new DBException(e);
        }
    }

    public void execute(String query) throws DBException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.createStatement();

            statement.execute(query);
        } catch (SQLException | NamingException e) {
            throw new DBException(e);
        } finally {
            close(statement, connection);
        }
    }

    public static void execute(String query, Object[] params) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getConnection();
            preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Long) {
                    preparedStatement.setLong(i + 1, (Long) params[i]);
                } else if (params[i] instanceof String) {
                    preparedStatement.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof Boolean) {
                    preparedStatement.setBoolean(i + 1, (Boolean) params[i]);
                } else if (params[i] instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) params[i]);
                }
            }

            preparedStatement.execute();
        } catch (SQLException | NamingException e) {
            throw new DBException(e);
        } finally {
            close(preparedStatement, connection);
        }
    }


    public static void close(AutoCloseable... connections) {
        if (connections != null) {
            for (AutoCloseable connection : connections) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
