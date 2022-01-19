package Model;

public class Diem {
    private int id;
    private String fullname;
    private String birthday;
    private String trungtam;
    private float chuyencan, baitap, giuaky, cuoiky, diemtb;
    private String xeploai, sdt, address;

    public Diem(int id, String fullname, String birthday, String trungtam, float chuyencan, float baitap, float giuaky, float cuoiky, float diemtb, String xeploai, String sdt, String address) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.trungtam = trungtam;
        this.chuyencan = chuyencan;
        this.baitap = baitap;
        this.giuaky = giuaky;
        this.cuoiky = cuoiky;
        this.diemtb = diemtb;
        this.xeploai = xeploai;
        this.sdt = sdt;
        this.address = address;
    }

    public Diem(int id, String fullname, String birthday, String trungtam, float chuyencan, float baitap, float giuaky, float cuoiky, float diemtb, String xeploai) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.trungtam = trungtam;
        this.chuyencan = chuyencan;
        this.baitap = baitap;
        this.giuaky = giuaky;
        this.cuoiky = cuoiky;
        this.diemtb = diemtb;
        this.xeploai = xeploai;
    }

    public Diem(int id, String fullname, String birthday, String trungtam, String sdt, String address) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.trungtam = trungtam;
        this.sdt = sdt;
        this.address = address;
    }

    public Diem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTrungtam() {
        return trungtam;
    }

    public void setTrungtam(String trungtam) {
        this.trungtam = trungtam;
    }

    public float getChuyencan() {
        return chuyencan;
    }

    public void setChuyencan(float chuyencan) {
        this.chuyencan = chuyencan;
    }

    public float getBaitap() {
        return baitap;
    }

    public void setBaitap(float baitap) {
        this.baitap = baitap;
    }

    public float getGiuaky() {
        return giuaky;
    }

    public void setGiuaky(float giuaky) {
        this.giuaky = giuaky;
    }

    public float getCuoiky() {
        return cuoiky;
    }

    public void setCuoiky(float cuoiky) {
        this.cuoiky = cuoiky;
    }

    public float getDiemtb() {
        float aver = chuyencan/10+baitap/10+3*giuaky/10+cuoiky/2;
        return (float) (Math.round(aver*100.0)/100.0);
    }

    public void setDiemtb(float diemtb) {
        this.diemtb = diemtb;
    }

    public String getXeploai() {
        if(getDiemtb()>=8.5)
            return "A";
        if (getDiemtb()>=7&&getDiemtb()<8.5)
            return "B";
        if(getDiemtb()>=5.5&& getDiemtb()<7)
            return "C";
        if(getDiemtb()>=4 && getDiemtb() <5.5)
            return "D";
        if (getDiemtb()<4)
            return "F";
        return null;
    }

    public void setXeploai(String xeploai) {
        this.xeploai = xeploai;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
