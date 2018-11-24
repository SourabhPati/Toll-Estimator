package c.sourabh.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankPage extends AppCompatActivity {

    Button back;
    Button finish;
    TextView tariff;
    Bundle ext;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_page);

        in = getIntent();
        ext = in.getExtras();

        tariff = (TextView)findViewById(R.id.tariff);
        tariff.setText(String.valueOf(ext.getDouble("tariff")));

        back = (Button)findViewById(R.id.back);
        finish = (Button)findViewById(R.id.okay);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bck = new Intent(ThankPage.this,Form.class);
                startActivity(bck);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
            }
        });

    }
}
