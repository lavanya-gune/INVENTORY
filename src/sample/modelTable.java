package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class modelTable {
    @FXML
    private SimpleIntegerProperty Pid;
    @FXML
    private SimpleStringProperty Pmfd;
    @FXML
    private SimpleStringProperty Pstock;
    @FXML
    private SimpleStringProperty Ptype;
    @FXML
    private SimpleStringProperty Pcompany;
    @FXML
    private SimpleStringProperty Plastdate;

    public int getPid() {
        return Pid.get();
    }

    public void setPid(int pid) {
        Pid = new SimpleIntegerProperty(pid);
    }

    public String getPmfd() {
        return Pmfd.get();
    }

    public void setPmfd(String pmfd) {
        Pmfd = new SimpleStringProperty(pmfd);
    }

    public String getPstock() {
        return Pstock.get();
    }

    public void setPstock(String pstock) {
        Pstock = new SimpleStringProperty(pstock);
    }

    public String getPtype() {
        return Ptype.get();
    }

    public void setPtype(String ptype) {
        Ptype = new SimpleStringProperty(ptype);
    }

    public String getPcompany() {
        return Pcompany.get();
    }

    public void setPcompany(String pcompany) {
        Pcompany = new SimpleStringProperty(pcompany);
    }

    public String getPlastdate() {
        return Plastdate.get();
    }

    public void setPlastdate(String plastdate) {
        Plastdate =new SimpleStringProperty(plastdate);
    }


    public modelTable(int pid, String pmfd, String pstock, String ptype, String pcompany, String plastdate) {
        this.Pid = new SimpleIntegerProperty(pid);
        this.Pmfd = new SimpleStringProperty(pmfd);
        this.Pstock = new SimpleStringProperty(pstock);
        this.Ptype = new SimpleStringProperty(ptype);
        this.Pcompany = new SimpleStringProperty(pcompany);
        this.Plastdate = new SimpleStringProperty(plastdate);
    }
}