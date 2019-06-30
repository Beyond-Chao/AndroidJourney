package com.example.a11_broadcasttest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;

    private EditText passwordEdit;

    private CheckBox rememberPass;

    private Button loginButton;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initSubviews();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = preferences.getBoolean("remember_password", false);
        if (isRemember) {
            String account = preferences.getString("account", "");
            String password = preferences.getString("password", "");

            accountEdit.setText(account);

            passwordEdit.setText(password);

            rememberPass.setChecked(true);
        }
    }

    private void initSubviews() {
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        rememberPass = findViewById(R.id.remember_pass);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                createNotification();

                if (account.equals("admin") && password.equals("123456")) {

                    editAccountPreferences(account, password);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Account or Password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelName = "CHANNEL_NAME";
        String channelDesc = "CHANNEL_DESC";
        String channelId = "com.example.broadcattest";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = channelName;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(channelDesc);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        builder.setTicker("sample notification");
        builder.setContentTitle("标题");
        builder.setContentText("Much longer text that cannot fit one line...");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification notification = builder.build();

        manager.notify(1, notification);

    }


    private void editAccountPreferences(String account, String password) {
        editor = preferences.edit();

        if (rememberPass.isChecked()) {
            editor.putBoolean("remember_password", true);
            editor.putString("account", account);
            editor.putString("password", password);
        } else {
            editor.clear();
        }

        editor.apply();
    }
}
