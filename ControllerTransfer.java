package sample;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerTransfer {

	@FXML
	public TextField amountTransfer;
	@FXML
	public TextArea detailsTransfer;
	@FXML
	public TextField receivingAccount;

	public void initialize() {

	}

	//Transfering
	private boolean transfer() {

		Transaction transfer = new Transaction();

		transfer.setType("Transfer");
		transfer.setAccount(account().getAccountNumber());
		transfer.setReceivingAccount(receivingAccount.getText());
		transfer.setAmount(Double.valueOf(amountTransfer.getText()));
		transfer.setDetails(detailsTransfer.getText());
		//Getting Date
		LocalDate localDate = java.time.LocalDate.now();
		String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
		transfer.setDate(date);


		return Datasource.getInstance.saveTransaction(transfer);
	}

	private Account account() {
		return Datasource.getInstance.account();

	}
}
