package io.realm;


public interface ArtistRealmProxyInterface {
    public int realmGet$ID();
    public void realmSet$ID(int value);
    public String realmGet$name();
    public void realmSet$name(String value);
    public String realmGet$photoRes();
    public void realmSet$photoRes(String value);
    public int realmGet$photoResInt();
    public void realmSet$photoResInt(int value);
    public String realmGet$photoResUri();
    public void realmSet$photoResUri(String value);
    public String realmGet$origin();
    public void realmSet$origin(String value);
    public String realmGet$bio();
    public void realmSet$bio(String value);
    public boolean realmGet$isFavorite();
    public void realmSet$isFavorite(boolean value);
}
