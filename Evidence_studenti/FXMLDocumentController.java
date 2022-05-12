/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broucek_05_02_studenti;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author Admin
 */
public class FXMLDocumentController implements Initializable {

    ObservableList<Student> evidenceStudentu;

    @FXML
    private TableView<Student> tableEvidenceStudentu;
    @FXML
    private TableColumn<Student, Integer> tableColum_Id;
    @FXML
    private TableColumn<Student, String> tableColum_Jmeno;
    @FXML
    private TableColumn<Student, String> tableColum_Prijmeni;
    @FXML
    private TableColumn<Student, Integer> tableColum_Rocnik;
    @FXML
    private Button btnPridej;
    @FXML
    private PieChart pieChart_EvidenceStudentu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evidenceStudentu = FXCollections.observableArrayList();
        evidenceStudentu.addAll(
                new Student(1, "Karel", "Novák", 1),
                new Student(2, "Milan", "Pes", 3),
                new Student(3, "Pavel", "Kočka", 1),
                new Student(4, "Jabuk", "Dvorak", 2)
        );

        setPieChartData();

        tableEvidenceStudentu.setItems(evidenceStudentu);
        tableColum_Id.setCellValueFactory(new PropertyValueFactory("id"));
        tableColum_Jmeno.setCellValueFactory(new PropertyValueFactory("jmeno"));
        tableColum_Prijmeni.setCellValueFactory(new PropertyValueFactory("prijmeni"));
        tableColum_Rocnik.setCellValueFactory(new PropertyValueFactory("rocnik"));

        tableColum_Id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableColum_Jmeno.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColum_Prijmeni.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColum_Id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }

    private void setPieChartData() {
        ObservableList<Data> data = FXCollections.observableArrayList(
                new PieChart.Data("První ročník", countOfRocnik(1)),
                new PieChart.Data("Druhý ročník", countOfRocnik(2)),
                new PieChart.Data("Třetí ročník", countOfRocnik(3)),
                new PieChart.Data("Čtvrt ročník", countOfRocnik(4)),
                new PieChart.Data("Pátý ročník", countOfRocnik(5))
        );

        pieChart_EvidenceStudentu.setData(data);
    }

    private int countOfRocnik(int rocnik) {
        int count = 0;
        for (Student student : evidenceStudentu) {
            if (student.getRocnik() == rocnik) {
                count++;
            }
        }
        return count;
    }

    @FXML
    private void HandleBtnOnActionPridej(ActionEvent event) {
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("Nový student");
        dialog.setHeaderText("Doplntě udaje o studentovy");

        Label label_id = new Label("ID: ");
        Label label_jmeno = new Label("Jmeno: ");
        Label label_prijmeni = new Label("Prijmeni: ");
        Label label_rocnik = new Label("Rocnik: ");

        TextField text_id = new TextField();
        TextField text_jmeno = new TextField();
        TextField text_prijmeni = new TextField();
        TextField text_rocnik = new TextField();
        
        GridPane novyStudent_grid = new GridPane();
        novyStudent_grid.add(label_id, 1,1);
        novyStudent_grid.add(text_id, 2,1);
        
        novyStudent_grid.add(label_jmeno, 1,2);
        novyStudent_grid.add(text_jmeno, 2,2);
        
        novyStudent_grid.add(label_prijmeni, 1,3);
        novyStudent_grid.add(text_prijmeni, 2,3);
        
        novyStudent_grid.add(label_rocnik, 1,4);
        novyStudent_grid.add(text_rocnik, 2,4);
        
        dialog.getDialogPane().setContent(novyStudent_grid);
        
        ButtonType btnPridej = new ButtonType("Pridej", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(btnPridej);
        
        dialog.showAndWait();
        
        int Id = Integer.parseInt(text_id.getText());
        String jmeno = text_jmeno.getText();
        String prijmeni = text_prijmeni.getText();
        int rocnik = Integer.parseInt(text_rocnik.getText());
        
        if(rocnik <= 5 && rocnik >= 1){
            evidenceStudentu.add(new Student(Id, jmeno, prijmeni, rocnik));
        }
        setPieChartData();
        
    }

    @FXML
    private void tableEditColumId(TableColumn.CellEditEvent<Student, Integer> event) {
        int indexId = event.getTablePosition().getRow();
        Student editId = tableEvidenceStudentu.getItems().get(indexId);
        int noveId = event.getNewValue();
        editId.setId(noveId);
        setPieChartData();
    }

    @FXML
    private void tableEditColumJmeno(TableColumn.CellEditEvent<Student, String> event) {
        int indexJmeno = event.getTablePosition().getRow();
        Student editJmeno = tableEvidenceStudentu.getItems().get(indexJmeno);
        String noveJmeno = event.getNewValue();
        editJmeno.setJmeno(noveJmeno);
        setPieChartData();
    }

    @FXML
    private void tableEditColumPrijmeni(TableColumn.CellEditEvent<Student, String> event) {
        int indexPrijmeni = event.getTablePosition().getRow();
        Student editPrijmeni = tableEvidenceStudentu.getItems().get(indexPrijmeni);
        String novePrijmeni = event.getNewValue();
        editPrijmeni.setPrijmeni(novePrijmeni);
        setPieChartData();
    }

    @FXML
    private void tableEditColumRocnik(TableColumn.CellEditEvent<Student, Integer> event) {
        int indexRocnik = event.getTablePosition().getRow();
        Student editRocnik = tableEvidenceStudentu.getItems().get(indexRocnik);
        int novyRocnik = event.getNewValue();
        editRocnik.setId(novyRocnik);
        setPieChartData();
    }

}
