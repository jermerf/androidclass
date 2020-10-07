package com.example.a8sqlitelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a8sqlitelist.adapters.CatPostAdapter;
import com.example.a8sqlitelist.adapters.IconAdapter;
import com.example.a8sqlitelist.model.CatPost;
import com.example.a8sqlitelist.model.IconResource;
import com.example.a8sqlitelist.sqlite.CatPostTable;
import com.example.a8sqlitelist.sqlite.SQLiteManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private SQLiteManager sqLiteManager;
    private EditText txtTitle;
    private EditText txtContent;
    private Spinner spinIcon;
    private ListView listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinIcon = findViewById(R.id.spinIcon);
        txtTitle = findViewById(R.id.txtItemTitle);
        txtContent = findViewById(R.id.txtItemContent);
        listItems = findViewById(R.id.listItems);

        spinIcon.setAdapter(new IconAdapter(this));

        sqLiteManager = new SQLiteManager(this);

        updateList();
    }

    private void updateList(){
        List<CatPost> catPosts = CatPostTable.getCatPosts(sqLiteManager.getReadableDatabase());
        listItems.setAdapter(new CatPostAdapter(this, catPosts));
    }

    public void addClicked(View v){
        IconResource icon = IconResource.values()[spinIcon.getSelectedItemPosition()];
        CatPost post = new CatPost(txtTitle.getText(), txtContent.getText(), icon.toString());
        CatPostTable.insert(sqLiteManager.getWritableDatabase(), post);

        txtTitle.setText("");
        txtContent.setText("");
        updateList();

        Animation bounce = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        v.startAnimation(bounce);
    }

    public void animateThisButton(View v) {
        Thread t = new Thread(() -> {
            for(long counter = 0; counter < 300000000; counter++){
                if(counter % 100000000 == 0) {
                    Log.e(TAG, "Counter at " + counter);
                }
            }
            runOnUiThread(() -> {
                Toast.makeText(this, "Done count", Toast.LENGTH_SHORT).show();
            });

        });


        t.start();
        Animation bounce = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        v.startAnimation(bounce);
        v.start
    }


}