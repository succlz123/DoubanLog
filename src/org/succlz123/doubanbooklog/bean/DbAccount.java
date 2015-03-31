package org.succlz123.doubanbooklog.bean;

/**
 * Created by fashi on 2015/3/25.
 */
public class DbAccount {

    private String access_token;
    private String douban_user_name;
    private int douban_user_id;
    private int expires_in;
    private String refresh_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getDouban_user_name() {
        return douban_user_name;
    }

    public void setDouban_user_name(String douban_user_name) {
        this.douban_user_name = douban_user_name;
    }

    public int getDouban_user_id() {
        return douban_user_id;
    }

    public void setDouban_user_id(int douban_user_id) {
        this.douban_user_id = douban_user_id;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
