package com.example.jodrod;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txtWelcome);

        // ปรับ Padding รองรับ Edge-to-Edge
        if (findViewById(R.id.main) != null) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

            auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();

            textView.setText("Wellcome : "+ (user != null ? user.getEmail() : null));

        // ผูกเมนูด้านล่าง BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        if (bottomNav != null) {
            bottomNav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Toast.makeText(this, "หน้าแรก", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_my_car) {
                    Toast.makeText(this, "รถของฉัน", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_repair_log) {
                    Toast.makeText(this, "บันทึกซ่อม", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.nav_profile) {
                    Toast.makeText(this, "โปรไฟล์", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
        }
    }
}