package com.shoppy.silentmodetoggle;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

        setContentView(R.layout.activity_main);

        FrameLayout contentView = (FrameLayout)findViewById(R.id.content);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RingerHelper.performToggle(audioManager);
                updateUI();
            }
        });
    }

   private void updateUI(){
       ImageView icon = (ImageView)findViewById(R.id.phone_icon);
       int phoneImage = RingerHelper.isPhoneSilent(audioManager)
               ? R.drawable.silent
               : R.drawable.loud;
       icon.setImageResource(phoneImage);

       TextView statusText = (TextView)findViewById(R.id.statusText);
       int ringerStatus = RingerHelper.isPhoneSilent(audioManager)
               ? R.string.silent
               : R.string.loud;
       statusText.setText(ringerStatus);

   }

    @Override
    protected void onResume() {
        super.onResume();
// Update our UI in case anything has changed.
        updateUI();
    }
}
