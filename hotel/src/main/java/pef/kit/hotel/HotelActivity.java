package pef.kit.hotel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HotelActivity extends ActionBarActivity implements FormFragment.OnFragmentInteractionListener, ResultsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        FormFragment formFragment = FormFragment.newInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, formFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hotel, menu);
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

    public void showToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public final static boolean isValidPhone(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target).matches();
        }
    }

    private void loopLayoutReset(LinearLayout ll){
        int count = ll.getChildCount();
        for (int i = 0;i <= count;i++){
            View v = ll.getChildAt(i);
            if (v instanceof LinearLayout) loopLayoutReset((LinearLayout) v);
            if (v != null && v instanceof EditText) {
                clearText(v);
            }
        }
    }

    private void clearText(View v){
        ((TextView) v).setText("");
    }

    @Override
    public void sendButton() {
        if (((EditText) findViewById(R.id.name)).getText() == null || ((EditText) findViewById(R.id.name)).getText().toString().equals("")) {
            showToast(getString(R.string.nameValidation));
            return;
        }
        if (!isValidPhone(((EditText) findViewById(R.id.phone)).getText())) {
            showToast(getString(R.string.phoneValidation));
            return;
        }
        if (!isValidEmail(((EditText)findViewById(R.id.email)).getText())) {
            showToast(getString(R.string.emailValidation));
            return;
        }
        ArrayList<String> data = new ArrayList<String>();
        data.add(0,((EditText) findViewById(R.id.name)).getText().toString());
        data.add(1,((EditText) findViewById(R.id.address)).getText().toString());
        data.add(2,((EditText) findViewById(R.id.phone)).getText().toString());
        data.add(2,((EditText) findViewById(R.id.email)).getText().toString());
        data.add(3,((EditText) findViewById(R.id.datum)).getText().toString());
        Boolean breakfast = ((CheckBox) findViewById(R.id.breakfast)).isChecked();
        int days = ((Spinner) findViewById(R.id.days)).getSelectedItemPosition();
        ResultsFragment formFragment = ResultsFragment.newInstance(data,breakfast,days);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, formFragment).addToBackStack(null)
                    .commit();


    }



    @Override
    public void resetButton(){
        loopLayoutReset((LinearLayout) findViewById(R.id.layout));
        Spinner spinner = (Spinner) findViewById(R.id.days);
        spinner.setSelection(0);
        ((CheckBox) findViewById(R.id.breakfast)).setChecked(false);
    }

    @Override
    public void toast(String toast) {
        showToast(toast);
    }
}
