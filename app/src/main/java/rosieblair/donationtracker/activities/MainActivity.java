package rosieblair.donationtracker.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import rosieblair.donationtracker.R;

public class MainActivity extends AppCompatActivity {

    private Button logButton;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logButton = findViewById(R.id.loginButton);
        regButton = findViewById(R.id.regButton);
        pressLogin();
        pressRegister();
    }

    /**
     * Method to handle a login-button press, directs the user to Login screen.
     */
    public void pressLogin() {
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    /**
     * Method to handle a register-button press, directs user to Registration screen.
     */
    public void pressRegister() {
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void login() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void register() {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }
}
