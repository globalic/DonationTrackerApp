package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.UserDBHelper;
import rosieblair.donationtracker.model.User;

/**
 * Class to facilitate user registration
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegistrationActivity.this;
    private UserDBHelper dbhelper;
    private User user;


    private EditText username; //username input box
    private EditText email; //email input box
    private EditText password; //password input box
    private Spinner typeDropDown; //user type dropdown spinner
    private TextView invalid_attempt; //text notification for bad attempt
    private Button regButton; //register button
    private Button cancelButton; //cancel button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        listeners();
        spinner();
        objects();
    }

    private void init() {
        username = findViewById(R.id.regNameEntry);
        password = findViewById(R.id.regPassEntry);
        email = findViewById(R.id.regEmailEntry);
        invalid_attempt = findViewById(R.id.invalidAttempt);
        typeDropDown = findViewById(R.id.regTypeDropDown);
        regButton = findViewById(R.id.regRegButton);
        cancelButton = findViewById(R.id.regCancelButton);
    }

    private void listeners() {
        regButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    private void spinner() {
        int index = typeDropDown.getSelectedItemPosition();
        Resources res = getResources();
        String[] strTypes = res.getStringArray(R.array.acctTypes);
        String type = User.types.get(index);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.regCancelButton:
                Log.d("Edit", "cancel register");
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                finish();
                break;
            case R.id.regRegButton:
                register();
                break;
        }
    }


    private void objects() {
        dbhelper = new UserDBHelper(activity);
        user = new User();
    }

    private void register() {
        Log.d("Edit", "attempt register");
        String usernm = username.getText().toString().trim();
        String eml = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String type = User.findTypeByString(typeDropDown.getSelectedItem().toString());

        if (!validate(usernm, eml)) {
            addtoDB(usernm, eml, pass, type);
            if (user == null) {
                invalid_attempt.setVisibility(View.VISIBLE);
                Log.d("ERROR", "registration");
                finish();
            } else {
                if (!(user.getType().equalsIgnoreCase("EMPLOYEE"))) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(getBaseContext(), "Registration successful!",
                            Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    //setting the color of notification's background bubble
                    toastView.getBackground().setColorFilter(Color.parseColor("#daeff1"),
                            PorterDuff.Mode.SRC);
                    toast.show();
                } else {
                    Intent intent = new Intent(RegistrationActivity.this, LocationEmployee.class);
                    intent.putExtra("empUsername", user.getUsername());
                    startActivity(intent);
                }
            }
        }
    }

    private boolean validate(String usernm, String eml) {
        return (dbhelper.checkUsername(usernm) || dbhelper.checkEmail(eml));
    }

    private void addtoDB(String usernm, String eml, String pass, String acctType) {
        if (usernm == null || eml == null || pass == null) {
            Log.d("Database", "failure: null user data");
            return;
        }
        user.setUsername(usernm);
        user.setEmail(eml);
        user.setPassword(pass);
        user.setLock(false);
        user.setType(acctType.toUpperCase());
        user.setEmpId(-1);
        dbhelper.addUser(user);
        Log.d("Database", "success: added user");
    }

    private void resetFields() {
        username.setText(null);
        email.setText(null);
        password.setText(null);
    }
}
