package com.example.s3labbahk;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.opencsv.CSVWriter;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class EstimateActivity extends AppCompatActivity {
    protected XmlPullParser xpp;

    protected RadioButton rb_area = null;
    protected RadioButton rb_est = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);

        TextView jobNo_ = findViewById(R.id.jobNo);
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MMdd-HHmm");
        jobNo_.setText(" HWK-" + dateFormat.format(System.currentTimeMillis()));

        /*
        Button btnBack_ = findViewById(R.id.btnBack);
        btnBack_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 클릭 이벤트 처리
                finish();
            }
        });
        */
    }

    public void onExcelSave(View v) throws IOException { saveExcel(); }
    private void saveExcel() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MMdd-HHmm");
        String fileName = "estimate_" + dateFormat.format(System.currentTimeMillis()) + ".csv"; // .csv
        String filePath = getExternalFilesDir(null) + File.separator + fileName;
        File xlsFile = new File(getExternalFilesDir(null), fileName);
        FileWriter mFileWriter = null;
        CSVWriter writer;

        // File exist
        if(xlsFile.exists() && !xlsFile.isDirectory()) {
            mFileWriter = new FileWriter(filePath, true);
            writer = new CSVWriter(new FileWriterWithEncoding(filePath, "euc-kr"));
        } else { writer = new CSVWriter(new FileWriterWithEncoding(filePath, "euc-kr")); }

        String[] dataRow = {"", "", ""};
        writer.writeNext(dataRow);

        TextView tv_API_EM0_ = findViewById(R.id.tv_API_EM0);
        EditText edit_res_vsslNm_ = findViewById(R.id.edit_res_vsslNm);
        String[] dataRow0 = {"1", tv_API_EM0_.getText().toString(), edit_res_vsslNm_.getText().toString()};
        writer.writeNext(dataRow0);

        TextView tv_API_EM1_ = findViewById(R.id.tv_API_EM1);
        EditText edit_res_imoNumb_ = findViewById(R.id.edit_res_imoNumb);
        String[] dataRow1 = {"2", tv_API_EM1_.getText().toString(), edit_res_imoNumb_.getText().toString()};
        writer.writeNext(dataRow1);

        TextView tv_API_EM2_ = findViewById(R.id.tv_API_EM2);
        Spinner spinner_res_vsslType_ = findViewById(R.id.spinner_res_vsslType);
        String[] dataRow2 = {"3", tv_API_EM2_.getText().toString(), spinner_res_vsslType_.getSelectedItem().toString()};
        writer.writeNext(dataRow2);

        TextView tv_API_EM3_ = findViewById(R.id.tv_API_EM3);
        EditText edit_res_LBD_ = findViewById(R.id.edit_res_LBD);
        String[] dataRow3 = {"4", tv_API_EM3_.getText().toString(), edit_res_LBD_.getText().toString()};
        writer.writeNext(dataRow3);

        TextView tv_API_EM4_ = findViewById(R.id.tv_API_EM4);
        Spinner spinner_res_currSpeed_ = findViewById(R.id.spinner_res_currSpeed);
        String[] dataRow4 = {"5", tv_API_EM4_.getText().toString(), spinner_res_currSpeed_.getSelectedItem().toString()};
        writer.writeNext(dataRow4);

        TextView tv_API_EM5_ = findViewById(R.id.tv_API_EM5);
        EditText edit_res_propeller_ = findViewById(R.id.edit_res_propeller);
        String[] dataRow5 = {"6", tv_API_EM5_.getText().toString(), edit_res_propeller_.getText().toString()};
        writer.writeNext(dataRow5);

        TextView tv_API_EM6_ = findViewById(R.id.tv_API_EM6);
        Spinner spinner_res_coatingType_ = findViewById(R.id.spinner_res_coatingType);
        String[] dataRow6 = {"7", tv_API_EM6_.getText().toString(), spinner_res_coatingType_.getSelectedItem().toString()};
        writer.writeNext(dataRow6);

        String[] dataRow7 = {"", "", ""};
        writer.writeNext(dataRow7);

        // Agent Contacts
        TextView tv_API_ = findViewById(R.id.tv_API);
        String[] dataRow8 = {"", tv_API_.getText().toString(), ""};
        writer.writeNext(dataRow8);

        TextView tv_API_M0_ = findViewById(R.id.tv_API_M0);
        EditText editM_t0_ = findViewById(R.id.editM_t0);
        String[] dataRow9 = {"1", tv_API_M0_.getText().toString(), editM_t0_.getText().toString()};
        writer.writeNext(dataRow9);

        TextView tv_API_M1_ = findViewById(R.id.tv_API_M1);
        EditText editM_t1_ = findViewById(R.id.editM_t1);
        String[] dataRow10 = {"2", tv_API_M1_.getText().toString(), editM_t1_.getText().toString()};
        writer.writeNext(dataRow10);

        TextView tv_API_M2_ = findViewById(R.id.tv_API_M2);
        EditText editM_t2_ = findViewById(R.id.editM_t2);
        String[] dataRow11 = {"3", tv_API_M2_.getText().toString(), editM_t2_.getText().toString()};
        writer.writeNext(dataRow11);

        TextView tv_API_M3_ = findViewById(R.id.tv_API_M3);
        EditText editM_t3_ = findViewById(R.id.editM_t3);
        String[] dataRow12 = {"4", tv_API_M3_.getText().toString(), editM_t3_.getText().toString()};
        writer.writeNext(dataRow12);

        TextView tv_API_M4_ = findViewById(R.id.tv_API_M4);
        EditText editM_t4_ = findViewById(R.id.editM_t4);
        String[] dataRow13 = {"5", tv_API_M4_.getText().toString(), editM_t4_.getText().toString()};
        writer.writeNext(dataRow13);

        TextView tv_API_M5_ = findViewById(R.id.tv_API_M5);
        EditText editM_t5_ = findViewById(R.id.editM_t5);
        String[] dataRow14 = {"6", tv_API_M5_.getText().toString(), editM_t5_.getText().toString()};
        writer.writeNext(dataRow14);

        TextView tv_API_M6_ = findViewById(R.id.tv_API_M6);
        EditText editM_t6_ = findViewById(R.id.editM_t6);
        String[] dataRow15 = {"7", tv_API_M6_.getText().toString(), editM_t6_.getText().toString()};
        writer.writeNext(dataRow15);
        // Agent Contacts

        String[] dataRow16 = {"", "", ""};
        writer.writeNext(dataRow16);

        // Customer Details
        TextView tv_CD_ = findViewById(R.id.tv_CD);
        String[] dataRow17 = {"", tv_CD_.getText().toString(), ""};
        writer.writeNext(dataRow17);

        TextView tv_CD0_ = findViewById(R.id.tv_CD0);
        EditText editCD0_ = findViewById(R.id.editCD0);
        String[] dataRow18 = {"1", tv_CD0_.getText().toString(), editM_t0_.getText().toString()};
        writer.writeNext(dataRow18);

        TextView tv_CD1_ = findViewById(R.id.tv_CD1);
        EditText editCD1_ = findViewById(R.id.editCD1);
        String[] dataRow19 = {"2", tv_CD1_.getText().toString(), editM_t1_.getText().toString()};
        writer.writeNext(dataRow19);

        TextView tv_CD2_ = findViewById(R.id.tv_CD2);
        EditText editCD2_ = findViewById(R.id.editCD2);
        String[] dataRow20 = {"3", tv_CD2_.getText().toString(), editM_t2_.getText().toString()};
        writer.writeNext(dataRow20);

        TextView tv_CD3_ = findViewById(R.id.tv_CD3);
        EditText editCD3_ = findViewById(R.id.editCD3);
        String[] dataRow21 = {"4", tv_CD3_.getText().toString(), editM_t3_.getText().toString()};
        writer.writeNext(dataRow21);

        TextView tv_CD4_ = findViewById(R.id.tv_CD4);
        EditText editCD4_ = findViewById(R.id.editCD4);
        String[] dataRow22 = {"5", tv_CD4_.getText().toString(), editM_t4_.getText().toString()};
        writer.writeNext(dataRow22);
        // Customer Details


        String[] dataRow23 = {"", "", ""};
        writer.writeNext(dataRow23);


        // Planning Meeting
        TextView tv_PM_ = findViewById(R.id.tv_PM);
        String[] dataRow24 = {"", tv_PM_.getText().toString(), ""};
        writer.writeNext(dataRow24);

        TextView tv_PM0_ = findViewById(R.id.tv_PM0);
        EditText editPM0_ = findViewById(R.id.editPM0);
        String[] dataRow25 = {"1", tv_PM0_.getText().toString(), editPM0_.getText().toString()};
        writer.writeNext(dataRow25);

        TextView tv_PM1_ = findViewById(R.id.tv_PM1);
        EditText editPM1_ = findViewById(R.id.editPM1);
        String[] dataRow26 = {"2", tv_PM1_.getText().toString(), editPM1_.getText().toString()};
        writer.writeNext(dataRow26);

        TextView tv_PM2_ = findViewById(R.id.tv_PM2);
        EditText editPM2_ = findViewById(R.id.editPM2);
        String[] dataRow27 = {"3", tv_PM2_.getText().toString(), editPM2_.getText().toString()};
        writer.writeNext(dataRow27);

        TextView tv_PM3_ = findViewById(R.id.tv_PM3);
        RadioGroup radioGroupPM0_ = findViewById(R.id.radioGroupPM0);
        radioGroupPM0_.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroupPM0_, int checkedId) {
                switch (checkedId){
                    case R.id.rb_asia:
                        rb_area = findViewById(R.id.rb_asia);
                        break;
                    case R.id.rb_pacific:
                        rb_area = findViewById(R.id.rb_pacific);
                        break;
                }
            }
        });
        if (rb_area == null) rb_area = findViewById(R.id.rb_asia);
        String[] dataRow28 = {"4", tv_PM3_.getText().toString(), rb_area.getText().toString()};
        writer.writeNext(dataRow28);

        TextView tv_PM4_ = findViewById(R.id.tv_PM4);
        RadioGroup radioGroupPM1_ = findViewById(R.id.radioGroupPM1);
        radioGroupPM1_.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroupPM1_, int checkedId) {
                switch (checkedId){
                    case R.id.rb_esther1:
                        rb_est = findViewById(R.id.rb_esther1);
                        break;
                    case R.id.rb_esther2:
                        rb_est = findViewById(R.id.rb_esther2);
                        break;
                    case R.id.rb_esther3:
                        rb_est = findViewById(R.id.rb_esther3);
                        break;
                }
            }
        });
        if (rb_est == null) rb_est = findViewById(R.id.rb_esther1);
        String[] dataRow29 = {"5", tv_PM4_.getText().toString(), rb_est.getText().toString()};
        writer.writeNext(dataRow29);
        // Planning Meeting


        String[] dataRow30 = {"", "", ""};
        writer.writeNext(dataRow30);


        // Services
        TextView tv_serv_ = findViewById(R.id.text_view_services);
        String[] dataRow31 = {"", tv_serv_.getText().toString(), ""};
        writer.writeNext(dataRow31);

        TextView tv_sm_ = findViewById(R.id.text_view_sm);
        TextView tv_code_ = findViewById(R.id.text_view_code);
        TextView tv_desc_ = findViewById(R.id.text_view_desc);
        String[] dataRow32 = {"", tv_sm_.getText().toString(), tv_code_.getText().toString(), tv_desc_.getText().toString()};
        writer.writeNext(dataRow32);

        TextView tv_serv0_ = findViewById(R.id.tv_serv0);
        Spinner spinnerServCode0_ = findViewById(R.id.spinnerServCode0);
        TextView tv_desc0_ = findViewById(R.id.tv_desc0);
        String[] dataRow33 = {"1", tv_serv0_.getText().toString(), spinnerServCode0_.getSelectedItem().toString(), tv_desc0_.getText().toString()};
        writer.writeNext(dataRow33);

        TextView tv_serv1_ = findViewById(R.id.tv_serv1);
        Spinner spinnerServCode1_ = findViewById(R.id.spinnerServCode1);
        TextView tv_desc1_ = findViewById(R.id.tv_desc1);
        String[] dataRow34 = {"2", tv_serv1_.getText().toString(), spinnerServCode1_.getSelectedItem().toString(), tv_desc1_.getText().toString()};
        writer.writeNext(dataRow34);

        TextView tv_serv2_ = findViewById(R.id.tv_serv2);
        Spinner spinnerServCode2_ = findViewById(R.id.spinnerServCode2);
        TextView tv_desc2_ = findViewById(R.id.tv_desc2);
        String[] dataRow35 = {"3", tv_serv2_.getText().toString(), spinnerServCode2_.getSelectedItem().toString(), tv_desc2_.getText().toString()};
        writer.writeNext(dataRow35);

        TextView tv_serv3_ = findViewById(R.id.tv_serv3);
        Spinner spinnerServCode3_ = findViewById(R.id.spinnerServCode3);
        TextView tv_desc3_ = findViewById(R.id.tv_desc3);
        String[] dataRow36 = {"4", tv_serv3_.getText().toString(), spinnerServCode3_.getSelectedItem().toString(), tv_desc3_.getText().toString()};
        writer.writeNext(dataRow36);

        TextView tv_serv4_ = findViewById(R.id.tv_serv4);
        Spinner spinnerServCode4_ = findViewById(R.id.spinnerServCode4);
        TextView tv_desc4_ = findViewById(R.id.tv_desc4);
        String[] dataRow37 = {"5", tv_serv4_.getText().toString(), spinnerServCode4_.getSelectedItem().toString(), tv_desc4_.getText().toString()};
        writer.writeNext(dataRow37);

        TextView tv_serv5_ = findViewById(R.id.tv_serv5);
        Spinner spinnerServCode5_ = findViewById(R.id.spinnerServCode5);
        TextView tv_desc5_ = findViewById(R.id.tv_desc5);
        String[] dataRow38 = {"6", tv_serv5_.getText().toString(), spinnerServCode5_.getSelectedItem().toString(), tv_desc5_.getText().toString()};
        writer.writeNext(dataRow38);

        TextView tv_serv6_ = findViewById(R.id.tv_serv6);
        Spinner spinnerServCode6_ = findViewById(R.id.spinnerServCode6);
        TextView tv_desc6_ = findViewById(R.id.tv_desc6);
        String[] dataRow39 = {"7", tv_serv6_.getText().toString(), spinnerServCode6_.getSelectedItem().toString(), tv_desc6_.getText().toString()};
        writer.writeNext(dataRow39);
        // Services

        String[] dataRow40 = {"", "", ""};
        writer.writeNext(dataRow40);


        // Known ~
        String[] dataRow41 = {"", "", ""};
        writer.writeNext(dataRow41);

        TextView tv_known0_ = findViewById(R.id.tv_known0);
        EditText editO0_ = findViewById(R.id.editO0);
        String[] dataRow42 = {"1", tv_known0_.getText().toString(), editO0_.getText().toString()};
        writer.writeNext(dataRow42);

        TextView tv_known1_ = findViewById(R.id.tv_known1);
        EditText editO1_ = findViewById(R.id.editO1);
        String[] dataRow43= {"2", tv_known1_.getText().toString(), editO1_.getText().toString()};
        writer.writeNext(dataRow43);

        TextView tv_known2_ = findViewById(R.id.tv_known2);
        EditText editO2_ = findViewById(R.id.editO2);
        String[] dataRow44 = {"3", tv_known2_.getText().toString(), editO2_.getText().toString()};
        writer.writeNext(dataRow44);

        TextView tv_known3_ = findViewById(R.id.tv_known3);
        EditText editO3_ = findViewById(R.id.editO3);
        String[] dataRow45 = {"4", tv_known3_.getText().toString(), editO3_.getText().toString()};
        writer.writeNext(dataRow45);

        writer.close();
        Toast.makeText(getApplicationContext(),xlsFile.getAbsolutePath()+"에 저장되었습니다",Toast.LENGTH_SHORT).show();
    }

    public void onClickDate(View view) {
        DialogFragment newFragment = new EstimateDateFragment();
        newFragment.show(getSupportFragmentManager(),"estimateDatePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        EditText editPM0 = this.findViewById(R.id.editPM0);
        editPM0.setText(dateMessage);
    }
}
