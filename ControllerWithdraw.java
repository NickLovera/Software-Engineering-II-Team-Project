package sample;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controllerwithdrawal {

	@FXML
	public TextField amountwithdrawal;
	
	@FXML
    public TextArea detailswithdrawal;

	public void initialize() {

	}

	public boolean withdrawal(){
		Transaction withdrawal = new Transaction();
		//Setting transaction details
		withdrawal.setType("withdrawal");
		withdrawal.setAccount(account().getAccountNumber());
		withdrawal.setAmount(amountwithdrawal);
		withdrawal.setDetails(detailswithdrawal);
		//Getting Date
		LocalDate localDate = java.time.LocalDate.now();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
		withdrawal.setDate(date);
        //Saving Transaction
		return Datasource.getInstance.saveTransaction(withdrawal);
	}


	private Account account(){
		return Datasource.getInstance.account();
	}
}
