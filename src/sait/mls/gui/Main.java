package sait.mls.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sait.mls.exceptions.clientale.InvalidClientTypeException;
import sait.mls.exceptions.clientale.InvalidPhoneNumberException;
import sait.mls.exceptions.clientale.InvalidPostalCodeException;
import sait.mls.exceptions.property.InvalidLegalDescriptionException;
import sait.mls.exceptions.property.InvalidNumberOfBathroomsException;
import sait.mls.persistence.clientale.ClientBroker;
import sait.mls.persistence.property.CommercialPropertyBroker;
import sait.mls.persistence.property.ResidentialPropertyBroker;
import sait.mls.problemdomain.clientale.Client;
import sait.mls.problemdomain.property.CommercialProperty;
import sait.mls.problemdomain.property.ResidentialProperty;

/**
 * Class description: The totally not rushed SAIT MLS Manager GUI by Christian
 * @author Christian Garrovillo
 *
 */
public class Main extends Application
{
	Stage window;
	BorderPane layout;
	Scene scene;
	
	/*
	 * Controllers
	 */
	int cSelectedi = 0;
	/*
	 * Client
	 */
	ClientBroker cb1;
	Client c = new Client();	
	ListView<String> cList = new ListView<>();
	ObservableList<String> cl = FXCollections.observableArrayList();
	ObservableList<Client> cl2 = FXCollections.observableArrayList();
	ArrayList<Client> cResult = new ArrayList<Client>();
	
	Menu cTab = new Menu("Client");
	MenuItem cTaSw = new MenuItem("Switch to...");
	Menu rTab = new Menu("Residential");
	MenuItem rTaSw = new MenuItem("Switch to...");
	Menu coTab = new Menu("Commercial");
	MenuItem coTaSw = new MenuItem("Switch to...");
	MenuBar tabs = new MenuBar();
	
	VBox cSearch = new VBox();
	Label cLbl = new Label("Search Clients");
	Label seaTypeLbl = new Label("Select type of Search to be performed:");
	
	ToggleGroup cSearchGroup = new ToggleGroup();
	RadioButton cSearchId = new RadioButton("Client ID");
	RadioButton cSearchLN = new RadioButton("Last Name");
	RadioButton cSearchCT = new RadioButton("Client Type");
	
	HBox cSearchArea = new HBox();
	TextField cSearchBar = new TextField();
	Button cSearchBut = new Button("Search");
	Button cSearchClr = new Button("Clear Search");
	
	VBox cInfo = new VBox();
	Label cILbl = new Label("Client Information");
	HBox cID = new HBox();
	Label cIDLbl = new Label("Client ID: ");
	Label cIDFld = new Label();
	HBox cFN = new HBox();
	Label cFNLbl = new Label("First Name: ");
	TextField cFNFld = new TextField();
	HBox cLN = new HBox();
	Label cLNLbl = new Label("Last Name: ");
	TextField cLNFld = new TextField();
	HBox cAD = new HBox();
	Label cADLbl = new Label("Address: ");
	TextField cADFld = new TextField();
	HBox cPC = new HBox();
	Label cPCLbl = new Label("Postal Code: ");
	TextField cPCFld = new TextField();
	HBox cPN = new HBox();
	Label cPNLbl = new Label("Phone Number: ");
	TextField cPNFld = new TextField();
	HBox cTP = new HBox();
	Label cTPLbl = new Label("Client ID: ");
	ChoiceBox<String> cTPCB = new ChoiceBox<>();
	
	HBox cButs = new HBox();
	Button cSave = new Button("Save");
	Button cDelete = new Button("Delete");
	Button cClear = new Button("Clear");
	
	/*
	 * Residential
	 */
	ResidentialPropertyBroker rb1;
	ResidentialProperty r = new ResidentialProperty();	
	ListView<String> rList = new ListView<>();
	ObservableList<String> rl = FXCollections.observableArrayList();
	ObservableList<ResidentialProperty> rl2 = FXCollections.observableArrayList();
	ArrayList<ResidentialProperty> rResult = new ArrayList<ResidentialProperty>();
	
	VBox rSearch = new VBox();
	Label rLbl = new Label("Search Residential Property");
	Label rseaTypeLbl = new Label("Select type of Search to be performed:");
	
	ToggleGroup rSearchGroup = new ToggleGroup();
	RadioButton rSearchId = new RadioButton("Residential Property ID");
	RadioButton rSearchQU = new RadioButton("Quadrant of City");
	RadioButton rSearchPr = new RadioButton("Residential Property Price");
	
