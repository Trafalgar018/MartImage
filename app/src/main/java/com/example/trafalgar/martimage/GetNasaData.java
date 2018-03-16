package com.example.trafalgar.martimage;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdelgado on 8/2/18.
 */

public class GetNasaData extends GetRawData {

    private static final String LOG_TAG = GetNasaData.class.getSimpleName();

    private List<Photo> mPhotos;
    private Uri mDestinationUri;

    public GetNasaData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        mPhotos = new ArrayList<>();
    }

    public List<Photo> getPhotos() {
        return mPhotos;
    }

    private boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String FLICKR_BASE_API_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=ctC1YbsCf2Zq7VJZbs736cPrfkKqkFdlZ5QsAzpT";

        mDestinationUri = Uri.parse(FLICKR_BASE_API_URL).buildUpon().build();

        return mDestinationUri != null;
    }

    private void processResult() {
        final String FLICKR_ITEMS = "photos";
        final String FLICKR_TITLE = "earth_date";
        final String FLICKR_MEDIA = "media";
        final String FLICKR_PHOTO_URL = "img_src";
        final String FLICKR_AUTHOR = "sol";

        if ( getDownloadStatus() != DownloadStatus.OK ) {
            Log.e(LOG_TAG, "No se ha descargado el JSON");
            return;
        }

        try {
            JSONObject jsonData = new JSONObject(getData());
            JSONArray itemsArray = jsonData.getJSONArray(FLICKR_ITEMS);

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                String title = jsonPhoto.getString(FLICKR_TITLE);
                String author = jsonPhoto.getString(FLICKR_AUTHOR);
                String photoUrl = jsonPhoto.getString(FLICKR_PHOTO_URL);
                String link = photoUrl.replaceFirst("_m.", "_b.");

                // Extract the author's name from the Json response field

                Photo photo = new Photo(title,link,author,photoUrl);
                mPhotos.add(photo);
            }

            for(Photo photo: mPhotos){
                Log.d(LOG_TAG, "Photo: " + photo.toString());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "No se puede crear el objeto JSON");
            e.printStackTrace();
        }
    }

    public void execute() {
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Build Uri: " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            String [] par = { mDestinationUri.toString() };

            return super.doInBackground(par);
        }
    }
}