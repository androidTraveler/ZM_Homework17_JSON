package com.example.miro.jsonpars;

import android.os.AsyncTask;
import com.example.miro.jsonpars.constants.Constants;
import com.example.miro.jsonpars.constants.UserConstants;
import com.example.miro.jsonpars.models.Address;
import com.example.miro.jsonpars.models.Company;
import com.example.miro.jsonpars.models.Geo;
import com.example.miro.jsonpars.models.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ParseTask extends AsyncTask<Void, Void, String> {

    private String resultJson = "";
    private ArrayList<User> userArrayList = new ArrayList<>();
    private UsersInfo mInterface;

    public interface UsersInfo{
        void usersIsReady(ArrayList<User> users);
    }

    public void setInterface(UsersInfo i){
        mInterface = i;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            URL url = new URL("http://jsonplaceholder.typicode.com/users");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(Constants.GET);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;// вся стрічка JSON отримана з посилання
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);

        String sName, sUsername, sEmail, sStreet, sSuite, sCity, sZipCode, sLat, sLng, sPhone, sWebsite, sCompanyName, sCatchPhrase, sBs;
        long lId;
        JSONArray users;

        try {
            users = new JSONArray(strJson);

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                    lId = user.getLong(UserConstants.ID);
                    sName = user.getString(UserConstants.NAME);
                    sUsername = user.getString(UserConstants.USERNAME);
                    sEmail = user.getString(UserConstants.EMAIL);
                    sPhone = user.getString(UserConstants.PHONE);
                    sWebsite = user.getString(UserConstants.WEBSITE);

                    JSONObject  address = user.getJSONObject(UserConstants.ADDRESS);
                        sStreet = address.getString(UserConstants.STREET);
                        sSuite = address.getString(UserConstants.SUITE);
                        sCity = address.getString(UserConstants.CITY);
                        sZipCode = address.getString(UserConstants.ZIPCODE);

                        JSONObject geo = address.getJSONObject(UserConstants.GEO);
                            sLat = geo.getString(UserConstants.LAT);
                            sLng = geo.getString(UserConstants.LNG);

                JSONObject company = user.getJSONObject(UserConstants.COMPANY);
                    sCompanyName = company.getString(UserConstants.NAME);
                    sCatchPhrase = company.getString(UserConstants.CATCHPHRASE);
                    sBs = company.getString(UserConstants.BS);

                Geo cGeo = new Geo(sLat, sLng);
                Address cAddress = new Address(sStreet,sSuite,sCity,sZipCode,cGeo);
                Company cCompany = new Company(sCompanyName,sCatchPhrase,sBs);
                User cUser = new User(lId, sName, sUsername, sEmail, cAddress, sPhone, sWebsite, cCompany);

                userArrayList.add(cUser);

                //Log.d("mLog", cUser.getAddress().getGeo().getLng());
                //Log.d("mLog", cUser.getName());
            }
            mInterface.usersIsReady(userArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}