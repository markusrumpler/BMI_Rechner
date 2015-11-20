package com.example.michi.bmi_rechner;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void onClickCalculate(View button){
        final EditText input_weight = (EditText) findViewById(R.id.body_weight);
        final EditText input_height = (EditText) findViewById(R.id.body_height);
        final EditText input_age = (EditText) findViewById(R.id.age);
        final EditText input_name = (EditText) findViewById(R.id.name);

        String weight = input_weight.getText().toString();
        String height = input_height.getText().toString();
        String age = input_age.getText().toString();
        String name = input_name.getText().toString();

        check_input(weight, height);


    }

    public  boolean check_input(String weight, String height){
        if (weight.equals("") || height.equals("")){
            provide_Toast("Es muss eine Eingabe für das Gewicht und die Höhe vorhandne sein!", "error");
            return false;
        }
        else{
            try{
              Float.parseFloat(weight);
                try{
                    Float.parseFloat(height);
                    return true;
                }
                catch (Exception e){
                    provide_Toast("Die Eingabe für die Höhe ist ungültig", "error");
                    return false;
                }
            }
            catch (Exception e){
                provide_Toast("Die Eingabe für das Gewicht ist ungültig", "errror");
                return false;
            }
        }
    }

    public void provide_Toast(String message, String kind_of_message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        View view_toast = toast.getView();
        if (kind_of_message.equals("error")) { //Wenn error, dann Ausgabe des Toasts in roter Frabe
            view_toast.setBackgroundColor(Color.RED);
        }
        else if(kind_of_message.endsWith("information")){ // Wenn information, dann Ausgabe des Toasts in grüner Farbe
            view_toast.setBackgroundColor(Color.GREEN);
        }
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
