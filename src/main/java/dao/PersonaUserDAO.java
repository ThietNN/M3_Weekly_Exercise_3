package dao;

import model.PersonaUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaUserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/Persona5?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1225";

    private static final String INSERT_PERSONA_USER = "INSERT INTO persona_user values(?,?,?,?)";
    private static final String SELECT_PERSONA_USER_BY_ID = "SELECT * FROM persona_user WHERE id = ?";
    private static final String SELECT_ALL_PERSONA_USER = "SELECT * FROM persona_user";
    private static final String DELETE_PERSONA_USER = "DELETE FROM persona_user WHERE id = ?;";
    private static final String UPDATE_PERSONA_USER = "UPDATE persona_user set name = ?, gender = ?, persona_ID = ? WHERE id = ?;";

    public PersonaUserDAO() {
    }

    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void insertPersonaUser(PersonaUser personaUser) throws SQLException{
        System.out.println(INSERT_PERSONA_USER);
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_PERSONA_USER)){
            preparedStatement.setInt(1,personaUser.getId());
            preparedStatement.setString(2,personaUser.getName());
            preparedStatement.setString(3,personaUser.getGender());
            preparedStatement.setInt(4,personaUser.getPersonaID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
    }
    public PersonaUser selectPersonaUser(int id){
        PersonaUser personaUser = null;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSONA_USER_BY_ID);){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                int personaId = resultSet.getInt("personaId");
                personaUser = new PersonaUser(id,name,gender,personaId);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return personaUser;
    }
    public List<PersonaUser> selectAllPersonaUser(){
        List<PersonaUser> personaUserList = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERSONA_USER);){
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                int personaId = resultSet.getInt("personaId");
                personaUserList.add(new PersonaUser(id,name,gender,personaId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return personaUserList;
    }

    public boolean deletePersonaUser(int id) throws SQLException{
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERSONA_USER);){
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updatePersonaUser (PersonaUser personaUser) throws SQLException{
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSONA_USER);){
            preparedStatement.setInt(4,personaUser.getId());
            preparedStatement.setString(1, personaUser.getName());
            preparedStatement.setString(2,personaUser.getGender());
            preparedStatement.setInt(3,personaUser.getPersonaID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
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
}
