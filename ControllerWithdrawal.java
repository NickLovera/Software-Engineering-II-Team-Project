package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.Account;
import project.Transaction;
import sample.data.Datasource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ControllerWithdrawal {

    @FXML
    public TextField amountWithdrawal;

    @FXML
    public TextArea detailsWithdrawal;

    @FXML
    private Label errorMessage;


    private Account account(){
        return Datasource.getInstance().account;
    }

    public void initialize() {

    }

    public boolean checkErrors(){
        return true;

    }

    public boolean withdrawal(){
        Transaction withdrawal = new Transaction();
        //Setting transaction details
        withdrawal.setType("Withdrawal");
        withdrawal.setAccount(account().getAccountNumber());
        withdrawal.setAmount(Double.parseDouble(amountWithdrawal.getText()));
        withdrawal.setDetails(detailsWithdrawal.getText());
        //Getting Date
        Date localDate = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
        withdrawal.setDate(date);
        //Saving Withdrawal Transaction
        return Datasource.getInstance().saveTransaction(withdrawal);
    }





}