	HBox rSearchArea = new HBox();
	TextField rSearchBar = new TextField();
	Button rSearchBut = new Button("Search");
	Button rSearchClr = new Button("Clear Search");
	
	VBox rInfo = new VBox();
	Label rILbl = new Label("Residential Property Information");
	
	HBox rID = new HBox();
	Label rIDLbl = new Label("Residential Property ID: ");
	Label rIDFld = new Label();
	
	HBox rPD = new HBox();
	Label rPDLbl = new Label("Property Legal Description: ");
	TextField rPDFld = new TextField();
	
	HBox rPA = new HBox();
	Label rPALbl = new Label("Property Address: ");
	TextField rPAFld = new TextField();
	
	HBox rQU = new HBox();
	Label rQULbl = new Label("City Quadrant: ");
	ChoiceBox<String> rQUCB = new ChoiceBox<>();
	
	HBox rZo = new HBox();
	Label rZoLbl = new Label("Zoning of Property: ");
	ChoiceBox<String> rZoCB = new ChoiceBox<>();
	
	HBox rPr = new HBox();
	Label rPrLbl = new Label("Property Asking Price: ");
	TextField rPrFld = new TextField();
	
	HBox rSq = new HBox();
	Label rSqLbl = new Label("Building Square Footage: ");
	TextField rSqFld = new TextField();
	
	HBox rBa = new HBox();
	Label rBaLbl = new Label("No. of Bathrooms: ");
	TextField rBaFld = new TextField();
	
	HBox rBe = new HBox();
	Label rBeLbl = new Label("No. of Bedrooms: ");
	TextField rBeFld = new TextField();
	
	HBox rGa = new HBox();
	Label rGaLbl = new Label("Garage Type: ");
	ChoiceBox<String> rGaCB = new ChoiceBox<>();
	
	HBox rCo = new HBox();
	Label rCoLbl = new Label("Comments about property: ");
	TextField rCoFld = new TextField();
	
	HBox rButs = new HBox();
	Button rSave = new Button("Save");
	Button rDelete = new Button("Delete");
	Button rClear = new Button("Clear");
	
	
	/*
	 * Commercial
	 */
	CommercialPropertyBroker cob1;
	CommercialProperty co = new CommercialProperty();	
	ListView<String> coList = new ListView<>();
	ObservableList<String> col = FXCollections.observableArrayList();
	ObservableList<CommercialProperty> col2 = FXCollections.observableArrayList();
	ArrayList<CommercialProperty> coResult = new ArrayList<CommercialProperty>();
	
	VBox coSearch = new VBox();
	Label coLbl = new Label("Search Commercial Property");
	Label coseaTypeLbl = new Label("Select type of Search to be performed:");
	
	ToggleGroup coSearchGroup = new ToggleGroup();
	RadioButton coSearchId = new RadioButton("Commercial Property ID");
	RadioButton coSearchQU = new RadioButton("Quadrant of City");
	RadioButton coSearchPr = new RadioButton("Commercial Property Price");
	
	HBox coSearchArea = new HBox();
	TextField coSearchBar = new TextField();
	Button coSearchBut = new Button("Search");
	Button coSearchClr = new Button("Clear Search");
	
	VBox coInfo = new VBox();
	Label coILbl = new Label("Commercial Property Information");
	
	HBox coID = new HBox();
	Label coIDLbl = new Label("CommercialProperty ID: ");
	Label coIDFld = new Label();
	
	HBox coPD = new HBox();
	Label coPDLbl = new Label("Property Legal Description: ");
	TextField coPDFld = new TextField();
	
	HBox coPA = new HBox();
	Label coPALbl = new Label("Property Address: ");
	TextField coPAFld = new TextField();
	
	HBox coQU = new HBox();
	Label coQULbl = new Label("City Quadrant: ");
	ChoiceBox<String> coQUCB = new ChoiceBox<>();
	
	HBox coZo = new HBox();
	Label coZoLbl = new Label("Zoning of Property: ");
	ChoiceBox<String> coZoCB = new ChoiceBox<>();
	
	HBox coPr = new HBox();
	Label coPrLbl = new Label("Property Asking Price: ");
	TextField coPrFld = new TextField();
	
	HBox coTp = new HBox();
	Label coTpLbl = new Label("Property Type: ");
	ChoiceBox<String> coTpCB = new ChoiceBox<>();
	
