package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=";

    // Member Variables:
    TextView mPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        final Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // TODO: Set an OnItemSelected listener on the spinner
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d("BitCoin", "" + parent.getItemAtPosition(position));
                Log.d("Bitcoin", "Position is: " + position);
                letsDoSomeNetworking("https://api.coingecko.com/api/v3/global");
                String finalUrl = BASE_URL + parent.getItemAtPosition(position);
                Log.d("BitCoin", "Final url is: " + finalUrl);
                String currency = spinner.getSelectedItem().toString();
                Log.d("BitCoin","A deviza: " + currency);
                letsDoSomeNetworking(finalUrl);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Bitcoin", "Nothing selected.");
            }
        });
    }


    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(final String url) {

        final AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("BitCoin", "JSON: " + response.toString());

                try {
                    String usd = response.getJSONObject("bitcoin").getString("usd");
                    Log.d("BitCoin", "USD exchange rate is: " + usd);
                    mPriceTextView.setText(usd);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String eur = response.getJSONObject("bitcoin").getString("eur");
                    mPriceTextView.setText(eur);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String huf = response.getJSONObject("bitcoin").getString("huf");
                    mPriceTextView.setText(huf);
                    Log.d("BitCoin", "huf árfolyama: " + huf);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String aud = response.getJSONObject("bitcoin").getString("aud");
                    mPriceTextView.setText(aud);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String cad = response.getJSONObject("bitcoin").getString("cad");
                    mPriceTextView.setText(cad);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String cny = response.getJSONObject("bitcoin").getString("cny");
                    mPriceTextView.setText(cny);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String gbp = response.getJSONObject("bitcoin").getString("gbp");
                    mPriceTextView.setText(gbp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String hkd = response.getJSONObject("bitcoin").getString("hkd");
                    mPriceTextView.setText(hkd);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String jpy = response.getJSONObject("bitcoin").getString("jpy");
                    mPriceTextView.setText(jpy);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String pln = response.getJSONObject("bitcoin").getString("pln");
                    mPriceTextView.setText(pln);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String rub = response.getJSONObject("bitcoin").getString("rub");
                    mPriceTextView.setText(rub);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String sek = response.getJSONObject("bitcoin").getString("sek");
                    mPriceTextView.setText(sek);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    String zar = response.getJSONObject("bitcoin").getString("zar");
                    mPriceTextView.setText(zar);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("BitCoin", "Request fail! Status code: " + statusCode);
                Log.d("BitCoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());
            }
        });
    }

}