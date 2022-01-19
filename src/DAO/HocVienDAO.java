package DAO;

import Model.Diem;
import Model.TrungTam;
import View.HocVienJPanel;
import View.TrungTamJPanel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HocVienDAO {
    public List<Diem> loadHocVien() {
        List<Diem> list = new ArrayList<>();
        try {

            Connection connection = DatabaseHepler.openConnection();
            String sql = "select id, fullname, birthday, trungtam, sdt, address from DiemHocVien";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Diem diem = new Diem(resultSet.getInt("id"), resultSet.getString("fullname"),
                        resultSet.getString("birthday"), resultSet.getString("trungtam"),
                        resultSet.getString("sdt"), resultSet.getString("address"));
                list.add(diem);
            }
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void Add(Diem diem) {
        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "insert into DiemHocVien(id, fullname, birthday, trungtam, sdt, address) values (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            HocVienJPanel hocVienJPanel = new HocVienJPanel();
            statement.setInt(1, hocVienJPanel.rowCount());
            statement.setString(2, diem.getFullname());
            statement.setString(3, diem.getBirthday());
            statement.setString(4, diem.getTrungtam());
            statement.setString(5, diem.getSdt());
            statement.setString(6, diem.getAddress());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Edit(Diem diem) {
        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "update DiemHocVien set fullname = ?, birthday = ?, trungtam =?, sdt =?, address = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, diem.getFullname());
            statement.setString(2, diem.getBirthday());
            statement.setString(3, diem.getTrungtam());
            statement.setString(4, diem.getSdt());
            statement.setString(5, diem.getAddress());
            statement.setInt(6, diem.getId());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        PreparedStatement statement = null;
        Statement statement1;
        Connection connection = null;
        try {
            connection = DatabaseHepler.openConnection();


            String sql = "delete from DiemHocVien where id = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();

            String sql3 = "select * from DiemHocVien";
            statement1 = connection.createStatement();

            ResultSet resultSet = statement1.executeQuery(sql3);
            int i = 1;
            while (resultSet.next()) {
                int ma = resultSet.getInt("id");
                String sql2 = "update DiemHocVien set id = ? where id = ?";
                statement = connection.prepareStatement(sql2);
                statement.setString(1, String.valueOf(i));
                statement.setString(2, String.valueOf(ma));
                i++;
                statement.executeUpdate();

            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Diem> findByName(String name) {
        List<Diem> list = new ArrayList<>();
        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select id, fullname, birthday, trungtam, sdt, address from DiemHocVien where fullname like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%"+name+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Diem diem = new Diem(resultSet.getInt("id"), resultSet.getString("fullname"),
                        resultSet.getString("birthday"), resultSet.getString("trungtam"),
                        resultSet.getString("sdt"), resultSet.getString("address"));
                list.add(diem);
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Diem getByID(int id) {

        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select id, fullname, birthday, trungtam, sdt, address from DiemHocVien where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Diem diem = new Diem();
            while (rs.next()){
                diem.setId(rs.getInt(1));
                diem.setFullname(rs.getString(2));
                diem.setBirthday(rs.getString(3));
                diem.setTrungtam(rs.getString(4));
                diem.setSdt(rs.getString(5));
                diem.setAddress(rs.getString(6));

                return diem;
            }
            statement.close();
            connection.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
