package com.example.videocall2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userIdEditText;
    TextView heyUserTextView;
    ZegoSendCallInvitationButton voiceCallBtn, videoCallBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userIdEditText=findViewById(R.id.user_id_edit_text);
        heyUserTextView=findViewById(R.id.hey_user_text_view);
        voiceCallBtn=findViewById(R.id.voice_call_btn);
        videoCallBtn=findViewById(R.id.video_call_btn);

        String userId=getIntent().getStringExtra("userId");
        heyUserTextView.setText("Hey "+userId);

        userIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String targetUserId=userIdEditText.getText().toString().trim();
            setVoiceCall(targetUserId);
            setVideoCall(targetUserId);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    void setVoiceCall(String targetUserId){
       voiceCallBtn.setIsVideoCall(false);
        voiceCallBtn.setResourceID("zego_uikit_call");
        voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserId)));

    }
    void setVideoCall(String targetUserId){
      videoCallBtn.setIsVideoCall(true);
        videoCallBtn.setResourceID("zego_uikit_call");
        videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserId)));

    }


}