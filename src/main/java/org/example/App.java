package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        ConnectionUtil.getConnection();
    }
    public static void createTable(){
        String create = """
                create table users(
                id serial primary key ,
                name varchar(50))
                """;
        try (Statement statement = ConnectionUtil.getConnection().createStatement()){
            statement.executeUpdate(create);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveUsers(String name){
        String save = """
                insert into users(name)
                values (?);
                """;
        try (PreparedStatement preparedStatement =
                     ConnectionUtil.getConnection().prepareStatement(save)){
            preparedStatement.setString(1 , name);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteById(Long id){
        String delete = """
                delete from users where id = ?;
                """;
        try (PreparedStatement preparedStatement =
                ConnectionUtil.getConnection().prepareStatement(delete)){
            preparedStatement.setLong(1 , id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<User> getAllUsers(){
        String getAll = """
                select * from users;
                """;
        try (Statement statement =
                ConnectionUtil.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(getAll);
            System.out.println(resultSet);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
