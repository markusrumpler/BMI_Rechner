package com.example.michi.bmi_rechner;

/**
 * Created by Michi on 21.12.2015.
 */
public class CalculateBMI {
    public float user_height;
    public float user_weight;
    public double user_bmi;
    public double round_bmi;
    public String bmi_description;

    public void return_bmi(){
        user_bmi = (user_weight / (user_height * user_height));
        round_bmi = round(user_bmi, 2);
    }

    public double round(double zahl, int stellen) {
        return (double) ((int)zahl + (Math.round(Math.pow(10,stellen)*(zahl-(int)zahl)))/(Math.pow(10,stellen)));
    }
}
