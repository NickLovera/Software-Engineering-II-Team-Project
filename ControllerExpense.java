package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.Account;
import project.Transaction;
import sample.data.Datasource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ControllerExpense {

    @FXML
    private TextField amountExpense;
    @FXML
    private TextArea detailsExpense;

    public Account account(){
        Datasource.getInstance().queryAccount();
        return Datasource.getInstance().account;
    }

    public void initialize(){

    }

    public boolean checkErrors(){
        return true;
    }

    public boolean expense() {
        Transaction expense = new Transaction();
        //Setting Transaction details
        expense.setType("Expense");
        expense.setAccount(account().getAccountNumber());
        expense.setAmount(Double.parseDouble(amountExpense.getText()));
        expense.setDetails(detailsExpense.getText());
        //Getting Date
        Date localDate = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
        expense.setDate(date);
        //Saving Transaction
        return Datasource.getInstance().saveTransaction(expense);
    }


}
