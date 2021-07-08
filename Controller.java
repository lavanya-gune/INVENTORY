package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

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
    PasswordField password;
    @FXML
    private ChoiceBox<String> Part_Type = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> partFor = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> Company = new ChoiceBox<>();

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

    public void AddNewItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddForm.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void AddExistingItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddExisting.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void AddItem(ActionEvent actionEvent) throws IOException {
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
    }

    public void selectCompany()
    {
        part = partFor.getValue();
        if (part.equals("Rail")) {
            Company.getItems().clear();
            Company.getItems().addAll(Rail);
        }
        else{
            if (part.equals("Injector")) {
                Company.getItems().clear();
                Company.getItems().addAll(Injector);
            }
            else{
                if (part.equals("PUMP")) {
                    Company.getItems().clear();
                    Company.getItems().addAll(Pump);
                }
                else{
                    Company.getItems().clear();
                    Company.getItems().addAll(MISC);
                }
            }

        }

    }
    @FXML
    private Button choose;
    public void singleFileChooser(ActionEvent event){
        FileChooser fc=new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files","*.jpg"));
        File f=fc.showOpenDialog(null);
        if(f!=null){
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

    public void deleteItem(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Delete_Existing.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteHistory(ActionEvent actionEvent) throws IOException {
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
}
