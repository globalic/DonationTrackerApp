package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import rosieblair.donationtracker.R;
import rosieblair.donationtracker.database.UserDBHelper;
import rosieblair.donationtracker.model.User;

/**
 * Class to allow functionality of user login system
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;
    private UserDBHelper dbhelper;

    private EditText inputUsername;
    private EditText inputPassword;
    private TextView failedLogin;
//    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    /**
     * Initializes the views, objects, and listeners.
     */
    private void init() {
        dbhelper = new UserDBHelper(activity);
//        user = new User();

        inputUsername = findViewById(R.id.enterUsernameLogin);
        inputPassword = findViewById(R.id.enterPasswordLogin);

        failedLogin = findViewById(R.id.invalidLoginAttempt);

        Button buttonLogin = findViewById(R.id.logButton);
        Button buttonCancel = findViewById(R.id.cancelButton);
        buttonLogin.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    /**
     * Button click handler.
     * If cancel button clicked, finish activity (restarts back at the main screen).
     * If login button clicked, call validate method to determine next set of actions.
     * @param view the selected view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancelButton:
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                resetFields();
//                startActivity(intent);
                finish();
                break;
            case R.id.logButton:
                validate();
                break;
        }
    }

    /**
     * Checks user's login credentials for:
     *   - Existence of username/password combination in database
     *   - User's type to determine what app screen they will be directed to
     *   - If invalid values entered, will notify this user of login failure
     */
    private void validate() {
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();


        if (dbhelper.checkUserPass(username, password)) {
            if (dbhelper.checkIfEmployee(username)) {
                List<User> user_list = dbhelper.userList();
                int location_key = -1;
                for (User u : user_list) {
                    if (u.getUsername().equals(username)) {
                        location_key = u.getEmpId();
                    }
                }
                if (location_key != -1) {
                    Log.d("login", "this user's empId = " + location_key + "");
                    Intent intent = new Intent(getApplicationContext(), EmployeeAppScreen.class);
                    intent.putExtra("locKey", location_key);
                    startActivity(intent);
                }
            } else {
                Log.d("login", "not employee");
                Intent intent = new Intent(getApplicationContext(), AppScreen.class);
                resetFields();
                startActivity(intent);
            }
        } else {
            Log.d("login", "user entered invalid login credentials");
            failedLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Clears the login/password input text boxes.
     */
    private void resetFields() {
        inputUsername.setText(null);
        inputPassword.setText(null);
    }

}
    //        if (dbhelper.checkUserPass(username, password)) {
//            for (User u : dbhelper.userList()) {
//                if (u.getUsername().equals(username)) user = u;
//            }
//
//            if (dbhelper.getUserType(username) != null
//                    && dbhelper.getUserType(username).equals("EMPLOYEE")) {
//                Intent intent = new Intent(getApplicationContext(), EmployeeAppScreen.class);
//                intent.putExtra("thisUser", user);
//                startActivity(intent);
//            } else if (dbhelper.getUserType(username) != null
//                    && dbhelper.getUserType(username).equals("MANAGER")) {
//                Intent intent = new Intent(getApplicationContext(), AppScreen.class);
//                startActivity(intent);
//            } else if (dbhelper.getUserType(username) != null
//                    && dbhelper.getUserType(username).equals("ADMIN")) {
//                Intent intent = new Intent(getApplicationContext(), AppScreen.class);
//                startActivity(intent);
//            } else {
//                Intent intent2 = new Intent(getApplicationContext(), AppScreen.class);
//                startActivity(intent2);
//            }
//        } else {
//            failedLogin.setVisibility(View.VISIBLE);
//        }

//    private EditText username; //username input
//    private EditText password; //password input
//    private TextView invalid_login; //text notification for bad login attempt
//    private Button loginButton; //login button
//    private Button cancelButton; //cancel button
////    private int equalsTracker;
////    public static int userIndex; //to keep track of user when after they login
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        login();
//        cancel();
//    }
//
//    private void login() {
//        username = findViewById(R.id.enterUsernameLogin);
//        password = findViewById(R.id.enterPasswordLogin);
//        invalid_login = findViewById(R.id.invalidLoginAttempt);
//
//        loginButton = findViewById(R.id.logButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("sos", "loginclick");
//                Intent intent = new Intent(getApplicationContext(), AppScreen.class);
//                startActivity(intent);
//            }
//        });
//    }
//
////    private void login() {
////        Log.d("Edit", "attempt login");
////
////        /*
////        TO - DO
////         - get/validate user inputs for login
////         - get user from username/pass inputs
////         - check if loc employee: (yes) -> locn emp appscreen
////         - else, go to appscreen
////        */
////
////        Intent intent = new Intent("AppScreen");
////        startActivity(intent);
////
////    }
//
//
//        //a login button click will check user's inputs for validity
////        loginButton = (Button) findViewById(R.id.loginButton);
////        loginButton.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View view) {
////                currentUserDatabase();
////                if (!validate(username.getText().toString(), password.getText().toString())) {
////                    invalid_login.setVisibility(View.VISIBLE); //notify user of bad attempt
////                } else { //successful attempt will direct user to application
////                    if (userTypeValidate()) { //if user is a location employee
////                        Intent intent = new Intent("edu.gatech.micheyang.pbjdonationtracker.EmployeeAppScreen");
////                        startActivity(intent);
////                    } else {
////                        Intent intent = new Intent("edu.gatech.micheyang.pbjdonationtracker.AppScreen");
////                        startActivity(intent);
////                    }
////                }
////            }
////        });
//
//
//
//    private void cancel() {
//        Log.d("Edit", "cancel login");
//        cancelButton = findViewById(R.id.cancelButton);
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                resetFields();
////                Intent intent = new Intent("MainActivity");
//////                startActivity(intent);
//                finish();
//            }
//        });
//    }
//
//    private void resetFields() {
//        username.setText(null);
//        password.setText(null);
////        invalid_login.setVisibility(View.INVISIBLE);
//    }

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


