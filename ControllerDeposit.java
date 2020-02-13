package sample;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerDeposit {

	@FXML
	public TextField amountDeposit;
	
	@FXML
    public TextArea detailsDeposit;

	public void initialize() {

	}

	public boolean deposit(){
		Transaction deposit = new Transaction();
		//Setting transaction details
		deposit.setType("Deposit");
		deposit.setAccount(account().getAccountNumber());
		deposit.setAmount(amountDeposit);
		deposit.setDetails(detailsDeposit);
		//Getting Date
		LocalDate localDate = java.time.LocalDate.now();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
		deposit.setDate(date);

		return Datasource.getInstance.saveTransaction(deposit);
	}


	private Account account(){
		return Datasource.getInstance.account();
	}
}
