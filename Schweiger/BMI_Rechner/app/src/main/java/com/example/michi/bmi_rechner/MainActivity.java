package com.example.michi.bmi_rechner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText input_weight;
    public EditText input_height;
    public EditText input_age;
    public EditText input_name;

    public static final String intent_name = "intent_name";
    public static final String intent_height = "intent_height";
    public static final String intent_weight = "intent_weight";

    public void onClickCalculate(View button){

        String weight = input_weight.getText().toString();
        String height = input_height.getText().toString();
        String age = input_age.getText().toString();
        String name = input_name.getText().toString();

        if(check_input(weight, height)){
            float int_weight = Float.parseFloat(weight);
            float int_height = Float.parseFloat(height);
            final Intent intent = new Intent(this, showBMI.class);

            intent.putExtra(intent_name, name);
            intent.putExtra(intent_height, int_height);
            intent.putExtra(intent_weight, int_weight);

            startActivity(intent);
        }


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
                    if (Float.parseFloat(height) > 3.00){
                        provide_Toast("Die Eingabe für die Höhe ist ungültig (Sie müss ein Komma enthalten z.B: 1.80)", "error");
                        return false;
                    }
                    provide_Toast("BMI wird errechnet", "information");
                    return true;
                }
                catch (Exception e){
                    provide_Toast("Die Eingabe für die Höhe ist ungültig", "error");
                    return false;
                }
            }
            catch (Exception e){
                provide_Toast("Die Eingabe für das Gewicht ist ungültig", "error");
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

    /*public class EinstellungenBearbeiten extends PreferenceActivity{
        @Override
        public void onCreate(Bundle icicle){
            super.onCreate(icicle);

            this.addPreferencesFromResource(R.xml.bmi_einstellungen);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_weight = (EditText) findViewById(R.id.body_weight);
        input_height = (EditText) findViewById(R.id.body_height);
        input_age = (EditText) findViewById(R.id.age);
        input_name = (EditText) findViewById(R.id.name);

        final Bundle userdata = getIntent().getExtras();

        if (userdata != null) {
            input_weight.setText(String.valueOf(userdata.getFloat("intent_weight")));
            input_height.setText(String.valueOf(userdata.getFloat("intent_height")));
        }
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
