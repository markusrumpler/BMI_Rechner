package com.example.michi.bmi_rechner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class showBMI extends AppCompatActivity implements Button.OnClickListener{

    public TextView user_name;
    public TextView user_bmi;
    public Button back_button;
    public Button show_bmi_table;

    public float user_weight;
    public float user_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bmi);

        user_name = (TextView) findViewById(R.id.name_txtView);
        user_bmi = (TextView) findViewById(R.id.bmi_txtView);
        back_button = (Button) findViewById(R.id.back_button);
        show_bmi_table = (Button) findViewById(R.id.table_button);
        back_button.setOnClickListener(this);
        show_bmi_table.setOnClickListener(this);

        final Bundle userdata = getIntent().getExtras();

        if (userdata != null) {
            final CalculateBMI ergebnis = new CalculateBMI();

            user_weight = userdata.getFloat(MainActivity.intent_weight);
            user_height = userdata.getFloat(MainActivity.intent_height);
            ergebnis.user_height = userdata.getFloat(MainActivity.intent_height);
            ergebnis.user_weight =  userdata.getFloat(MainActivity.intent_weight);
            String str_name = userdata.getString("intent_name");

            ergebnis.return_bmi();

            double double_user_bmi = ergebnis.round_bmi;
            String bmi_description;
            if (double_user_bmi > 40){
                bmi_description = "krankhafte Fettsucht";
                user_bmi.setBackgroundColor(Color.RED);
            }
            else if (double_user_bmi > 30) {
                bmi_description = "Fettsucht";
                user_bmi.setBackgroundColor(Color.YELLOW);
            }
            else if (double_user_bmi > 25){
                bmi_description = "Übergewicht";
                user_bmi.setBackgroundColor(Color.BLUE);
            }
            else if (double_user_bmi > 18.5){
                bmi_description = "Normalgewicht";
                user_bmi.setBackgroundColor(Color.GREEN);
            }
            else{
                bmi_description = "Untergewicht";
                user_bmi.setBackgroundColor(Color.LTGRAY);
            }

            user_name.setText("Ihr BMI beträgt: ");
            user_bmi.setText(String.valueOf(double_user_bmi)
                    + "\n" + bmi_description);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_bmi, menu);
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

    @Override
    public void onClick(View v) {
        if(v == back_button){
            final Intent intent = new Intent(this, MainActivity.class);

            intent.putExtra("intent_weight", user_weight);
            intent.putExtra("intent_height", user_height);


            startActivity(intent);
        }
        else if(v == show_bmi_table){
            show_BMI_table();
        }
    }

    public void show_BMI_table(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("BMI Tabelle");

        final ImageView img1 = new ImageView(this);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.bmi_tabelle);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 1000, 800, true);
        img1.setImageBitmap(bMapScaled);

        alert.setView(img1);

        alert.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.dismiss();

            }
        });
        alert.show();
    }
}
