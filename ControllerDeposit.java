package sample;

import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.Account;
import project.Transaction;
import sample.data.Datasource;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class ControllerDeposit {

    @FXML
    private TextField amountDeposit;
    @FXML
    private TextArea detailsDeposit;

	//Getting current user's account
    private Account account(){
        Datasource.getInstance().queryAccount();
        return Datasource.getInstance().account;
    }

    public void initialize(){

    }

    public boolean checkErrors(){
        return true;
    }

    public boolean deposit(){
        Transaction deposit = new Transaction();
        //Setting transaction details
        deposit.setType("Deposit");
        deposit.setAccount(account().getAccountNumber());
        deposit.setAmount(Double.parseDouble(amountDeposit.getText()));
        deposit.setDetails(detailsDeposit.getText());
        //Getting Date
        Date localDate = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
        deposit.setDate(date);
        //Saving deposit transaction
        return Datasource.getInstance().saveTransaction(deposit);

    }







}
