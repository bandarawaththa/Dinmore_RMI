package dtos;

import java.io.Serializable;

public class TelOperatorDTO implements Serializable {
    private String id;
    private String UName;
    private String PW;
    private String Salt;

    public TelOperatorDTO(String id, String UName, String PW, String salt) {
        this.id = id;
        this.UName = UName;
        this.PW = PW;
        Salt = salt;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    public TelOperatorDTO() {}

    public TelOperatorDTO(String UName) {
        this.UName = UName;
    }

    public TelOperatorDTO(String UName, String PW) {
        this.UName = UName;
        this.PW = PW;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
