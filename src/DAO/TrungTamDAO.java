package DAO;

import Model.Diem;
import Model.TrungTam;
import View.TrungTamJPanel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrungTamDAO {
    public List<TrungTam> findAll() throws Exception{
        Connection connection = DatabaseHepler.openConnection();
        List<TrungTam> list = new ArrayList<>();
        String sql = "select * from TrungTam";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            TrungTam trungTam = new TrungTam(rs.getInt("id"),rs.getString("name"));
            list.add(trungTam);
        }
        statement.close();
        connection.close();
        return list;
    }

    public void Add(TrungTam trungTam) throws Exception{
        Connection connection = DatabaseHepler.openConnection();
        String sql = "insert into TrungTam(id, name) values (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        TrungTamJPanel trungTamJPanel = new TrungTamJPanel();
        statement.setInt(1, trungTamJPanel.rowCount());
        statement.setString(2, trungTam.getName());

        statement.executeUpdate();
        statement.close();
        connection.close();

    }

    public void Edit(TrungTam trungTam) throws Exception{
        Connection connection = DatabaseHepler.openConnection();
        String sql= "update TrungTam set name = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, trungTam.getName());
        statement.setInt(2,trungTam.getId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public void delete(int id) {
        PreparedStatement statement = null;
        Statement statement1;
        Connection connection = null;
        try {
            connection = DatabaseHepler.openConnection();


            String sql = "delete from TrungTam where id = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();

            String sql3 = "select * from TrungTam";
            statement1 = connection.createStatement();

            ResultSet resultSet = statement1.executeQuery(sql3);
            int i = 1;
            while (resultSet.next()) {
                int ma = resultSet.getInt("id");
                String sql2 = "update TrungTam set id = ? where id = ?";
                statement = connection.prepareStatement(sql2);
                statement.setString(1, String.valueOf(i));
                statement.setString(2, String.valueOf(ma));
                i++;
                statement.executeUpdate();

            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public TrungTam getByID(int id) {

        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select id, name from TrungTam where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            TrungTam trungTam = new TrungTam();
            while (rs.next()){
                trungTam.setId(rs.getInt(1));
                trungTam.setName(rs.getString(2));

                return trungTam;
            }
            statement.close();
            connection.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<TrungTam> findByName(String name) throws Exception{
        List<TrungTam> list = new ArrayList<>();
        Connection connection = DatabaseHepler.openConnection();
        String sql = "select * from TrungTam where name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%"+name+"%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            TrungTam trungTam = new TrungTam(resultSet.getInt("id"), resultSet.getString("name"));
            list.add(trungTam);
        }
        statement.close();
        connection.close();
        return list;
    }
    public List<TrungTam> getData() {

        try {
            Connection cons = DatabaseHepler.openConnection();
            String sql = "SELECT trungtam, COUNT (id) as soluong FROM DiemHocVien group by trungtam";
            List<TrungTam> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrungTam trungTam = new TrungTam();
                trungTam.setName(rs.getString("trungtam"));
                trungTam.setSoluong(rs.getInt("soluong"));
                list.add(trungTam);
            }
            ps.close();
            cons.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
