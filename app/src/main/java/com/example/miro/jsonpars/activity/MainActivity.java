package com.example.miro.jsonpars.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.miro.jsonpars.ParseTask;
import com.example.miro.jsonpars.R;
import com.example.miro.jsonpars.constants.UserConstants;
import com.example.miro.jsonpars.listview.UserAdapter;
import com.example.miro.jsonpars.models.User;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ParseTask.UsersInfo, AdapterView.OnItemClickListener {

    private ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseTask mParseTask = new ParseTask();
        mParseTask.execute();
        mParseTask.setInterface(this);
    }

    @Override
    public void usersIsReady(ArrayList<User> users) {
        if (users != null){
            userArrayList = users;
            UserAdapter userAdapter = new UserAdapter(this, users);
            ListView listView = (ListView) findViewById(R.id.lvUsersMA);
            if (listView != null) {
                listView.setAdapter(userAdapter);
                listView.setOnItemClickListener(this);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = userArrayList.get(position);
        Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
        intent.putExtra(UserConstants.ID, user.getId());
        intent.putExtra(UserConstants.NAME, user.getName());
        intent.putExtra(UserConstants.USERNAME, user.getUsername());
        intent.putExtra(UserConstants.EMAIL, user.getEmail());
        intent.putExtra(UserConstants.PHONE, user.getPhone());
        intent.putExtra(UserConstants.WEBSITE, user.getWebsite());
        intent.putExtra(UserConstants.STREET, user.getAddress().getStreet());
        intent.putExtra(UserConstants.SUITE, user.getAddress().getSuite());
        intent.putExtra(UserConstants.CITY, user.getAddress().getCity());
        intent.putExtra(UserConstants.ZIPCODE, user.getAddress().getZipcode());
        intent.putExtra(UserConstants.LAT, user.getAddress().getGeo().getLat());
        intent.putExtra(UserConstants.LNG, user.getAddress().getGeo().getLng());
        intent.putExtra(UserConstants.COMPANY, user.getCompany().getName());
        intent.putExtra(UserConstants.CATCHPHRASE, user.getCompany().getCatchPhrase());
        intent.putExtra(UserConstants.BS, user.getCompany().getBs());
        startActivity(intent);
    }
}
