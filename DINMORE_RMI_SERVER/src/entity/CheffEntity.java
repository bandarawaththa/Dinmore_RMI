package entity;

public class CheffEntity {
    private int id;
    private String UName;
    private String PW;
    private String Salt;

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    public CheffEntity(int id, String UName, String PW, String salt) {
        this.id = id;
        this.UName = UName;
        this.PW = PW;
        Salt = salt;
    }

    public CheffEntity(int id, String UName, String PW) {
        this.id = id;
        this.UName = UName;
        this.PW = PW;
    }

    public CheffEntity(String UName, String PW) {
        this.UName = UName;
        this.PW = PW;
    }

    public CheffEntity(String UName) {
        this.UName = UName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }
}