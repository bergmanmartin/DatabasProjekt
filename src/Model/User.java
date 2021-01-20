package Model;

public class User {
    private String username, sex, adress, phone, birthday, regdate, fname, lname;
    private int mednumber;



    public User(String username, int mednumber, String sex, String adress, String phone, String birthday, String regdate, String fname, String lname) {
        this.username = username;
        this.mednumber = mednumber;
        this.sex = sex;
        this.adress = adress;
        this.phone = phone;
        this.birthday = birthday;
        this.regdate = regdate;
        this.fname = fname;
        this.lname = lname;

    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getMednumber() {
        return mednumber;
    }

    public void setMednumber(int mednumber) {
        this.mednumber = mednumber;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
