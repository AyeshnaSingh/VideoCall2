package com.example.videocall2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    EditText userIdEditText;
    Button startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIdEditText=findViewById(R.id.user_id_edit_text);
        startBtn=findViewById(R.id.start_btn);
        startBtn.setOnClickListener((v)->{
            String userId=userIdEditText.getText().toString().trim();
            if(userId.isEmpty()){
                return;
            }
            startService(userId);
            Intent intent=new Intent(MainActivity.this, CallActivity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
        });

    }

    void startService(String userId){
        Application application = getApplication();
        long appID =1588845656 ;
        String appSign ="1d1b48b440d039bfdabe6cbdbf4b88c4a4afff3a70ac889264905ab329cbbd5d";  // yourAppSign

        String userName =userId;

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userId, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}