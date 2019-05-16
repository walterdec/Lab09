/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import it.polito.tdp.borders.db.BordersDAO;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model model;
	BordersDAO dao = new BordersDAO();

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader
	
    @FXML
    private ComboBox<Country> comboBox;

    @FXML
    private Button btnCercaStatiConfinanti;

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		txtResult.clear();
		try{
			int anno = Integer.parseInt(txtAnno.getText());
			if(anno<1816 || anno>2016) {
				txtResult.setText("Inseire anno nel range 1816-2016");
				return;
			}
			model.calcolaConfini(anno);
		} catch(NumberFormatException e) {
			txtResult.setText("Inserire anno valido");
			return;
		}
		for(Country country : model.getVertici()) {
			txtResult.appendText("Country: "+country.getStateNme()+"\nBorders: ");
			for(Country neighbor : model.getVicini(country)) {
				txtResult.appendText(neighbor.getStateNme()+", ");
			}
			txtResult.appendText("\nNumber of borders: "+model.getGradoVertice(country)+"\n\n");
		}
		
	}
	
    @FXML
    void doCercaStatiConfinanti(ActionEvent event) {

    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		Map<Integer, Country> paesi = dao.loadAllCountries();
		ObservableList<Country> countriesList = FXCollections.observableArrayList();
		for(Entry<Integer, Country> c : paesi.entrySet()) {
			countriesList.add(c.getValue());
		}
		Collections.sort(countriesList);
		comboBox.setItems(countriesList);
		comboBox.setValue(countriesList.get(0));
	}
	
	public void setModel(Model model) {
		this.model=model;
	}
}