	HBox coFl = new HBox();
	Label coFlLbl = new Label("No. of Floors: ");
	TextField coFlFld = new TextField();
	
	HBox coCo = new HBox();
	Label coCoLbl = new Label("Comments about property: ");
	TextField coCoFld = new TextField();
	
	HBox coButs = new HBox();
	Button coSave = new Button("Save");
	Button coDelete = new Button("Delete");
	Button coClear = new Button("Clear");
	
	/**
	 * Initializes the window for the GUI.
	 * @throws InvalidLegalDescriptionException 
	 * @throws InvalidNumberOfBathroomsException 
	 * @throws NumberFormatException 
	 */
	@Override
	public void start(Stage primaryStage) throws InvalidLegalDescriptionException, NumberFormatException, InvalidNumberOfBathroomsException
	{
		cob1 = CommercialPropertyBroker.getBroker();
		rb1 = ResidentialPropertyBroker.getBroker();
		
		window = primaryStage;
		window.setTitle("SAIT MLS");
		layout = new BorderPane();
		
		
		cTab.getItems().add(cTaSw);
		rTab.getItems().add(rTaSw);
		coTab.getItems().add(coTaSw);
		tabs.getMenus().addAll(cTab, rTab, coTab);
		
		/*
		 * Client
		 */
		try
		{
			cb1 = ClientBroker.getBroker();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cSearchId.setToggleGroup(cSearchGroup);
		cSearchId.setUserData(1);
		cSearchId.setSelected(true);
		cSearchLN.setToggleGroup(cSearchGroup);
		cSearchLN.setUserData(2);
		cSearchCT.setToggleGroup(cSearchGroup);
		cSearchCT.setUserData(3);
	
		cSearchArea.getChildren().addAll(cSearchBar, cSearchBut, cSearchClr);
		cSearch.getChildren().addAll(cLbl, seaTypeLbl, cSearchId, cSearchLN, cSearchCT, cSearchArea, cList);
		
		
		cID.getChildren().addAll(cIDLbl, cIDFld);
		cFN.getChildren().addAll(cFNLbl, cFNFld);	
		cLN.getChildren().addAll(cLNLbl, cLNFld);
		cAD.getChildren().addAll(cADLbl, cADFld);	
		cPC.getChildren().addAll(cPCLbl, cPCFld);	
		cPN.getChildren().addAll(cPNLbl, cPNFld);	
		cTPCB.getItems().addAll("C", "R");
		cTP.getChildren().addAll(cTPLbl, cTPCB);	
		
		cButs.getChildren().addAll(cSave, cDelete, cClear);
		
		cInfo.getChildren().addAll(cILbl,cID, cFN, cLN, cAD, cPC, cPN, cTP, cButs);
		
		
		//****DONT TOUCH UNLESS U WANNA FAIL AND BE HOMELESS *******//
		cSearchBut.setOnAction(e -> {
			cSearchClicked();	
		});
		
		cSearchClr.setOnAction(e -> {
			cSearchClr();
		});
		
		cList.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			cSelected();
		});
		
		cSave.setOnAction(e -> {
			cSaveClicked();
		});
		
		cDelete.setOnAction(e -> {
			cDeleteClicked();
		});
		
		cClear.setOnAction(e -> {
			cClearClicked();
		});
		
		/*
		 * Residential
		 */
		rSearchId.setToggleGroup(rSearchGroup);
		rSearchId.setUserData(4);
		rSearchId.setSelected(true);
		rSearchQU.setToggleGroup(rSearchGroup);
		rSearchQU.setUserData(5);
		rSearchPr.setToggleGroup(rSearchGroup);
		rSearchPr.setUserData(6);
		
		rSearchArea.getChildren().addAll(rSearchBar, rSearchBut, rSearchClr);
		rSearch.getChildren().addAll(rLbl, rseaTypeLbl, rSearchId,rSearchQU,rSearchPr,rSearchArea,rList);
		
		rID.getChildren().addAll(rIDLbl, rIDFld);
		rPD.getChildren().addAll(rPDLbl, rPDFld);
		rPA.getChildren().addAll(rPALbl, rPAFld);
		rQUCB.getItems().addAll("NW", "NE", "SE", "SW");
		rQU.getChildren().addAll(rQULbl, rQUCB);
		rZoCB.getItems().addAll("R1", "R2", "R3", "R4");
		rZo.getChildren().addAll(rZoLbl, rZoCB);
		rPr.getChildren().addAll(rPrLbl, rPrFld);
		rSq.getChildren().addAll(rSqLbl, rSqFld);
		rBa.getChildren().addAll(rBaLbl, rBaFld);
		rBe.getChildren().addAll(rBeLbl, rBeFld);
		rGaCB.getItems().addAll("A", "D", "N");
		rGa.getChildren().addAll(rGaLbl, rGaCB);
		rCo.getChildren().addAll(rCoLbl, rCoFld);
		
