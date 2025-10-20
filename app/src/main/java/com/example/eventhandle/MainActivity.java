package com.example.eventhandle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tvMsg;
    private Button btClick;
    private int counter = 0;
    private View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            counter++;
            tvMsg.setText("You have hit me " + counter + " times!");
            /// continue
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("mynumber", "10");
            startActivity(intent);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        tvMsg = findViewById(R.id.tvMsg);
        btClick = findViewById(R.id.btClick);
        btClick.setOnClickListener(onclick);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mynumber", tvMsg.getText().toString());
        outState.putInt("counter_value", counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvMsg.setText(savedInstanceState.getString("mynumber"));
        counter = savedInstanceState.getInt("counter_value");
    }

}