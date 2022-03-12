package com.example.moodlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.moodlevel.dao.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private DBHelper dbHelper;

    private TextView status;
    private TextView showLevel;
    private SeekBar level;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        status = (TextView) findViewById(R.id.status);
        showLevel = (TextView) findViewById(R.id.showLevel);
        level = (SeekBar) findViewById(R.id.level);
        level.setOnSeekBarChangeListener(this);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Date dateNow = new Date();
        @SuppressLint("SimpleDateFormat") String date = (new SimpleDateFormat("yyyy-MM-dd")).format(dateNow);
        @SuppressLint("SimpleDateFormat") String time = (new SimpleDateFormat("HH:mm:ss")).format(dateNow);

        ContentValues cv = new ContentValues();
        cv.put("date", date);
        cv.put("time", time);
        cv.put("moodLevel", level.getProgress());
        db.insert("mood", null, cv);

        db.close();

        level.setProgress(1);
        showLevel.setText("1");
        status.setText("OK");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        showLevel.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}