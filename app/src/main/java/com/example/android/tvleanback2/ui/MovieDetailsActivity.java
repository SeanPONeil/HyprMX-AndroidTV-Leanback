/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.android.tvleanback2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.tvleanback2.R;
import com.hyprmx.android.activities.HyprMXActivity;
import com.hyprmx.android.sdk.HyprMXPresentation;
import com.hyprmx.android.sdk.api.data.Offer;
import com.hyprmx.android.sdk.api.data.OffersAvailableResponse;
import com.hyprmx.android.sdk.utility.OnOffersAvailableResponseListener;

/*
 * Details activity class that loads LeanbackDetailsFragment class
 */
public class MovieDetailsActivity extends HyprMXActivity {
    public static final String SHARED_ELEMENT_NAME = "hero";
    public static final String MOVIE = "Movie";
    public static final String NOTIFICATION_ID = "NotificationId";

    HyprMXPresentation _presentation;
    Runnable runnable;
    boolean areOffersAvailable = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_details);

        _presentation = new HyprMXPresentation();
        _presentation
                .prepare(new OnOffersAvailableResponseListener() {

                    @Override
                    public void onError(int statusCode, Exception error) {
                        Toast.makeText(MovieDetailsActivity.this,
                                "Prepare failed: " + error.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onOffersAvailable(OffersAvailableResponse offersAvailable) {
                        for (Offer offer : offersAvailable.getOffersAvailable()) {
                            MovieDetailsActivity.this.areOffersAvailable = true;

                            /**
                             * We generate a unique transaction ID for each offer, and automatically send it back to you in the callback.
                             * If you would like to provide your own transaction ID you can do so by setting the transaction ID on the offer
                             * you get here. You can verify the value set here is matched against the offer passed to the onOfferCompleted callback.
                             *
                             */
                            offer.setTransactionId("PartnerCode");
                        }
                    }

                    @Override
                    public void onNoOffersAvailable(OffersAvailableResponse offersAvailable) {
                        Toast.makeText(MovieDetailsActivity.this,
                                "No Offers", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public boolean onSearchRequested() {
        startActivity(new Intent(this, SearchActivity.class));
        return true;
    }

    public void setTrailerClickedRunnable(MovieDetailsFragment.TrailerClickedRunnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void onOfferCompleted(Offer offer) {
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override
    public void onOfferCancelled(Offer offer) {
        if (runnable != null) {
            runnable.run();
        }
    }

    public HyprMXPresentation getPresentation() {
        return _presentation;
    }
}
