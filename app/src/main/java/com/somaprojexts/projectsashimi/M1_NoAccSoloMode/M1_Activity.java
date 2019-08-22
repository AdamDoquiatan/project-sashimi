package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.somaprojexts.projectsashimi.BuildConfig;
import com.somaprojexts.projectsashimi.card.Card;
import com.somaprojexts.projectsashimi.util.SectionsStatePagerAdapter;
import com.somaprojexts.projectsashimi.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class M1_Activity extends AppCompatActivity {

    private static final String TAG = "M1_Activity.java";
    private static final String YELP_BASE_URL = "https://api.yelp.com/v3";

    private static ViewPager viewPager;
    private static SectionsStatePagerAdapter adapter;
    private static List<String> fragmentNameList;

    private OkHttpClient client;
    private JSONObject yelpData;
    private LocationManager locationManager;
    private double latitude;
    private double longitude;
    private ArrayList<Card> cards;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.m1_activity_layout);

        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        client = new OkHttpClient();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        cards = new ArrayList<>();

        viewPager = findViewById(R.id.m1_fragment_container);
        setupViewPager(viewPager);

        // Get GPS coordinates
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Log.i(TAG, "GPS: latitude=" + latitude + " longitude=" + longitude);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        } else {
            latitude = 49.27468354941877;
            longitude = -122.79091358272127;
        }
    }

    protected void onResume() {
        super.onResume();
        String url = "/businesses/search?radius=500&latitude=" + latitude
                + "&longitude=" + longitude;
        Log.i(TAG, "URL: " + url);
        doYelpGetRequest(url);
    }

    public static void setFragment(String fragmentName) {
        Log.i(TAG, "SetFragment");
        viewPager.setCurrentItem(fragmentNameList.indexOf(fragmentName));
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new M1_PrefSelect_Frag(), "M1_PrefSelect_Frag");
        adapter.addFragment(new M1_SwipeDash_Frag(), "M1_SwipeDash_Frag");
        adapter.addFragment(new M1_NoMoreOptions_Frag(), "M1_NoMoreOptions_Frag");
        adapter.addFragment(new M1_Details_Frag(), "M1_Details_Frag");
        adapter.addFragment(new M1_Favorites_Frag(), "M1_Favorites_Frag");
        adapter.addFragment(new M1_FavDetails_Frag(), "M1_FavDetails_Frag");
        adapter.addFragment(new M1_Map_Frag(), "M1_Map_Frag");

        fragmentNameList = adapter.getFragmentNameList();
        viewPager.setAdapter(adapter);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    private void doYelpGetRequest(String url) {
        Request request = new Request.Builder()
                .url(YELP_BASE_URL + url)
                .addHeader("Authorization", "Bearer " + BuildConfig.YELP_API_KEY)
                .build();
        final Resources resources = getResources();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response1) {
                try (ResponseBody body1 = response1.body()) {
                    yelpData = new JSONObject(body1.string());
                    Log.i(TAG, yelpData.toString(1));
                    JSONArray businesses = yelpData.getJSONArray("businesses");
                    M1_SwipeDash_Frag swipeDashFrag = (M1_SwipeDash_Frag)
                            adapter.getItem("M1_SwipeDash_Frag");

                    for (int i = 0; i < businesses.length(); i++) {
                        final JSONObject business = businesses.getJSONObject(i);
                        String url = business.getString("image_url");
                        Log.i(TAG, "IMAGE_URL: " + url);
                        Request request = new Request.Builder().url(url).build();
                        Response response2 = client.newCall(request).execute();

                        try (ResponseBody body2 = response2.body()) {
                            InputStream input = body2.byteStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(input);
                            Drawable image = new BitmapDrawable(resources, bitmap);
                            Card card = new Card(business.getString("name"), image);
                            cards.add(card);
                            swipeDashFrag.getCardStackAdapter().notifyItemInserted(cards.size() - 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}