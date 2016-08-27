package hablemoscodigo.robert.android.tipcalc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputTotal)
    EditText inputTotal;
    @Bind(R.id.btnCalcular)
    Button btnCalcular;
    @Bind(R.id.btnIncrementar)
    Button btnIncrementar;
    @Bind(R.id.btnDecrementar)
    Button btnDecrementar;
    @Bind(R.id.intputPropina)
    EditText intputPropina;
    @Bind(R.id.btnLimpiar)
    Button btnLimpiar;
    @Bind(R.id.txtPropina)
    TextView txtPropina;

    public static int incremento_propina = 1;
    public static int propina_default = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnCalcular)
    public void handleClickCalcular(){
        Log.e(getLocalClassName(),"click en submit");
        esconderTeclado();
        String strInputTotal = inputTotal.getText().toString().trim();
        if(!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            double tip = total*(tipPercentage/100d);
            String strTip = String.format(getString(R.string.global_message_propina),tip);
            txtPropina.setVisibility(View.VISIBLE);
            txtPropina.setText(strTip);

        }
    }

    public int getTipPercentage() {
        int tipPercentage = propina_default;
        String strInputTipPercentage = intputPropina.getText().toString().trim();
        if (!strInputTipPercentage.isEmpty()){
            tipPercentage=Integer.parseInt(strInputTipPercentage);
        }
        else{
            intputPropina.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    @OnClick(R.id.btnIncrementar)
    public void handleClickIncrementar(){
        esconderTeclado();
        handleTipChange(incremento_propina);

    }

    @OnClick(R.id.btnDecrementar)
    public void handleClickDecrementar(){
        esconderTeclado();
        handleTipChange(-incremento_propina);



    }

    private void handleTipChange(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if(tipPercentage > 0){
            intputPropina.setText(String.valueOf(tipPercentage));
        }
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }


    private void esconderTeclado() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }catch(NullPointerException npe){
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }


}
