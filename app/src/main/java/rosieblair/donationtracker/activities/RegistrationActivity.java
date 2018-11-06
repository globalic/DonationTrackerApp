package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import rosieblair.donationtracker.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText username; //username input box
    private EditText email; //email input box
    private EditText password; //password input box
    private Spinner type; //user type dropdown spinner
    private TextView invalid_attempt; //text notification for bad attempt
    private Button regButton; //register button
    private Button cancelButton; //cancel button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        register();
        cancel();
    }

    private void init() {
        username =  findViewById(R.id.regNameEntry);
        email =  findViewById(R.id.regEmailEntry);
        password =  findViewById(R.id.regPassEntry);
        type =  findViewById(R.id.regTypeDropDown);
        invalid_attempt = findViewById(R.id.invalidAttempt);


//        regButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if (!validate(username.getText().toString(),
////                        password.getText().toString(), email.getText().toString())) {
////                    invalid_attempt.setVisibility(View.VISIBLE);
////                } else { //registration success, add user's info into database lists
////                    UserDatabase.usernames.add(username.getText().toString());
////                    UserDatabase.passwords.add(password.getText().toString());
////                    UserDatabase.emails.add(email.getText().toString());
////                    UserDatabase.types.add(type.getSelectedItem().toString());
////                    UserDatabase.location.add(0);
////                    if (type.getSelectedItem().toString().equals("Location Employee")) {
////                        Log.d("Reg", "location employee register");
////                        Intent intent = new Intent("edu.gatech.micheyang.pbjdonationtracker.LocationEmployee");
////                        startActivity(intent);
////                    } else {
////                        Intent intent = new Intent("edu.gatech.micheyang.pbjdonationtracker.activities.UserLogin");
////                        startActivity(intent);
////                    }
////                }
//                Log.d("ONCLICK", "regbutton");
//                register();
//            }
//        });

    }

    private void register() {
        regButton = findViewById(R.id.regRegButton);
        Log.d("Edit", "attempt register");
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ONCLICK", "regbutton");
//                String typeText = type.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //add user after validating
//        String userText = username.getText().toString();
//        String passText = password.getText().toString();
//        String emailText = email.getText().toString();
//        String typeText = type.getSelectedItem().toString();
//        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(intent);
    }

    private void cancel() {
        Log.d("Edit", "cancel register");
        cancelButton = findViewById(R.id.regCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                resetFields();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }

    private void resetFields() {
        username.setText(null);
        email.setText(null);
        password.setText(null);
//        type.setSelection(0);
    }

}
