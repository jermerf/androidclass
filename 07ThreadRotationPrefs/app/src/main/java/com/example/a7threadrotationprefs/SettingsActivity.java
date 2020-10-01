package com.example.a7threadrotationprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.a7threadrotationprefs.config.AppSettings;

public class SettingsActivity extends AppCompatActivity {
    private SeekBar seekFluffiness;
    private TextView txtFluffiness;
    private EditText txtRole;
    private RadioGroup radStatus;
    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        seekFluffiness = findViewById(R.id.seekFluffiness);
        txtFluffiness = findViewById(R.id.txtFluffiness);
        txtRole = findViewById(R.id.txtRole);
        radStatus = findViewById(R.id.radStatus);

        seekFluffiness.setOnSeekBarChangeListener(seekChanged);

        settings = new AppSettings(this);

        seekFluffiness.setProgress(settings.getFluffiness());
        txtRole.setText(settings.getRole());
        int radId = idForStatus(settings.getStatus());
        RadioButton radSelected = findViewById(radId);
        radSelected.setChecked(true);
    }

    @Override
    protected void onStop() {
        settings.saveSettings(
                seekFluffiness.getProgress(),
                txtRole.getText().toString(),
                statusForId(radStatus.getCheckedRadioButtonId()));

        super.onStop();
    }

    private AppSettings.Status statusForId(int id) {
        switch (id){
            case R.id.radActive: return AppSettings.Status.ACTIVE;
            case R.id.radDisabled: return AppSettings.Status.DISABLED;
            case R.id.radDisallowed: return AppSettings.Status.DISALLOWED;
        }
        return AppSettings.Status.DISABLED;
    }

    private int idForStatus(AppSettings.Status status) {
        switch (status){
            case ACTIVE: return R.id.radActive;
            case DISABLED: return R.id.radDisabled;
            case DISALLOWED: return R.id.radDisallowed;
        }
        return R.id.radDisabled;
    }

    SeekBar.OnSeekBarChangeListener seekChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            txtFluffiness.setText(progress + " %");
        }
        @Override public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    public interface SettingsFluffAccessor {

    }
}