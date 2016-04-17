package com.example.miro.jsonpars.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.miro.jsonpars.R;
import com.example.miro.jsonpars.constants.Constants;
import com.example.miro.jsonpars.constants.UserConstants;
import com.example.miro.jsonpars.models.User;

public class UserInfoActivity extends AppCompatActivity {

    TextView tvID, tvName, tvUserName, tvEmail, tvStreet, tvSuite, tvCity, tvZipcode, tvLat, tvLng, tvPhone, tvWebsite, tvCompanyName, tvCatchPhrase,  tvBs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        findViews();

        Intent intent = getIntent();
        long lId = intent.getLongExtra(UserConstants.ID, 0);
        String sId = String.valueOf(lId);
        tvID.setText("Id: " + sId);
        tvName.setText("Name: " + intent.getStringExtra(UserConstants.NAME));
        tvUserName.setText("Username: " + intent.getStringExtra(UserConstants.USERNAME));
        tvEmail.setText("Email: " + intent.getStringExtra(UserConstants.EMAIL));
        tvStreet.setText("Street: " + intent.getStringExtra(UserConstants.STREET));
        tvSuite.setText("Suite: " + intent.getStringExtra(UserConstants.SUITE));
        tvCity.setText("City: " + intent.getStringExtra(UserConstants.CITY));
        tvZipcode.setText("Zipcode: " + intent.getStringExtra(UserConstants.ZIPCODE));
        tvLat.setText("Lat: " + intent.getStringExtra(UserConstants.LAT));
        tvLng.setText("Lng: " + intent.getStringExtra(UserConstants.LNG));
        tvPhone.setText("Phone: " + intent.getStringExtra(UserConstants.PHONE));
        tvWebsite.setText("Website: " + intent.getStringExtra(UserConstants.WEBSITE));
        tvCompanyName.setText("Name: " + intent.getStringExtra(UserConstants.COMPANY));
        tvCatchPhrase.setText("CatchPhrase: " + intent.getStringExtra(UserConstants.CATCHPHRASE));
        tvBs.setText("Bs: " + intent.getStringExtra(UserConstants.BS));
//        Log.d("mLog", intent.getStringExtra(UserConstants.CATCHPHRASE));
    }

    private void findViews() {
        tvID = (TextView) findViewById(R.id.id_UIA);
        tvName = (TextView) findViewById(R.id.name_UIA);
        tvUserName = (TextView) findViewById(R.id.username_UIA);
        tvEmail = (TextView) findViewById(R.id.email_UIA);
        tvStreet = (TextView) findViewById(R.id.street_UIA);
        tvSuite = (TextView) findViewById(R.id.suite_UIA);
        tvCity = (TextView) findViewById(R.id.city_UIA);
        tvZipcode = (TextView) findViewById(R.id.zipcode_UIA);
        tvLat = (TextView) findViewById(R.id.lat_UIA);
        tvLng = (TextView) findViewById(R.id.lng_UIA);
        tvPhone = (TextView) findViewById(R.id.phone_UIA);
        tvWebsite = (TextView) findViewById(R.id.website_UIA);
        tvCompanyName = (TextView) findViewById(R.id.companyName_UIA);
        tvCatchPhrase = (TextView) findViewById(R.id.catchPhrase_UIA);
        tvBs = (TextView) findViewById(R.id.bs_UIA);
    }
}
