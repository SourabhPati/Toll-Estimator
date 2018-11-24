package c.sourabh.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Form extends AppCompatActivity {

    EditText VehNo;
    Button proceed;
    Switch way;
    EditText adh;
    double tariff;
    boolean ways;
    int vehType;
    ScrollView sv;
    TextView si;
    ImageButton MB;
    ImageButton car;
    ImageButton Heavy1;
    ImageButton Heavy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        VehNo = (EditText)findViewById(R.id.VehicleNo);
        proceed = (Button)findViewById(R.id.Proceed);
        way = (Switch)findViewById(R.id.way);
        adh = (EditText)findViewById(R.id.adhaar);

        //proceed.setEnabled(false);
        vehType=0;

        si = (TextView) findViewById(R.id.selectinfo);
        sv = (ScrollView)findViewById(R.id.scrollView2);
        MB = (ImageButton)findViewById(R.id.motorcycle);
        car = (ImageButton)findViewById(R.id.car);
        Heavy1 = (ImageButton)findViewById(R.id.truck1);
        Heavy2 = (ImageButton)findViewById(R.id.truck2);


        MB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehType = 1;
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehType = 2;
            }
        });

        Heavy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehType = 3;
            }
        });

        Heavy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehType = 4;
            }
        });


        way.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    way.setText(way.getTextOn());
                else
                    way.setText(way.getTextOff());
                ways = isChecked;
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateVehNo() && validateAdhNo() && validateVehType())
                {
                    tariff = calculateTariff();
                    Intent intent = new Intent(Form.this,ThankPage.class);
                    intent.putExtra("tariff",tariff);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean validateVehNo() {

        String Vno = VehNo.getText().toString();
        Vno = Vno.replaceAll(" ","");
        if(Vno.isEmpty()) {
            VehNo.setError("Please enter your Vehicle number");
            return false;
        }
        if(Vno.length()<8 || Vno.length()>10) {
            VehNo.setError("Please enter a valid Vehicle number");
            return false;
        }
        return true;
    }

    private boolean validateAdhNo()
    {
        String adhNo = adh.getText().toString();
        adhNo = adhNo.replaceAll(" ","");
        if(adhNo.isEmpty())
        {
            adh.setError("Please enter your Adhaar number");
            return false;
        }
        if(!android.text.TextUtils.isDigitsOnly(adhNo) || adhNo.length()!= 12)
        {
            adh.setError("Please enter a valid Adhaar number");
            return false;
        }

        return true;
    }

    private boolean validateVehType()
    {
        if(vehType==0)
        {
            si.setError("");
            Toast.makeText(getApplicationContext(),"Please select a vehicle type",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private double calculateTariff()
    {
        double res = 0.0;
        if(vehType == 1)
            res = 0.0;
        if(vehType == 2)
            res = !ways ? 36.0 : 56.0;
        if(vehType == 3)
            res = !ways ? 104.0 : 154.0;
        if(vehType == 4)
            res = !ways ? 207.0 : 309.0;
        return res;
    }
}
