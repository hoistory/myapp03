package com.example.hoistory.myapp03;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private EditText toToastText;
    private Button clearButton;
    private Button toastButton;

    private ButtonOnClickListener btnListen;
    private ButtonHintOnLongClick btnHinlisten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toToastText =(EditText)findViewById(R.id.editText);
        toToastText.setOnClickListener(new TextOnClickListener());
        clearButton = (Button)findViewById(R.id.btnClear);
        toastButton = (Button)findViewById(R.id.btnToast);
    }

    private class TextOnClickListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {


            btnListen = new ButtonOnClickListener();
            btnHinlisten = new ButtonHintOnLongClick();

            clearButton.setOnClickListener(btnListen);
            toastButton.setOnClickListener(btnListen);

            clearButton.setOnLongClickListener(btnHinlisten);
            toastButton.setOnLongClickListener(btnHinlisten);

        }
    }

    public class ButtonHintOnLongClick implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            String hint =null;
            Context context = v.getContext();

            if(v.getId()== R.id.btnClear){
                hint = "This button will clear the text ";
            }else if(v.getId() == R.id.btnToast){
                hint = "This button will Toast the text";
            }
            Toast.makeText(context,hint,Toast.LENGTH_SHORT).show();
            return true;

        }
    }

    public class ButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
                if(v.getId()==R.id.btnClear){
                    toToastText.setText("");
                }else if(v.getId() == R.id.btnToast){
                    Toast.makeText(v.getContext(),toToastText.getText().toString(),
                            Toast.LENGTH_LONG).show();
                }
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
