package com.kzhou.spotifystreamer.model.view;

/**
 * User: Kevin
 * Date: 14/06/15
 * Time: 10:47 PM
 */
public class ListItem {
    private String imageUrl;

    private String name;

    private String subName;

    private String id;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
