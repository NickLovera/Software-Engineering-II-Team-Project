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
import java.util.List;


public class ControllerTransfer {

    @FXML
    private TextField amountTransfer;
    @FXML
    private TextArea detailsTransfer;
    @FXML
    private TextField receivingAccount;
    @FXML
    private Label errorMessage;


    public void initialize(){

    }

    private Account account() {
        return Datasource.getInstance().account;
    }

    public boolean checkForErrors(){
        List<String> accounts = Datasource.getInstance().queryAllAccounts();
        System.out.println(accounts.get(0) + ", " + accounts.get(1));

        int status = 0;
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).equals(receivingAccount.getText())){
                status++;
                break;
            }
        }
        if(status == 0){
            errorMessage.setText("Receiving account does not exist, please double check");
            return false;
        }
        return true;
    }

    //Transferring
    public boolean transfer() {

        Transaction transfer = new Transaction();

        transfer.setType("Transfer");
        transfer.setAccount(account().getAccountNumber());
        transfer.setReceivingAccount(receivingAccount.getText());
        transfer.setAmount(Double.parseDouble(amountTransfer.getText()));
        transfer.setDetails(detailsTransfer.getText());
        //Getting Date
        Date localDate = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(localDate);
        transfer.setDate(date);


        return Datasource.getInstance().saveTransaction(transfer);
    }




}
