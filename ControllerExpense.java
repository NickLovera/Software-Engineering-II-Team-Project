package sample;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerExpense {

    @FXML
    public TextField amountExpense;
    @FXML
    public TextArea detailsExpense;

    public void initialize() {

    }
    
    public boolean expense() {
    	Transaction expense = new Transaction();
    	//Setting Transaction details
		expense.setType("expense");
		expense.setAccount(account().getAccountNumber());
		expense.setAmount(Double.valueOf(amountExpense.getText()));
		expense.setDetails(detailsExpense.getText());
		//Getting Date
		LocalDate localDate = java.time.LocalDate.now();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
		expense.setDate(date);
		//Saving Transaction
		return Datasource.getInstance.saveTransaction(expense);
	}

	private Account account() {
		return Datasource.getInstance.account();

	}
    
}
