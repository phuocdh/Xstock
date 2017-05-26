package io.realm;


public interface UserDetailRealmProxyInterface {
    public String realmGet$email();
    public void realmSet$email(String value);
    public String realmGet$password();
    public void realmSet$password(String value);
    public String realmGet$username();
    public void realmSet$username(String value);
    public String realmGet$firstname();
    public void realmSet$firstname(String value);
    public String realmGet$lastname();
    public void realmSet$lastname(String value);
    public String realmGet$sexid();
    public void realmSet$sexid(String value);
    public int realmGet$is_active();
    public void realmSet$is_active(int value);
}
