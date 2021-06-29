package com.planning.springbean.applicationcontext;

import java.util.Locale;

/**
 * @author yxc
 * @date 2021/6/10 14:53
 */
public class Dialog {

    private Locale locale;
    private String hello;
    private String thanks;

    public Dialog() {
    }

    public Dialog(Locale locale, String hello, String thanks) {
        this.locale = locale;
        this.hello = hello;
        this.thanks = thanks;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }
}
