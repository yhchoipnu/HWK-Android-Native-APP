package com.example.s3labbahk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EmailActivity extends AppCompatActivity {
    private static final int PICK_FROM_GALLERY = 101;
    private EditText mEditTextToAddr;
    private EditText mEditTextSubject;
    TextView Tecket_Attach0, Tecket_Attach1, Tecket_Attach2, Tecket_Attach3;
    private EditText mEditTextMsg;
    ArrayList<Uri> uris = new ArrayList<Uri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Button btnBack_ = findViewById(R.id.btnBack);
        btnBack_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 클릭 이벤트 처리
                finish(); // 현재 엑티비티(즉, EmailActivity) 종료
            }
        });

        mEditTextToAddr = findViewById(R.id.edit_text_toAddr);
        mEditTextSubject = findViewById(R.id.edit_text_subject);

        Button btnAttach = findViewById(R.id.btn_attachment);
        btnAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
        Tecket_Attach0 = findViewById(R.id.feedback_attach0);
        Tecket_Attach1 = findViewById(R.id.feedback_attach1);
        Tecket_Attach2 = findViewById(R.id.feedback_attach2);
        Tecket_Attach3 = findViewById(R.id.feedback_attach3);

        mEditTextMsg = findViewById(R.id.edit_text_msg);

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String recipientList = mEditTextToAddr.getText().toString();
        String[] recipients = recipientList.split(","); // A@daum.net, , , ,

        String subj = mEditTextSubject.getText().toString();
        String msg = mEditTextMsg.getText().toString();

        Intent intentSend = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intentSend.putExtra(Intent.EXTRA_EMAIL, recipients);
        intentSend.putExtra(Intent.EXTRA_SUBJECT, subj);
        if (uris.size() > 0) {
            intentSend.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        }
        intentSend.putExtra(Intent.EXTRA_TEXT, msg);

        intentSend.setType("message/rfc822");
        startActivity(Intent.createChooser(intentSend, "Choose an email client program."));
    }

    public void openFolder() {
        Intent intentFolder = new Intent();
        intentFolder.setType("*/*");
        //intentFolder.setType("image/*");
        //intentFolder.setType("file/*");
        intentFolder.setAction(Intent.ACTION_GET_CONTENT);
        intentFolder.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intentFolder, "Complete action using"), PICK_FROM_GALLERY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            Uri URI = data.getData();
            uris.add(URI);
            for (int i=0; i<uris.size(); i++) {
                Uri getURI = uris.get(i);
                if (i==0) {
                    Tecket_Attach0.setText(getURI.getLastPathSegment());
                    Button btn_remove0_ = findViewById(R.id.btn_remove0);
                    //btn_remove0_.setVisibility(View.VISIBLE);
                } else if (i==1) {
                    Tecket_Attach1.setText(getURI.getLastPathSegment());
                    Button btn_remove1_ = findViewById(R.id.btn_remove1);
                    //btn_remove1_.setVisibility(View.VISIBLE);
                } else if (i==2) {
                    Tecket_Attach2.setText(getURI.getLastPathSegment());
                    Button btn_remove2_ = findViewById(R.id.btn_remove2);
                    //btn_remove2_.setVisibility(View.VISIBLE);
                } else if (i==3) {
                    Tecket_Attach3.setText(getURI.getLastPathSegment());
                    Button btn_remove3_ = findViewById(R.id.btn_remove3);
                    //btn_remove3_.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}