		rButs.getChildren().addAll(rSave, rDelete, rClear);
		rInfo.getChildren().addAll(rILbl, rID,rPD,rPA,rQU,rZo,rPr,rSq,rBa,rBe,rGa,rCo,rButs);
		
		rSearchBut.setOnAction(e -> {
			rSearchClicked();
		});
		
		rSearchClr.setOnAction(e -> {
			rSearchClr();
		});
		
		rList.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			rSelected();
		});
		
		rSave.setOnAction(e -> {
			rSaveClicked();
		});
		
		rDelete.setOnAction(e -> {
			rDeleteClicked();
		});
		
		rClear.setOnAction(e -> {
			rClearClicked();
		});
		
		/*
		 * Commercial
		 */
		coSearchId.setToggleGroup(coSearchGroup);
		coSearchId.setUserData(7);
		coSearchId.setSelected(true);
		coSearchQU.setToggleGroup(coSearchGroup);
		coSearchQU.setUserData(8);
		coSearchPr.setToggleGroup(coSearchGroup);
		coSearchPr.setUserData(9);
		
		coSearchArea.getChildren().addAll(coSearchBar, coSearchBut, coSearchClr);
		coSearch.getChildren().addAll(coLbl, coseaTypeLbl, coSearchId,coSearchQU,coSearchPr,coSearchArea,coList);
		
		coID.getChildren().addAll(coIDLbl, coIDFld);
		coPD.getChildren().addAll(coPDLbl, coPDFld);
		coPA.getChildren().addAll(coPALbl, coPAFld);
		coQUCB.getItems().addAll("NW", "NE", "SE", "SW");
		coQU.getChildren().addAll(coQULbl, coQUCB);
		coZoCB.getItems().addAll("I1", "I2", "I3", "I4");
		coZo.getChildren().addAll(coZoLbl, coZoCB);
		coPr.getChildren().addAll(coPrLbl, coPrFld);
		coTpCB.getItems().addAll("M", "O");
		coTp.getChildren().addAll(coTpLbl,coTpCB);
		coFl.getChildren().addAll(coFlLbl, coFlFld);
		coCo.getChildren().addAll(coCoLbl, coCoFld);
		
		coButs.getChildren().addAll(coSave, coDelete, coClear);
		
		coInfo.getChildren().addAll(coILbl, coID,coPD,coPA,coQU,coZo,coPr,coTp,coFl,coCo,coButs);
		
		coSearchBut.setOnAction(e -> {
			coSearchClicked();
			//System.out.println("Button clicked from setonaction");
		});

		coSearchClr.setOnAction(e -> {
			coSearchClr();
		});
		
		coList.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
			coSelected();
		});
		
		coSave.setOnAction(e -> {
			coSaveClicked();
		});
		
		coDelete.setOnAction(e -> {
			coDeleteClicked();
		});
		
		coClear.setOnAction(e -> {
			coClearClicked();
		});
		
		
		/*
		 * settings
		 */
		//Changing views
		cTab.setOnAction(e -> {
			//System.out.println("Cli");
			layout.setLeft(cSearch);
			layout.setRight(cInfo);
		});
		rTab.setOnAction(e -> {
			//System.out.println("Res");
			layout.setLeft(rSearch);
			layout.setRight(rInfo);
		});
		coTab.setOnAction(e -> {
			layout.setLeft(coSearch);
			layout.setRight(coInfo);
		});
		
		window.setOnCloseRequest(e -> {
			e.consume();
			cb1.closeBroker();
			rb1.closeBroker();
			cob1.closeBroker();
			window.close();
		});
		
		cSearch.setPadding(new Insets(20, 0, 0, 80));
		cInfo.setPadding(new Insets(100, 100, 0, 0));
		rSearch.setPadding(new Insets(20, 0, 0, 80));
		rInfo.setPadding(new Insets(100, 100, 0, 0));
		coSearch.setPadding(new Insets(20, 0, 0, 80));
		coInfo.setPadding(new Insets(100, 100, 0, 0));
		
		//defaults:
		layout.setTop(tabs);
		layout.setLeft(cSearch);		//default
		layout.setRight(cInfo);		//default
		
		//layout.setLeft(rSearch);
		//layout.setRight(rInfo);
		
		//layout.setLeft(coSearch);
		//layout.setRight(coInfo);
		scene = new Scene(layout, 800, 600);
		window.setScene(scene);
		window.show();
	}
	/*
	 * Client
	 */
	Client c2Mani = new Client();
	/**
	 * Method responsible for selecting the Client
	 */
	public void cSelected()
	{
		if (!cl.isEmpty())
		{
			cSelectedi = cList.getSelectionModel().getSelectedIndex();
		cIDFld.setText(Long.toString(cl2.get(cSelectedi).getClientID()));
		cFNFld.setText(cl2.get(cSelectedi).getFirstName());
		cLNFld.setText(cl2.get(cSelectedi).getLastName());
		cADFld.setText(cl2.get(cSelectedi).getAddress());
		cPCFld.setText(cl2.get(cSelectedi).getPostalCode());
		cPNFld.setText(cl2.get(cSelectedi).getPhoneNumber());
		if (cl2.get(cSelectedi).getClientType() == 'C' )
			cTPCB.getSelectionModel().selectFirst();
		else
			cTPCB.getSelectionModel().selectLast();
		c2Mani.setClientID(Long.parseLong(cIDFld.getText()));
		}
	}
	
	/**
	 * Method responsible for handling the event for Client Search button
	 */
	public void cSearchClicked()
	{
		String cSearchFor = cSearchBar.getText();
		switch (cSearchGroup.getSelectedToggle().getUserData().toString())
		{
		case "1":
			c = new Client();
			try
			{
				c.setClientID(Long.parseLong(cSearchFor));
			} catch (NumberFormatException e)
			{
				AlertBox.display("Error", "Not a valid number to search for.");
			}
			break;
		case "2":
			c = new Client();
			c.setLastName(cSearchFor);
			break;
		case "3":
			c = new Client();
			cSearchFor = cSearchFor.toUpperCase();
			try
			{
				
				c.setClientType(cSearchFor.charAt(0));
			} catch (InvalidClientTypeException e1)
			{
				AlertBox.display("Error", "Invalid Client Type");
			} catch (StringIndexOutOfBoundsException e)
			{
				AlertBox.display("Error", "Invalid Client Type");
			}
			break;
		}
		
		try
		{
			cResult = (ArrayList<Client>) cb1.search(c);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		cl.clear();
		cl2.clear();
		for (Client x : cResult)
		{
			cl.addAll(x.toString());
			cl2.add(x);
		}	
		cList.setItems(cl);	
		cClearClicked();
	}
	
	/**
	 * Method responsible for handling the event for Client Save button
	 */
	public void cSaveClicked()
	{
		boolean cNeworNah = false;
		int cSucc = 0;
		c = new Client();
		if (cIDFld.getText() == "")
		{
			cNeworNah = true;
			cIDFld.setText("0");
		}
		c.setClientID(Long.parseLong(cIDFld.getText()));
		c.setFirstName(cFNFld.getText());
		c.setLastName(cLNFld.getText());
		c.setAddress(cADFld.getText());
		try
		{
			c.setPostalCode(cPCFld.getText().toUpperCase());
			cSucc += 1;
		} catch (InvalidPostalCodeException e)
		{
			AlertBox.display("Error", "Invalid Postal Code. Must follow format X0X 0X0");
			cSucc -= 1;
		}
		
		try
		{
			c.setPhoneNumber(cPNFld.getText());
			cSucc +=1;
		} catch (InvalidPhoneNumberException e)
		{
			AlertBox.display("Error", "Invalid Phone Number. Must follow format 123-456-7890");
			cSucc -=1;
		}
		
		try
		{	
			c.setClientType(cTPCB.getSelectionModel().getSelectedItem().charAt(0));
			cSucc +=1;
		} catch (InvalidClientTypeException e)
		{
			AlertBox.display("Error", "Invalid Client Type.");
			cSucc -=1;
		}
		
		if (cSucc == 3 && cNeworNah == true)
		{
			cb1.persist(c);
			AlertBox.display("Success", "Successfully Added.");
			//cSearchClicked();
			
		} else if (cSucc == 3) {
			cb1.persist(c);
			AlertBox.display("Success", "Successfully Modified.");
			//cSearchClicked();
			
		}else {
			AlertBox.display("Error", "Failed to add Client");
			
		}
		cSearchClr();
		
	}
	
	/**
	 * Method responsible for handling the event for Client Delete button
	 */
	public void cDeleteClicked()
	{
		if (cb1.remove(c2Mani))
		{
			AlertBox.display("Success", "Successfully Removed.");
			//cSearchClicked();
		} else {
			AlertBox.display("Error", "Failed to delete");
		}
	}
	
	/**
	 * Method responsible for handling the event for Client Clear button
	 */
	public void cClearClicked()
	{
		cIDFld.setText("");
		cFNFld.setText(null);
		cLNFld.setText(null);
		cADFld.setText(null);
		cPCFld.setText(null);
		cPNFld.setText(null);
		cTPCB.getSelectionModel().clearSelection();
	}
	
	/**
	 * Method responsible for handling the event for the Clear Search button
	 */
	public void cSearchClr()
	{
		cSearchBar.clear();
		cl.clear();
		cl2.clear();
		cList.setItems(cl);
		cList.refresh();
	}
	
	
	
	
	
	/*
	 * Residential
	 */
	ResidentialProperty r2Mani = new ResidentialProperty();
	int rI = 0;
	/**
	 * Method responsible for selecting the Residential Property
	 */
	public void rSelected()
	{
		if (!rl.isEmpty())
		{
			rI = rList.getSelectionModel().getSelectedIndex();
		
		rIDFld.setText(Long.toString(rl2.get(rI).getId()));
		rPDFld.setText(rl2.get(rI).getLegalDescription());
		rPAFld.setText(rl2.get(rI).getAddress());
		switch(rl2.get(rI).getQuadrant())
		{
		case "NW":
			rQUCB.getSelectionModel().select("NW");
			break;
		case "NE":
			rQUCB.getSelectionModel().select("NE");
			break;
		case "SW":
			rQUCB.getSelectionModel().select("SW");
			break;
		case "SE":
			rQUCB.getSelectionModel().select("SE");
			break;
		}
		switch (rl2.get(rI).getZone())
		{
		case "R1":
			rZoCB.getSelectionModel().select("R1");
			break;
		case "R2":
			rZoCB.getSelectionModel().select("R2");
			break;
		case "R3":
			rZoCB.getSelectionModel().select("R3");
			break;
		case "R4":
			rZoCB.getSelectionModel().select("R4");
			break;
		}
		rPrFld.setText(Double.toString(rl2.get(rI).getAskingPrice()));
		rSqFld.setText(Double.toString(rl2.get(rI).getArea()));
		rBaFld.setText(Double.toString(rl2.get(rI).getBathrooms()));
		rBeFld.setText(Integer.toString(rl2.get(rI).getBedrooms()));
		switch (rl2.get(rI).getGarage())
		{
		case 'A':
			rGaCB.getSelectionModel().select("A");
			break;
		case 'D':
			rGaCB.getSelectionModel().select("D");
			break;
		case 'N':
			rGaCB.getSelectionModel().select("N");
			break;
		}
		rCoFld.setText(rResult.get(rI).getComments());
		r2Mani.setId(Long.parseLong(rIDFld.getText()));		//assign ID to r2Manipulate
		}
	}
	
	/**
	 * Method responsible for handling the event for the Residential Search button
	 */
	public void rSearchClicked()
	{
		String rSearchFor = rSearchBar.getText();
		switch (rSearchGroup.getSelectedToggle().getUserData().toString())
		{
		case "4":
			try
			{
				r = new ResidentialProperty();
				r.setId(Long.parseLong(rSearchFor));
			} catch (NumberFormatException e)
			{
				AlertBox.display("Error", "Not a valid number to search for.");
			}
			break;
		case "5":
			rSearchFor = rSearchFor.toUpperCase();
			r = new ResidentialProperty();
			r.setQuadrant(rSearchFor);
			break;
		case "6":
			try
			{
				r = new ResidentialProperty();
				r.setAskingPrice(Double.parseDouble(rSearchFor));
			} catch (NumberFormatException e)
			{
				AlertBox.display("Error", "Not a valid number to search for.");
			}
			break;
		}
		try
		{
			rResult = (ArrayList<ResidentialProperty>) rb1.search(r);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rl.clear();
		rl2.clear();
		for (ResidentialProperty x : rResult)
		{
			rl.addAll(x.toString());
			rl2.add(x);
		}	
		rList.setItems(rl);
		rClearClicked();
	}
	
	/**
	 * Method responsible for handling the event for the Residential Save button
	 */
	public void rSaveClicked()
	{
		boolean rNeworNah = false;
		r = new ResidentialProperty();
		int rSucc = 0;
		
		if (rIDFld.getText() == "")
		{
			rNeworNah = true;
			rIDFld.setText("0");
		}
		try
		{
			
			r.setId(Long.parseLong(rIDFld.getText()));
			r.setLegalDescription(rPDFld.getText());
			r.setAddress(rPAFld.getText());
			r.setQuadrant(rQUCB.getSelectionModel().getSelectedItem());
			r.setZone(rZoCB.getSelectionModel().getSelectedItem());
			r.setAskingPrice(Double.parseDouble(rPrFld.getText()));
			r.setArea(Double.parseDouble(rSqFld.getText()));
			r.setBathrooms(Double.parseDouble(rBaFld.getText()));
			r.setBedrooms(Integer.parseInt(rBeFld.getText()));
			r.setGarage(rGaCB.getSelectionModel().getSelectedItem().toString().charAt(0));
			r.setComments(rCoFld.getText());
			
			rSucc += 1;
		}
			catch (NumberFormatException e) 
		{
			AlertBox.display("Error", "Wrong number format");
			rSucc -=1;
		} catch (InvalidLegalDescriptionException e)
		{
				AlertBox.display("Error", "Invalid Legal Description. input must be format of [0-9999][A-Z][0-9999][-][0-99]");
		} catch (InvalidNumberOfBathroomsException e)
		{
				AlertBox.display("Error", "Invalid number of bathrooms. Must be divisible by .5");
		}
		if (rSucc == 1 && rNeworNah == true)
		{
			rb1.persist(r);
			AlertBox.display("Success", "Successfully Added.");
			//rSearchClicked();
		} else if (rSucc == 1) {
			rb1.persist(r);
			AlertBox.display("Success", "Successfully Modified.");
			//rSearchClicked();
		} else {
			AlertBox.display("Error", "Failed to add Property.");
		}
		rSearchClr();
		
	}
	
	/**
	 * Method responsible for handling the event for the Residential Delete button
	 */
	public void rDeleteClicked()
	{
		if (rb1.remove(r2Mani))
		{
			AlertBox.display("Success", "Successfully Removed.");
			//rSearchClicked();
		}
	}
	
	/**
	 * Method responsible for handling the event for the Residential Clear button
	 */
	public void rClearClicked()
	{
		rIDFld.setText("");
		rPDFld.setText(null);
		rPAFld.setText(null);
		rQUCB.getSelectionModel().clearSelection();
		rZoCB.getSelectionModel().clearSelection();
		rPrFld.setText(null);
		rSqFld.setText(null);
		rBaFld.setText(null);
		rBeFld.setText(null);
		rGaCB.getSelectionModel().clearSelection();
		rCoFld.setText(null);
	}
	
	/**
	 * Method responsible for handling the event for the Clear Search button
	 */
	public void rSearchClr()
	{
		rSearchBar.clear();
		rl.clear();
		rl2.clear();
		rList.setItems(rl);
		rList.refresh();
	}
	
	
	
	
	
	CommercialProperty co2Mani = new CommercialProperty();
	int coI = 0;
	/**
	 * Method responsible for selecting the Commercial Property
	 */
	public void coSelected()
	{
		if (!col.isEmpty())
		{
			coI = coList.getSelectionModel().getSelectedIndex();
		
		coIDFld.setText(Long.toString(col2.get(coI).getId()));
		coPDFld.setText(col2.get(coI).getLegalDescription());
		coPAFld.setText(col2.get(coI).getAddress());
		switch(col2.get(coI).getQuadrant())
		{
		case "NW":
			coQUCB.getSelectionModel().select("NW");
			break;
		case "NE":
			coQUCB.getSelectionModel().select("NE");
			break;
		case "SW":
			coQUCB.getSelectionModel().select("SW");
			break;
		case "SE":
			coQUCB.getSelectionModel().select("SE");
			break;
		}
		switch (col2.get(coI).getZone())
		{
		case "I1":
			coZoCB.getSelectionModel().select("I1");
			break;
		case "I2":
			coZoCB.getSelectionModel().select("I2");
			break;
		case "I3":
			coZoCB.getSelectionModel().select("I3");
			break;
		case "I4":
			coZoCB.getSelectionModel().select("I4");
			break;
		}
		coPrFld.setText(Double.toString(col2.get(coI).getAskingPrice()));
		switch (col2.get(coI).getType())
		{
		case "M":
			coTpCB.getSelectionModel().select("M");
			break;
		case "O":
			coTpCB.getSelectionModel().select("O");
			break;
		}
		coFlFld.setText(Integer.toString(coResult.get(coI).getNoFloors()));
		coCoFld.setText(coResult.get(coI).getComments());
		co2Mani.setId(Long.parseLong(coIDFld.getText())); //assign id to co2manipulate
		}
	}
	
	/**
	 * Method responsible for handling the event for the Commercial Save button
	 */
	public void coSaveClicked() 
	{
		boolean coNeworNah = false;
		co = new CommercialProperty();
		int coSucc = 0;
		
		if (coIDFld.getText() == "")
		{
			coNeworNah = true;
			coIDFld.setText("0");
		}
		try
		{
			
			co.setId(Long.parseLong(coIDFld.getText()));
			co.setLegalDescription(coPDFld.getText());
			co.setAddress(coPAFld.getText());
			co.setQuadrant(coQUCB.getSelectionModel().getSelectedItem());
			co.setZone(coZoCB.getSelectionModel().getSelectedItem());
			co.setAskingPrice(Double.parseDouble(coPrFld.getText()));
			co.setType(coTpCB.getSelectionModel().getSelectedItem());
			co.setNoFloors(Integer.parseInt(coFlFld.getText()));
			co.setComments(coCoFld.getText());
			coSucc += 1;
			
		} catch (NumberFormatException e) {
			AlertBox.display("Error", "Wrong number format");
			coSucc -= 1;
		} catch (NullPointerException e) {
			AlertBox.display("Error", "Please try Again");
		} catch (InvalidLegalDescriptionException e)
		{
			AlertBox.display("Error", "Invalid Legal Description Format. input must be format of [0-9999][A-Z][0-9999][-][0-99]");
		}
		
		
		if (coSucc == 1 && coNeworNah == true)
		{
			cob1.persist(co);
			AlertBox.display("Success", "Successfully Added.");
			//coSearchClicked();
		} else if (coSucc == 1) {
			cob1.persist(co);
			AlertBox.display("Success", "Successfully Modified.");
			//coSearchClicked();
		} else {
			AlertBox.display("Error", "An unknown error occured that is unknown to the creator of this rushed program");
		}
		coSearchClr();
	}
	
	/**
	 * Method responsible for handling the event for the Commercial Search button
	 */
	public void coSearchClicked()
	{
		String coSearchFor = coSearchBar.getText();
		switch (coSearchGroup.getSelectedToggle().getUserData().toString())
		{
		case "7":
			co = new CommercialProperty();
			co.setId(Long.parseLong(coSearchFor));
			break;
		case "8":
			coSearchFor = coSearchFor.toUpperCase();
			co = new CommercialProperty();
			co.setQuadrant(coSearchFor);
			//System.out.println("search for quadrant");
			break;
		case "9":
			co = new CommercialProperty();
			co.setAskingPrice(Double.parseDouble(coSearchFor));
			break;
		}
		try
		{
			coResult = (ArrayList<CommercialProperty>) cob1.search(co);
			//System.out.println("executed from search clicked");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		col.clear();
		col2.clear();
		for (CommercialProperty x : coResult)
		{
			col.addAll(x.toString());
			col2.add(x);
		}	
		coList.setItems(col);
		//coClearClicked();
	}
	
	/**
	 * Method responsible for handling the event for the Commercial Clear button
	 */
	public void coClearClicked()
	{
		coIDFld.setText("");
		coPDFld.setText(null);
		coPAFld.setText(null);
		coQUCB.getSelectionModel().clearSelection();
		coZoCB.getSelectionModel().clearSelection();
		coPrFld.setText(null);
		coTpCB.getSelectionModel().clearSelection();
		coFlFld.setText(null);
		coCoFld.setText(null);
	}
	
	/**
	 * Method responsible for handling the event for the Commercial Delete button
	 */
	public void coDeleteClicked()
	{
		if (cob1.remove(co2Mani))
		{
			AlertBox.display("Success", "Successfully Removed.");
			coSearchClr();
		}
	}
	
	/**
	 * Method responsible for handling the event for the Clear Search button
	 */
	public void coSearchClr()
	{
		coSearchBar.clear();
		col.clear();
		col2.clear();
		coList.setItems(col);
		coList.refresh();
	}
}

