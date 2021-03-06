package sample;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SearchTableController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String connectQuery;

    @FXML
    public TextField filterBox;
    @FXML
    public TableView<modelTable> tableView=new TableView<>();
    @FXML
    public TableColumn<modelTable,String> col_partNo=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_refPartNo=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_addOn=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,Integer> col_quantity=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_partFor=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_company=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_inventoryDate=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_sourceOfPurchase=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_stockLocation=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_setOf=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_prefix=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,String> col_comment=new TableColumn<>();
    @FXML
    public TableColumn<modelTable,ImageView> col_img=new TableColumn<>();

    @FXML
    private TextField enteredProdCode;

    ObservableList<modelTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery = "SELECT * FROM `inventory_management`.`inward_item`";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while(queryOutput.next()) {
                observableList.add(new modelTable(
                        queryOutput.getString("part_no"),
                        queryOutput.getString("ref_part_no"),
                        queryOutput.getString("stock_loc"),
                        queryOutput.getString("add_on"),
                        queryOutput.getInt("quantity"),
                        queryOutput.getString("part_for"),
                        queryOutput.getString("company"),
                        queryOutput.getString("inventory_date"),
                        queryOutput.getString("source_of_p"),
                        queryOutput.getString("stock_loc"),
                        queryOutput.getString("setof"),
                        queryOutput.getString("prefix"),
                        queryOutput.getString("comment")));
            }


            col_partNo.setCellValueFactory(new PropertyValueFactory<>("P_partNumber"));
            col_refPartNo.setCellValueFactory(new PropertyValueFactory<>("P_refPartNumber"));

            col_img.setCellValueFactory(new PropertyValueFactory<>("images"));

            col_addOn.setCellValueFactory(new PropertyValueFactory<>("P_addOn"));
            col_quantity.setCellValueFactory(new PropertyValueFactory<>("P_quantity"));
            col_partFor.setCellValueFactory(new PropertyValueFactory<>("P_partFor"));
            col_company.setCellValueFactory(new PropertyValueFactory<>("P_company"));
            col_inventoryDate.setCellValueFactory(new PropertyValueFactory<>("P_invDate"));
            col_sourceOfPurchase.setCellValueFactory(new PropertyValueFactory<>("P_sourceOfPurchase"));
            col_stockLocation.setCellValueFactory(new PropertyValueFactory<>("P_stockLocation"));
            col_setOf.setCellValueFactory(new PropertyValueFactory<>("P_setOf"));
            col_prefix.setCellValueFactory(new PropertyValueFactory<>("P_prefix"));
            col_comment.setCellValueFactory(new PropertyValueFactory<>("P_comment"));
            tableView.setItems(observableList);

            FilteredList<modelTable> filteredData= new FilteredList<>(observableList, b->true);
            filterBox.textProperty().addListener((observableValue, s, t1) -> {
                filteredData.setPredicate(modelTable -> {
                    if(t1==null || t1.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=t1.toLowerCase();

                    if(modelTable.getP_partNumber().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    if(String.valueOf(modelTable.getP_refPartNumber()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    if(String.valueOf(modelTable.getP_quantity()).toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                        return true;
                    }
                    if(modelTable.getP_invDate().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                        return true;
                    }
                    if(modelTable.getP_partFor().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<modelTable> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedData);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goSample(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void retrieveSearchedItems(ActionEvent event) {
    }
}

