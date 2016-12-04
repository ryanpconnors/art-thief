package com.ryanpconnors.artthief.vote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.ryanpconnors.artthief.R;
import com.ryanpconnors.artthief.artgallery.ArtWork;
import com.ryanpconnors.artthief.artgallery.Gallery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by rpc80 on 12/3/16.
 */

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView; // source: https://github.com/dm77/barcodescanner
    private static final String TAG = "ScannerActivity";

    private final ArrayList<BarcodeFormat> barcodeFormats = new ArrayList<BarcodeFormat>() {{
        add(BarcodeFormat.QR_CODE);
    }};

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        mScannerView.setFormats(barcodeFormats);
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        Log.v(TAG, rawResult.getText());
        Log.v(TAG, rawResult.getBarcodeFormat().toString());

        mScannerView.stopCamera();
        String scanData = rawResult.getText();

        Toast.makeText(this, scanData, Toast.LENGTH_LONG).show();

        //TODO: get user rating info and send to Zurka
        getUserVotingPackage(scanData);

        finish();
    }


    private JSONObject getUserVotingPackage(String scanData) {

        JSONObject jsonPackage = new JSONObject();
        JSONArray list = new JSONArray();

        for (int i = 5; i >= 1; i--) {
            List<ArtWork> artworks = Gallery.get(this).getArtWorks(i, getString(R.string.descending));

            for (ArtWork artWork : artworks) {
                JSONObject artworkJson = new JSONObject();
                try {
                    artworkJson.put(getString(R.string.package_art_thief_id), artWork.getArtThiefID());
                    artworkJson.put(getString(R.string.package_show_id), artWork.getShowId());
                    artworkJson.put(getString(R.string.package_rating), artWork.getStars());
                    list.put(artworkJson);
                }
                catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        try {
            jsonPackage.put(getString(R.string.package_list), list);
            jsonPackage.put(getString(R.string.package_uuid), UUID.randomUUID().toString());
            jsonPackage.put(getString(R.string.package_scan_data), scanData);
        }
        catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        return jsonPackage;
    }

}
