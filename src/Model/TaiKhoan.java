package Model;

public class TaiKhoan {

    private int id;
    private String user;
    private String password;
    private boolean tinh_trang;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getUser () {
        return user;
    }

    public void setUser (String user) {
        this.user = user;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public boolean isTinh_trang () {
        return tinh_trang;
    }

    public void setTinh_trang (boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
}