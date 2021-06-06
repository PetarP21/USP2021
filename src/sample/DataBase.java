package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static List<Movie> movies = new ArrayList<>();
    public static void add(Movie movie){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesdb","root","");
            String query = " INSERT INTO movies (name, actors, genre,year)" + " values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString (1, movie.getName());
            ps.setString (2, movie.getActors());
            ps.setString (3, movie.getGenre());
            ps.setInt    (4, movie.getYear());

            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> getMovies(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesdb","root","");
            PreparedStatement ps = null;
            ResultSet rs = null;
            String query = "SELECT * FROM movies";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                movies.add(new Movie(rs.getString("name"),
                        rs.getString("actors"),
                        rs.getString("genre"),
                        Integer.parseInt(rs.getString("year"))));
            }
            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return movies;
    }
}
