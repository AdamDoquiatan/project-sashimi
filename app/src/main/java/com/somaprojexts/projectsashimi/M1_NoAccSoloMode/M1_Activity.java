package com.somaprojexts.projectsashimi.M1_NoAccSoloMode;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
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
import java.util.concurrent.CompletableFuture;

public class M1_Activity extends AppCompatActivity {

    private static final String TAG = "M1_Activity.java";

    private static ViewPager viewPager;
    private static SectionsStatePagerAdapter adapter;
    private static List<String> fragmentNameList;

    // CardStackView components
    private ArrayList<Card> cards;
    private CardStackLayoutManager cardStackLayoutManager;
    private CardStackAdapter cardStackAdapter;

    private RequestQueue queue; // Volley HTTP request queue
    private FusedLocationProviderClient fusedLocationClient; // GPS location API

    // References to fragments
    private M1_PrefSelect_Frag m1PrefSelectFrag;
    private M1_SwipeDash_Frag m1SwipeDashFrag;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.m1_activity_layout);

        // Initialize CardStackView components
        cards = new ArrayList<>();
        cardStackLayoutManager = new CardStackLayoutManager(this);
        cardStackAdapter = new CardStackAdapter(this, cards);

        queue = Volley.newRequestQueue(this);

        // Set up view pager
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.m1_fragment_container);
        setupViewPager(viewPager);

        // Check location permissions
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Wait to get last GPS location, then send Yelp API call with location coordinates
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            String url = getYelpUrl("/businesses/search", location);
            getYelpData(url).thenAcceptAsync(data -> loadCardsFromYelpData(cards, data));
        });
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
        m1PrefSelectFrag = (M1_PrefSelect_Frag) adapter
                .addFragment(new M1_PrefSelect_Frag(), "M1_PrefSelect_Frag");
        m1SwipeDashFrag = (M1_SwipeDash_Frag) adapter
                .addFragment(new M1_SwipeDash_Frag(), "M1_SwipeDash_Frag");
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

    private String getYelpUrl(String endpoint, Location location) {
        return BuildConfig.YELP_BASE_URL + endpoint + "?radius=2000" +
                "&latitude=" + location.getLatitude() +
                "&longitude=" + location.getLongitude();
    }

    private CompletableFuture<JSONObject> getYelpData(String url) {
        CompletableFuture<JSONObject> result = new CompletableFuture<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> result.complete(response), null) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + BuildConfig.YELP_API_KEY);

                return params;
            }
        };

        queue.add(jsonObjectRequest);
        return result;
    }

    private CompletableFuture<Drawable> getYelpImage(String url) {
        CompletableFuture<Drawable> result = new CompletableFuture<>();

        ImageRequest imageRequest = new ImageRequest(
                url, response -> result.complete(new BitmapDrawable(getResources(), response)),
                0, 0,
                ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, null);

        queue.add(imageRequest);
        return result;
    }

    private void loadCardsFromYelpData(ArrayList<Card> cards, JSONObject data) {
        try {
            JSONArray businesses = data.getJSONArray("businesses");

            for (int i = 0; i < businesses.length(); i++) {
                JSONObject business = businesses.getJSONObject(i);
                String name = business.getString("name");
                getYelpImage(business.getString("image_url"))
                        .thenAccept(image -> {
                            cards.add(new Card(name, image));
                            cardStackAdapter.notifyItemInserted(cards.size() - 1);
                            m1SwipeDashFrag.getProgress_bar().setVisibility(View.INVISIBLE);
                        });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}