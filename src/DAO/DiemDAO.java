package DAO;

import Model.Diem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiemDAO {

    public List<Diem> findAll(){
        List<Diem> diemList = new ArrayList<>();
        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select id, fullname, birthday,trungtam, chuyencan, baitap, giuaky, cuoiky, diemtb, xeploai from DiemHocVien";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Diem cp = new Diem(resultSet.getInt("id"),resultSet.getString("fullname") ,resultSet.getString("birthday"),
                        resultSet.getString("trungtam"), resultSet.getFloat("chuyencan"), resultSet.getFloat("baitap"),
                        resultSet.getFloat("giuaky"), resultSet.getFloat("cuoiky"), resultSet.getFloat("diemtb"),resultSet.getString("xeploai"));
                diemList.add(cp);
            }
            statement.close();
            connection.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return diemList;
    }

    public void edit(Diem diem) {
        try {
            Connection connection = DatabaseHepler.openConnection();

            String sql = "update DiemHocVien set fullName = ?, birthday =?,chuyencan =?,baitap=?,giuaky=?,cuoiky=?, diemtb = ?, xeploai = ? where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, diem.getFullname());
            statement.setString(2, diem.getBirthday());
            statement.setFloat(3, diem.getChuyencan());
            statement.setFloat(4, diem.getBaitap());
            statement.setFloat(5, diem.getGiuaky());
            statement.setFloat(6, diem.getCuoiky());
            statement.setFloat(7, diem.getDiemtb());
            statement.setString(8, diem.getXeploai());
            statement.setInt(9, diem.getId());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Diem> findByName(String fullName) {
        List<Diem> diemList = new ArrayList<>();
        try {
            Connection connection = DatabaseHepler.openConnection();

            String sql = "select id, fullname, birthday,trungtam, chuyencan, baitap, giuaky, cuoiky, diemtb, xeploai from DiemHocVien where fullname like ?";
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1, "%"+fullName+"%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Diem cp = new Diem(resultSet.getInt("id"),resultSet.getString("fullname") ,resultSet.getString("birthday"),
                        resultSet.getString("trungtam"), resultSet.getFloat("chuyencan"), resultSet.getFloat("baitap"),
                        resultSet.getFloat("giuaky"), resultSet.getFloat("cuoiky"), resultSet.getFloat("diemtb"),resultSet.getString("xeploai"));
                diemList.add(cp);
            }
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diemList;
    }

    public Diem getHocVienByID(int id) {

        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select id, fullname, birthday,trungtam, chuyencan, baitap, giuaky, cuoiky, diemtb, xeploai from DiemHocVien where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Diem diem = new Diem();
            while (rs.next()){
                diem.setId(rs.getInt(1));
                diem.setFullname(rs.getString(2));
                diem.setBirthday(rs.getString(3));
                diem.setTrungtam(rs.getString(4));
                diem.setChuyencan(rs.getFloat(5));
                diem.setBaitap(rs.getFloat(6));
                diem.setGiuaky(rs.getFloat(7));
                diem.setCuoiky(rs.getFloat(8));
                diem.setDiemtb(rs.getFloat(9));
                diem.setXeploai(rs.getString(10));
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
