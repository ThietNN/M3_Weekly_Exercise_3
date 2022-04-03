package dao;

import model.Persona;
import model.PersonaUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/Persona5?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1225";

    private static final String INSERT_PERSONA = "INSERT INTO persona values(?,?);";
    private static final String SELECT_PERSONA_BY_ID = "SELECT * FROM persona WHERE id = ?";
    private static final String SELECT_ALL_PERSONA = "SELECT * FROM persona";
    private static final String DELETE_PERSONA = "DELETE FROM persona WHERE id = ?;";
    private static final String UPDATE_PERSONA = "UPDATE persona set name = ? WHERE id = ?;";

    public PersonaDAO() {
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

    public void insertPersona(Persona persona) throws SQLException{
        System.out.println(INSERT_PERSONA);
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERSONA)){
            preparedStatement.setInt(1,persona.getId());
            preparedStatement.setString(2,persona.getName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public Persona selectPersona(int id){
        Persona persona = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERSONA_BY_ID);){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                persona = new Persona(id,name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return persona;
    }

    public List<Persona> selectAllPersona(){
        List<Persona> personaList = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERSONA);){
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                personaList.add(new Persona(id,name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return personaList;
    }
    public boolean deletePersona(int id) throws SQLException{
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERSONA);){
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public boolean updatePersona(Persona persona) throws SQLException{
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERSONA)){
            preparedStatement.setInt(2,persona.getId());
            preparedStatement.setString(1,persona.getName());
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
