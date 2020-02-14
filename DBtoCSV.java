import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import project.Account;
import project.User;
import sample.data.Datasource;

public class ControllerStatement {

    @FXML
    public Label user;
    @FXML
    public Label accountNumber;
    @FXML
    public Label totalExpenses;
    @FXML
    public Label totalMoneySaved;
    @FXML
    public Label finalBalance;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ComboBox<String> yearComboBox;

    private User user(){
        return Datasource.getInstance().user;
    }

    private Account account(){
        return Datasource.getInstance().account;
    }
    
    public void getStatement()
    {
    	String accountNum = account().getAccountNumber();
    	String pullMonth = monthComboBox.getText();
    	String pullYear = yearComboBox.getText();
    	String userHome = System.getProperty("user.home"); //Pulls the users home directory dynamically for file storage
    	String outputFolder = userHome + File.separator + "statements"; //Creates a folder named "statements" in the users home directory
    	File folder = new File(outputFolder);
    	if (!folder.exists()) { //Checks if the folder (statements) already exists, and if it doesn't it creates one
    	   folder.mkdir();
    	}
    	FileWriter csvfile = new FileWriter(outputFolder+File.separator+""+pullYear+""+pullMonth+"_MonthlyStatement.csv");
    	List<Transaction> transactions = Datasource.getInstance.queryStatement(accountNum, pullMonth, pullYear);
    	String holder;
    	
    	csvfile.append("Account Number");
    	csvfile.append(",");
    	holder = Datasource.getInstance().account;
    	csvfile.append(holder);
    	csvfile.append(System.getProperty("line.separator"));
    	
    	//Loops through the list of transactions and pulls amount, type, details, and date and appends it to the csv file, separated by a comma
    	for(int i=0;i<transactions.size();i++)
    	{
    		holder = transactions.get(i).getType();
    		csvfile.append(holder);
    		csvfile.append(",");
    		holder = Double.toString(transactions.get(i).getAmount());
    		csvfile.append(holder);
    		csvfile.append(",");
    		holder = transactions.get(i).getDetails();
    		csvfile.append(holder);
    		csvfile.append(",");
    		holder = transactions.get(i).getDate();
    		csvfile.append(holder);
    		csvfile.append(System.getProperty("line.separator"));
    	}
    	csvfile.flush();
    	csvfile.close();
    	
    }

    public void initialize() {
        populateTable(account());
    }

    private void populateTable(Account account){

    }
}
