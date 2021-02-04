package com.example.s3labbahk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.util.concurrent.TimeUnit;

public class LocationActivity extends AppCompatActivity implements AutoPermissionsListener { // '위험권한' '자동' 부여를 위한 코드 추가
    protected TextView tv_my_loc;
    private String getItem = null;
    private Spinner spi_dest = null;
    private LatLng coordTarget;
    private LatLng coordDest = new LatLng(35.1035259, 129.0400728);
    private String my_loc = null;
    protected EditText mEditTextSpeed;
    protected TextView tv_time1;

    SupportMapFragment mapFragment;
    GoogleMap map;
    private Marker currentMarker = null;
    private Marker destMarker = null;
    private MarkerOptions markerOptions2 = new MarkerOptions();
    private Polyline polyline = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);

        tv_my_loc = findViewById(R.id.text_view_my_loc);
        spi_dest = findViewById(R.id.spinner_dest1);
        spi_dest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // update a time
                if (getItem != null) {
                    tv_time1.setText(String.format(""));
                }

                getItem = spi_dest.getSelectedItem().toString();
                if (getItem.equals("부산항")) {
                    coordDest = new LatLng(35.1035259, 129.0400728);
                } else if (getItem.equals("인천항")) {
                    coordDest = new LatLng(37.4592495, 126.6248459);
                } else if (getItem.equals("울산항")) {
                    coordDest = new LatLng(35.5188419, 129.3742153);
                }
                // 목적지 위치 표시를 위한 마커
                markerOptions2.position(coordDest);
                markerOptions2.title("Destination");
                markerOptions2.snippet(getItem);
                markerOptions2.draggable(true);
                markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                if (destMarker == null) {
                    destMarker = map.addMarker(markerOptions2);
                } else {
                    destMarker.remove();
                    destMarker = map.addMarker(markerOptions2);
                }

                // update a line
                if (polyline != null) {
                    polyline.remove();
                    polyline = map.addPolyline(new PolylineOptions()
                            .add(coordTarget, coordDest)
                            .color(Color.parseColor("#009688"))
                            .width(5));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        mEditTextSpeed = findViewById(R.id.edit_text_speed);
        mEditTextSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextSpeed.setText("");
            }
        });

        Button btnCalc_ = findViewById(R.id.btnCalc);
        btnCalc_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 클릭 이벤트 처리
                boolean isDigitResult = true;
                my_loc = tv_my_loc.getText().toString();
                String spd = mEditTextSpeed.getText().toString();
                char tmpCh;

                for (int i=0; i<spd.length(); i++) {
                    tmpCh = spd.charAt(i);
                    if (Character.isDigit(tmpCh) == false && !(tmpCh == '.')) {
                        isDigitResult = false;
                    }
                }

                if (isDigitResult == false) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해 주세요", Toast.LENGTH_LONG).show();
                    tv_time1.setText(String.format(""));
                } else {
                    if (!spd.equals("") && my_loc != null && my_loc.length() != 0 && coordDest != null) {
                        //coordTarget = new LatLng(35.166668, 129.066666);
                        //coordDest = new LatLng(37.532600, 127.024612);
                        double distance = getDistance(coordTarget, coordDest); // meter  // 320000m

                        double speedDoubleVal = Double.valueOf(spd); // km/h
                        double speedKn = speedDoubleVal / 1.852; // km/h  to  knot
                        int time = ((int)distance / (int)speedKn); // 320km / 115 km/h    =>    02:47
                        int timeMinute = time * 3600; // ms(milli seconds) to minutes
                        String strMinute = String.format("%dh %02dm", TimeUnit.MILLISECONDS.toMinutes(timeMinute)/60,
                                TimeUnit.MILLISECONDS.toMinutes(timeMinute)%60);
                        tv_time1.setText(strMinute);
                    } else {
                        tv_time1.setText(String.format(""));
                    }
                }
            }
        });
        /*
        mEditTextSpeed.addTextChangedListener(new TextWatcher() {
            Boolean isBackspaceClicked = false;

            @Override
            // 입력되는 텍스트에 변화가 있을 때
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            // 입력이 끝났을 때
            public void afterTextChanged(Editable arg0) {
                boolean isDigitResult = true;
                String spd = mEditTextSpeed.getText().toString();
                char tmpCh;

                for (int i=0; i<spd.length(); i++) {
                    tmpCh = spd.charAt(i);
                    if (Character.isDigit(tmpCh) == false && !(tmpCh == '.')) {
                        isDigitResult = false;
                    }
                }

                if (isDigitResult == false) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해 주세요", Toast.LENGTH_LONG).show();
                } else {
                    if (!spd.equals("") && !my_loc.equals("NULL") && !coordDest.equals("NULL")) {
                        //coordTarget = new LatLng(35.166668, 129.066666);
                        //coordDest = new LatLng(37.532600, 127.024612);
                        double distance = getDistance(coordTarget, coordDest); // meter  // 320000m

                        double speedDoubleVal = Double.valueOf(spd); // km/h
                        double speedKn = speedDoubleVal / 1.852; // km/h  to  knot
                        int time = ((int)distance / (int)speedKn); // 320km / 115 km/h    =>    02:47
                        int timeMinute = time * 3600; // ms(milli seconds) to minutes
                        String strMinute = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(timeMinute)/60,
                                TimeUnit.MILLISECONDS.toMinutes(timeMinute)%60);
                        tv_time1.setText(strMinute);
                    } else {
                        tv_time1.setText(String.format(""));
                    }
                }
            }

            @Override
            // 입력하기 전에
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        */
        tv_time1 = findViewById(R.id.disp_time1);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                startLocationService();
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AutoPermissions.Companion.loadAllPermissions(this, 100);
    }

    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        double distance = 0;

        Location locationA = new Location("A");
        Location locationB = new Location("B");

        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);

        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);

        distance = locationA.distanceTo(locationB);

        return distance;
    }

    private void setDefaultLocation() {
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97); //디폴트 위치, Seoul
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요";

        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        currentMarker = map.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 11);
        map.moveCamera(cameraUpdate);
    }

    // 1. 위치 "관리자" 객체 참조
    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); // 위치관리자 객체 정의

        try {
            int chk1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            int chk2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

            Location location = null;
            if (chk1 == PackageManager.PERMISSION_GRANTED && chk2 == PackageManager.PERMISSION_GRANTED) { // ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION 권한이 모두 있을 경우 동작
                location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER); // 최근 위치정보 확인
            } else {
                return;
            }
            if (location != null) {
                double latitude = location.getLatitude(); // 위도
                double longitude = location.getLongitude(); // 경도
                coordTarget = new LatLng(latitude, longitude);
                //String msg = "<최근 위치>\nLatitude: " + latitude + "\nLongitude: " + longitude;
                String msg = String.format("%.6f", latitude) + "\n" + String.format("%.6f", longitude);
                tv_my_loc.setText(msg);
                showCurrentLocation(latitude, longitude);

                // draw a line
                polyline = map.addPolyline(new PolylineOptions()
                        .add(coordTarget, coordDest)
                        .color(Color.parseColor("#009688"))
                        .width(5));
            }

            // 3. 위치정보 업데이트 요청
            GPSListener gpsListener = new GPSListener(); // 리스너 객체 생성
            long minTime = 10000; // 10s
            float minDistance = 0;

            // 10s 주기로 위치정보를 전달받음.
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener); // 위치 요청 (최소 시간 or 최소 거리 이상일 경우)
            //Toast.makeText(getApplicationContext(), "내 위치 요청 중", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    // 2. 위치 "리스너" 구현
    class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) { // 위치 정보가 확인됐을 때, 자동 호출되는 메소드
            Double latitude = location.getLatitude(); // 위도
            Double longitude = location.getLongitude(); // 경도
            coordTarget = new LatLng(latitude, longitude);
            //String msg = "<내 위치>\nLatitude: " + latitude + "\nLongitude: " + longitude;
            String msg = String.format("%.6f", latitude) + "\n" + String.format("%.6f", longitude);
            Log.d("Map", msg);

            showCurrentLocation(latitude, longitude);
            //tv_my_loc.setText(msg);
        }

        @Override
        public void onProviderDisabled(String provider) {}
        @Override
        public void onProviderEnabled(String provider) {}
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }

    private void showCurrentLocation(Double latitude, Double longitude) {
        LatLng curPoint = new LatLng(latitude, longitude);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 11));

        //디폴트 위치, Seoul
        //Toast.makeText(getApplicationContext(), "실행", Toast.LENGTH_LONG).show();
        String markerTitle = "내위치";
        String markerSnippet = "위치정보가 확인되었습니다.";

        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(curPoint);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        currentMarker = map.addMarker(markerOptions);



        // update a line
        if (polyline != null) {
            polyline.remove();
            polyline = map.addPolyline(new PolylineOptions()
                    .add(coordTarget, coordDest)
                    .color(Color.parseColor("#009688"))
                    .width(5));
        }



        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(curPoint, 11);
        map.moveCamera(cameraUpdate);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int requestCode, String[] permissions) {
        //Toast.makeText(this, "Permissions Denied(" + permissions.length + ")", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        //Toast.makeText(this, "Permissions Granted(" + permissions.length + ")", Toast.LENGTH_LONG).show();
    }
}