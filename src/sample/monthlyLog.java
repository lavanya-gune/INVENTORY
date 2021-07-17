package sample;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class monthlyLog  implements Initializable {
    public DatePicker startdatelog;
    public DatePicker enddatelog;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TableView<modelTable> tableView = new TableView<>();
    @FXML
    public TableColumn<modelTable, Integer> col_prodCode = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_mfd = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_company = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_lastDate = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_stockLocation = new TableColumn<>();
    @FXML
    public TableColumn<modelTable, String> col_type = new TableColumn<>();


    ObservableList<modelTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_prodCode.setCellValueFactory(new PropertyValueFactory<>("Pid"));
        col_mfd.setCellValueFactory(new PropertyValueFactory<>("Pmfd"));
        col_company.setCellValueFactory(new PropertyValueFactory<>("Pcompany"));
        col_lastDate.setCellValueFactory(new PropertyValueFactory<>("Plastdate"));
        col_stockLocation.setCellValueFactory(new PropertyValueFactory<>("Pstock"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("Ptype"));
        tableView.setItems(observableList);

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String connectQuery = "SELECT * FROM `inventory_management`.`product_details`";
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput.next()) {
                observableList.add(new modelTable(
                        queryOutput.getInt("prod_code"),
                        queryOutput.getString("mfd"),
                        queryOutput.getString("last_date"),
                        queryOutput.getString("stock_location"),
                        queryOutput.getString("company"),
                        queryOutput.getString("comment")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void monthlyLog(ActionEvent actionEvent) {
            LocalDate startDate=startdatelog.getValue();
            LocalDate endDate=enddatelog.getValue();
            String connectQuery = "select * from product_details where mfd between '" + startDate + "' and '" + endDate + "'";
            System.out.println(connectQuery);
            tableView.getItems().clear();

            try {
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();

                Statement statement = connectDB.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectQuery);

                while(queryOutput.next()) {
                    observableList.add(new modelTable(
                            queryOutput.getInt("prod_code"),
                            queryOutput.getString("mfd"),
                            queryOutput.getString("last_date"),
                            queryOutput.getString("stock_location"),
                            queryOutput.getString("company"),
                            queryOutput.getString("comment")));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    public void goDelete(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        }

