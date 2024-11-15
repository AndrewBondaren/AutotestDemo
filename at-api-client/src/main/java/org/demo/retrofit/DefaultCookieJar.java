package org.demo.retrofit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(staticName = "create")
public class DefaultCookieJar implements CookieJar {

    @Getter
    private final Map<String, Cookie> cookieMap = new HashMap<>();

    @Override
    public void saveFromResponse(final HttpUrl httpUrl, final List<Cookie> list) {
        if (httpUrl.url().toString().contains("logout")) {
            cookieMap.clear();
        }
        list.forEach(cookie ->
                cookieMap.put(
                        cookie.name(),
                        createCookie(httpUrl.host(), cookie.name(), cookie.value())));
    }

    @Override
    public List<Cookie> loadForRequest(final HttpUrl httpUrl) {
        return new ArrayList<>(cookieMap.values());
    }

    private Cookie createCookie(final String domain, final String name, final String value) {
        return new Cookie.Builder().domain(domain).name(name).value(value).build();
    }

}
