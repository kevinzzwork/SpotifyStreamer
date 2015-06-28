package com.kzhou.spotifystreamer;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Kevin
 * Date: 25/06/15
 * Time: 10:05 PM
 */
public class Constants {
    public static final String ARTIST_ID_KEY = "ArtistId";
    public static final String ARTIST_NAME_KEY = "ArtistName";
    public static final Map<String, Object> countryCodeMap;
    static
    {
        countryCodeMap = new HashMap<String, Object>();
        countryCodeMap.put("country", "US");
    }
}
