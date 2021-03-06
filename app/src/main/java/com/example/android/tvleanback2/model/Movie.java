/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.tvleanback2.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;
import java.net.URISyntaxException;

/*
 * Movie class represents video entity with title, description, image thumbs and video url.
 */
public class Movie implements Parcelable {
    private String mId;
    private String mTitle;
    private String mDescription;
    private String mBgImageUrl;
    private String mCardImageUrl;
    private String mVideoUrl;
    private String mStudio;
    private String mCategory;

    public Movie() {}

    private Movie(Parcel in) {
        String[] data = new String[8];
        in.readStringArray(data);

        mId = data[0];
        mTitle = data[1];
        mDescription = data[2];
        mBgImageUrl = data[3];
        mCardImageUrl = data[4];
        mVideoUrl = data[5];
        mStudio = data[6];
        mCategory = data[7];
    }

    @Override
    public boolean equals(Object m) {
        return m instanceof Movie && mId.equals(((Movie) m).getId());
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getStudio() {
        return mStudio;
    }

    public void setStudio(String studio) {
        mStudio = studio;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }

    public String getBackgroundImageUrl() {
        return mBgImageUrl;
    }

    public void setBackgroundImageUrl(String bgImageUrl) {
        mBgImageUrl = bgImageUrl;
    }

    public String getCardImageUrl() {
        return mCardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        mCardImageUrl = cardImageUrl;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public URI getBackgroundImageURI() {
        try {
            return new URI(getBackgroundImageUrl());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{mId,
                mTitle,
                mDescription,
                mBgImageUrl,
                mCardImageUrl,
                mVideoUrl,
                mStudio,
                mCategory});
    }

    @Override
    public String toString() {
        String s = "Movie{";
        s += "mId=" + mId;
        s += ", mTitle='" + mTitle + "'";
        s += ", mVideoUrl='" + mVideoUrl + "'";
        s += ", backgroundImageUrl='" + mBgImageUrl + "'";
        s += ", backgroundImageURI='" + getBackgroundImageURI().toString() + "'";
        s += ", mCardImageUrl='" + mCardImageUrl + "'";
        s += "}";
        return s;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
