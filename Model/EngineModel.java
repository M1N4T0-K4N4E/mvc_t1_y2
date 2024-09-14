package Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Model.MilkCalculate.BrownCowCalculate;
import Model.MilkCalculate.PinkCowCalculate;
import Model.MilkCalculate.WhiteCowCalculate;
import View.EngineView;

public class EngineModel {

    private int resultPink  = 0;
    private int resultBrow  = 0;
    private int resultWhite  = 0;
    private ArrayList<String[]> cowData = new ArrayList<String[]>();
    private boolean error = false;
    private String resultMassage = "";

    public EngineModel(){
        readCSV();
    }
    
    public void executeFromCode(String cowCode){
        resetValue();
        
        if (!isValid(cowCode)) {
            error = true;
            setResultMassage(cowCode);
            return;
        }

        String[] cow = findCow(cowCode);

        if (cow == null) {
            error = true;
            setResultMassage(cowCode);
            return;
        }

        calculate(cow);
        setResultMassage(cowCode);
    }

    private String[] findCow(String cowCode) {
        for (String[] strings : cowData) {
            if (strings[0].equals(cowCode)) {
                return strings;
            }
        }
        return null;
    }

    private void calculate(String[] cow) {
        int yaer = Integer.parseInt(cow[2]);
        int month = Integer.parseInt(cow[3]);
        if (cow[1].equals("Pink")) {
            resultPink = PinkCowCalculate.Calculate(month);
        } else if (cow[1].equals("Brown")) {
            resultBrow = BrownCowCalculate.Calculate(yaer);
        } else if (cow[1].equals("White")) {
            resultWhite = WhiteCowCalculate.Calculate(yaer, month);
        }
    }

    private void readCSV() {
        String csvFile = "cowData.csv";
        String line;
        String splitFrom = ","; // Adjust this if your delimiter is different

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(splitFrom);
                cowData.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValid(String cowCode) {
        if (cowCode.length() != 8) {
            return false;
        }

        if (IsFirstZero(cowCode.charAt(0))) {
            return false;
        }

        if (!isNumber(cowCode)) {
            return false;  
        }
        return true;
    }

    private boolean isNumber(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean IsFirstZero(char c) {
        return c == '0';
    }

    private void resetValue() {
        resultPink = 0;
        resultBrow = 0;
        resultWhite = 0;
        error = false;
    }

    public String getResultMassage(){
        return resultMassage;
    }
    
    private void setResultMassage(String query){
        if (error) {
            resultMassage = "Cow code " + query + " is not valid.";
        } else {
            resultMassage = "Produced milk from cow code " + query + " is: " 
                        + (resultPink + resultBrow + resultWhite) + " liters.";
        }
    }
}