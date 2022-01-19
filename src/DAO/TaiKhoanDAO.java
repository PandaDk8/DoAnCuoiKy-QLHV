package DAO;

import Model.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoanDAO {
    public TaiKhoan login(String tenDangNhap, String matKhau) {
        try {
            Connection cons = DatabaseHepler.openConnection ();
            String sql = "SELECT * FROM TaiKhoan WHERE taikhoan LIKE ?  AND matkhau LIKE ?";
            TaiKhoan taiKhoan = null;
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                taiKhoan = new TaiKhoan();
                taiKhoan.setId (rs.getInt("id"));
                taiKhoan.setUser(rs.getString("taikhoan"));
                taiKhoan.setPassword (rs.getString("matkhau"));
            }
            ps.close();
            cons.close();
            return taiKhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
