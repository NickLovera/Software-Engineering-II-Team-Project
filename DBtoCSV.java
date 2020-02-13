import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBtoCSV {
	
    public static void main(String[] args) throws IOException {
    	
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
    		csvfile.append(",");
    	}
    	csvfile.flush();
    	csvfile.close();
    	
    }       
}

