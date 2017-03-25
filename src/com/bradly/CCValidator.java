package com.bradly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sylentbv on 3/25/2017.
 */
public class CCValidator extends  JFrame{
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JLabel validationResultsLabel;
    private JButton quitButton;

    public CCValidator(){
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(400,210));
        setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();

                boolean valid = isVisaCreditCardNumberValid(ccNumber);

                if(valid){
                    validationResultsLabel.setText("Credit Card Number is valid!");
                }
                else
                {
                    validationResultsLabel.setText("Credit Card Number is NOT valid!");
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(CCValidator.this,"Are you sure you want to exit?",
                        "Quit",JOptionPane.OK_CANCEL_OPTION);
                if(quit==JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private static boolean isVisaCreditCardNumberValid(String cc) {

        boolean bResult = false;
        //loop through entered value and create int array
        int[] iCCNumbers = new int[cc.length()];
        //loop through string, parse characters into the int array
        for(int x=0;x<cc.length();x++){
            iCCNumbers[x] = Integer.parseInt(cc.substring(x,x+1));
        }

        int iTotalValue=0;
        boolean bDoubleValue = false;
        //loop through cc numbers
        for(int x=iCCNumbers.length-1;x>=0;x--){
            // if value should be doubled, do so
            if(bDoubleValue){
                int iCurrentValue = iCCNumbers[x]*2;
                // if double is less than 10 use that, otherwise add digits
                if(iCurrentValue>9){
                    int sum=0;
                    int iCurrentDigit;
                    //use remainder to get singles digits until no more remain
                    while (iCurrentValue!= 0){
                        iCurrentDigit=iCurrentValue%10;
                        sum+=iCurrentDigit;
                        iCurrentValue=iCurrentValue/10;
                    }
                    iTotalValue+=sum;
                }
                else{
                    iTotalValue+=iCurrentValue;
                }
                bDoubleValue=false;
            }
            else{
                iTotalValue+=iCCNumbers[x];
                bDoubleValue=true;
            }
        }

        //if total value divisible by 10, valid number
        if(iTotalValue%10 == 0){
            bResult=true;
        }

        return bResult;

    }
}
