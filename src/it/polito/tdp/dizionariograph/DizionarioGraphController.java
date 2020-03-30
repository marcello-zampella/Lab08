package it.polito.tdp.dizionariograph;

import java.util.ArrayList;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

    @FXML
    private TextField TxtNumeroLettere;

    @FXML
    private TextField TxtParola;

    @FXML
    private TextArea textField;

    private Model model;
    
    private int numero;

	public void setModel(Model model) {
		this.model=model;
	}
	
    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	if(this.TxtNumeroLettere.getText().isEmpty()) {
    		this.textField.setText("DEVI INSERIRE UN NUMERO!");
    		return;
    	}
    	numero=Integer.parseInt(this.TxtNumeroLettere.getText());
    	if(numero<2) {
    		this.textField.setText("INSERISCI UN NUMERO SENSATO!");
    		return;
    	}
    	model.createGraph(numero);
    	this.textField.setText("GRAFO CREATO!!!\n");
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.textField.clear();
    	this.TxtNumeroLettere.clear();
    	this.TxtParola.clear();
    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	int numero=this.model.findMaxDegree();
    	if(numero==-1) {
    		this.textField.setText("DEVI CREARE IL GRAFO");
    		return;
    	}
    	this.textField.appendText(("il grado massimo e': "+numero));
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	this.textField.clear();
    	String parola=this.TxtParola.getText();
    	if(parola.isEmpty()) {
    		this.textField.setText("SEVI INSERIRE LA PAROLA!");
    		return;
    	}
    	if(parola.length()!=this.numero) {
    		this.textField.setText("LA PAROLA INSERITA DEVE ESSERE LUNGA "+numero);
    		return;
    	}
    	ArrayList<String> parole=model.displayNeighbours(parola);
    	if(parole==null) {
    		this.textField.setText("DEVI PRIMA CREARE IL GRAFO!");
    	return;
    }
    	for(String s: parole) {
    		this.textField.appendText(s+"\n");
    	}
    }

}
