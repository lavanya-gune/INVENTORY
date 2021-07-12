package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button submit;
    @FXML
    Button back;
    @FXML
    Label wrong;
    @FXML
    Button HOME;
    @FXML
    private TextField Productcode;
    @FXML
    PasswordField password;
    @FXML
    private ChoiceBox<String> Part_Type = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> partFor = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> Company = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> PS = new ChoiceBox<>();
    @FXML
    private GridPane details;
    @FXML
    private AnchorPane myAnchorPane;

    @FXML
    private DatePicker mfd;
    @FXML
    private DatePicker lastDate;
    @FXML
    private TextArea techDetails;
    @FXML
    private TextArea comment;


    public String part;

    private final String[] partfor = {"Injector", "Rail", "Pump", "MISCELLANEOUS"};
    private final String[] partType = {"Full Kit", "Nozzle", "Valve", "Both Nozzle and Valve", "Solenoid"};
    private final String[] Pump = {"ROTARY", "LINE PUMP", "CP", "VP", "MISCELLANEOUS"};
    private final String[] Rail = {"BOSCH", "VOLVO", "DELPHI", "MISCELLANEOUS"};
    private final String[] Injector = {"BOSCH", "DELPHI", "DENSO", "CONTI", "PEIZO", "UNIT INJECTOR", "VOLVO", "CAT", "MISCELLANEOUS"};
    private final String[] MISC = {"BOSCH", "DELPHI", "DENSO", "CONTI", "PEIZO", "UNIT INJECTOR", "VOLVO", "CAT", "ROTARY", "LINE PUMP", "CP", "VP", "MISCELLANEOUS"};

    public void goLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void checkLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        if (password.getText().equals("1234")) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            wrong.setVisible(true);
        }
    }

    public void goSample(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddNewItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddForm.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddExistingItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddExisting.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goAddItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goThird(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partFor.getItems().addAll(partfor);
        Part_Type.getItems().addAll(partType);
        PS.getItems().addAll("Part", "System");
    }

    public void selectCompany() {
        part = partFor.getValue();
        if (part.equals("Rail")) {
            Company.getItems().clear();
            Company.getItems().addAll(Rail);
        } else {
            if (part.equals("Injector")) {
                Company.getItems().clear();
                Company.getItems().addAll(Injector);
            } else {
                if (part.equals("PUMP")) {
                    Company.getItems().clear();
                    Company.getItems().addAll(Pump);
                } else {
                    Company.getItems().clear();
                    Company.getItems().addAll(MISC);
                }
            }

        }

    }

    @FXML
    private Button choose;

    public void singleFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg"));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            choose.setText(f.getAbsolutePath());
        }
    }

    public void goDelete(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void godeleteItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Delete_Existing.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void godeleteHistory(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteItemHistory.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goSearch(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void GenerateBarCode(ActionEvent actionEvent) {

        String ProdCode = Productcode.getText();
        String PartFor = partFor.getValue().toString();
        String TypeOfPart = Part_Type.getValue().toString();
        String company = Company.getValue().toString();
        String ManufactureDate = mfd.getValue().toString();
        String StockLocation = "pta nhi";
        String LastDate = lastDate.getValue().toString();
        String TechDetails = techDetails.getText();
        String Comment = comment.getText();

        try {

            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);

            alert.getDialogPane().setContentText("Do you want to confirm?");

            alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                addData(ProdCode, PartFor, TypeOfPart, company, ManufactureDate, StockLocation, LastDate, TechDetails, Comment);

                Code128Bean code128 = new Code128Bean();
                String image_name = Productcode.getText() + ".png";
                String myString = Productcode.getText();
                code128.setHeight(15f);
                code128.setModuleWidth(0.3);
                code128.setQuietZone(10);
                code128.doQuietZone(true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
                code128.generateBarcode(canvas, myString);
                canvas.finish();
                //write to png file
                FileOutputStream fos = new FileOutputStream("C:\\Users\\4manm\\IdeaProjects\\GG\\INVENTORY\\Barcode\\Barcode" + image_name);
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();


                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void goDELETE_PUBLIC(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DELETE_PUBLIC.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void itemDetails() {
        details.setVisible(true);
    }


    public void AddExisting(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Do you want to confirm?");

        alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItem.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void DeleteExisting(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Do you want to confirm?");

        alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


            public void DeleteHistory(ActionEvent actionEvent) throws IOException {
                Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                Alert alert = new Alert(type, "");

                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);

                alert.getDialogPane().setContentText("Do you want to confirm?");

                alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete.fxml")));
                    stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }


    }

    public void DeleteLog(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) myAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Do you want to confirm?");

        alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void godeleteLog(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteLog.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addData(String ProdCode,String PartFor,String TypeOfPart,String company,String ManufactureDate,String StockLocation,String LastDate,String TechDetails,String Comment){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
//        Stage stage=(Stage) myAnchorPane.getScene().getWindow();

//        Alert.AlertType type=Alert.AlertType.CONFIRMATION;
//        Alert alert=new Alert(type,"");
//
//        alert.initModality(Modality.APPLICATION_MODAL);
//        alert.initOwner(stage);
//
//        alert.getDialogPane().setContentText("Do you want to confirm?");
//
//        alert.getDialogPane().setHeaderText("You have given the correct information about the products.");
//        Optional<ButtonType> result= alert.showAndWait();
//        if(result.get()==ButtonType.OK)
//        {
//            System.out.println("Got it");
//        }
//        else if (result.get()==ButtonType.CANCEL){
//            System.out.println("Cancelled");
//        }



        //    ProductCode PartType partFor Company mfd lastDate techDetails comment
        String connectQuery = "INSERT INTO `inventory_management`.`product_details` VALUES ("+ProdCode+",'"+PartFor+"','"+TypeOfPart+"','"+company+"','"+ManufactureDate+"','"+LastDate+"','"+StockLocation+"','"+TechDetails+"','"+Comment+"'"+")";
//        System.out.print(connectQuery);

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
//            ResultSet queryOutput = statement.executeUpdate(connectQuery);

//            while(queryOutput.next()){
//
//                showDetails.setText(queryOutput.getString("prod_code"));
////                System.out.println(queryOutput.getString("prod_code"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
