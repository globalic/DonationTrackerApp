package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import rosieblair.donationtracker.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username; //username input
    private EditText password; //password input
    private TextView invalid_login; //text notification for bad login attempt
    private Button loginButton; //login button
    private Button cancelButton; //cancel button
//    private int equalsTracker;
//    public static int userIndex; //to keep track of user when after they login


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login();
        cancel();
    }

    private void login() {
        username = findViewById(R.id.enterUsernameLogin);
        password = findViewById(R.id.enterPasswordLogin);
        invalid_login = findViewById(R.id.invalidLoginAttempt);

        loginButton = findViewById(R.id.logButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sos", "loginclick");
                Intent intent = new Intent(getApplicationContext(), AppScreen.class);
                startActivity(intent);
            }
        });
    }

//    private void login() {
//        Log.d("Edit", "attempt login");
//
//        /*
//        TODO
//         - get/validate user inputs for login
//         - get user from username/pass inputs
//         - check if loc employee: (yes) -> locn emp appscreen
//         - else, go to appscreen
//        */
//
//        Intent intent = new Intent("AppScreen");
//        startActivity(intent);
//
//    }


        //a login button click will check user's inputs for validity
//        loginButton = (Button) findViewById(R.id.loginButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                currentUserDatabase();
//                if (!validate(username.getText().toString(), password.getText().toString())) {
//                    invalid_login.setVisibility(View.VISIBLE); //notify user of bad attempt
//                } else { //successful attempt will direct user to application
//                    if (userTypeValidate()) { //if user is a location employee
//                        Intent intent = new Intent("edu.gatech.micheyang.pbjdonationtracker.EmployeeAppScreen");
//                        startActivity(intent);
//                    } else {
//                        Intent intent = new Intent("edu.gatech.micheyang.pbjdonationtracker.AppScreen");
//                        startActivity(intent);
//                    }
//                }
//            }
//        });



    private void cancel() {
        Log.d("Edit", "cancel login");
        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                resetFields();
//                Intent intent = new Intent("MainActivity");
////                startActivity(intent);
                finish();
            }
        });
    }

    private void resetFields() {
        username.setText(null);
        password.setText(null);
//        invalid_login.setVisibility(View.INVISIBLE);
    }

//        /**
//         * Method that determines if user login attempt is successful.
//         * If empty database, null inputs, or invalid info then attempt is unsuccessful.
//         * @param _username the user's inputted username
//         * @param _password the user's inputted password
//         * @return true if valid login information, false for null/invalid info
//         */
//        private boolean validate(String _username, String _password) {
//            if (UserDatabase.usernames.size() == 0) { return false; }
//            else if (_username == null || _password == null) { return false; }
//            for (int i = 0; i < UserDatabase.usernames.size(); i++) {
//                if (UserDatabase.usernames.get(i).equals(_username)) {
//                    equalsTracker = i;
//                    userIndex = i;
//                    return UserDatabase.passwords.get(i).equals(_password);
//                }
//            }
//            return false;
//        }
//
//        /**
//         * Method that determines if user type is a location employee
//         * @return true if is a location employee, false if not
//         */
//        private boolean userTypeValidate() {
//            return (UserDatabase.types.get(equalsTracker).equals("Location Employee"));
//        }
//
//        /**
//         * Updates our Log with current database information (debug & message tracking purposes).
//         * Iterates through each list from database and stores entries w/ associated tags.
//         */
//        private void currentUserDatabase() {
//            for (int i = 0; i < UserDatabase.usernames.size(); i++) {
//                Log.d("USER", "Username: " + UserDatabase.usernames.get(i));
//                Log.d("PASS", "Password: " + UserDatabase.passwords.get(i));
//                Log.d("MAIL", "Email: " + UserDatabase.emails.get(i));
//                Log.d("TYPE", "Type: " + UserDatabase.types.get(i));
//            }
//        }
//    }

}
