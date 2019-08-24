package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.somaprojexts.projectsashimi.BuildConfig;
import com.somaprojexts.projectsashimi.card.Card;
import com.somaprojexts.projectsashimi.card.CardStackAdapter;
import com.somaprojexts.projectsashimi.util.SectionsStatePagerAdapter;
import com.somaprojexts.projectsashimi.R;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M1_Activity extends AppCompatActivity {

    private static final String TAG = "M1_Activity.java";
    private static final String YELP_BASE_URL = "https://api.yelp.com/v3";

    private static ViewPager viewPager;
    private static SectionsStatePagerAdapter adapter;
    private static List<String> fragmentNameList;

    private ArrayList<Card> cards;
    private CardStackLayoutManager cardStackLayoutManager;
    private CardStackAdapter cardStackAdapter;
    private RequestQueue queue;
    private LocationManager locationManager;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.m1_activity_layout);

        cards = new ArrayList<>();
        cardStackLayoutManager = new CardStackLayoutManager(this);
        cardStackAdapter = new CardStackAdapter(this, cards);

        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.m1_fragment_container);
        setupViewPager(viewPager);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestSingleUpdate(
                LocationManager.GPS_PROVIDER, new GPSLocationListener(), null);
        queue = Volley.newRequestQueue(this);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public CardStackLayoutManager getCardStackLayoutManager() {
        return cardStackLayoutManager;
    }

    public CardStackAdapter getCardStackAdapter() {
        return cardStackAdapter;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new M1_PrefSelect_Frag(), "M1_PrefSelect_Frag");
        adapter.addFragment(new M1_SwipeDash_Frag(), "M1_SwipeDash_Frag");
//        adapter.addFragment(new M1_NoMoreOptions_Frag(), "M1_NoMoreOptions_Frag");
//        adapter.addFragment(new M1_Details_Frag(), "M1_Details_Frag");
//        adapter.addFragment(new M1_Favorites_Frag(), "M1_Favorites_Frag");
//        adapter.addFragment(new M1_FavDetails_Frag(), "M1_FavDetails_Frag");
//        adapter.addFragment(new M1_Map_Frag(), "M1_Map_Frag");

        fragmentNameList = adapter.getFragmentNameList();
        viewPager.setAdapter(adapter);
    }

    public static void setFragment(String fragmentName) {
        Log.i(TAG, "SetFragment");
        viewPager.setCurrentItem(fragmentNameList.indexOf(fragmentName));
    }

    private void doYelpGetRequest(String url, final Consumer<JSONObject> callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, YELP_BASE_URL + url, null,
                callback::accept, null) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + BuildConfig.YELP_API_KEY);

                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    private void doImageGetRequest(String url, final Consumer<Drawable> callback) {
        ImageRequest imageRequest = new ImageRequest(
                url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Drawable image = new BitmapDrawable(getResources(), response);
                try {
                    callback.accept(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 0,
                ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, null);
        queue.add(imageRequest);
    }

    private class GPSLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            String url = "/businesses/search?radius=2000&latitude=" + location.getLatitude()
                    + "&longitude=" + location.getLongitude();
            Log.i(TAG, "URL: " + url);

            doYelpGetRequest(url, new CardLoader());
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
    }

    private class CardLoader implements Consumer<JSONObject> {

        @Override
        public void accept(JSONObject jsonObject) {
            try {
                JSONArray businesses = jsonObject.getJSONArray("businesses");

                for (int i = 0; i < businesses.length(); i++) {
                    JSONObject business = businesses.getJSONObject(i);

                    doImageGetRequest(business.getString("image_url"), image -> {
                        try {
                            Card card = new Card(business.getString("name"), image);
                            cards.add(card);
                            cardStackAdapter.notifyItemInserted(cards.size() - 1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}