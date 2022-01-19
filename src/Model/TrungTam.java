package Model;

public class TrungTam {
    private int id;
    private String name;
    private int soluong;

    public TrungTam(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TrungTam() {
    }
    public TrungTam(String name, int soluong){
        this.name = name;
        this.soluong = soluong;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
