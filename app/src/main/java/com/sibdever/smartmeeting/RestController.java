package com.sibdever.smartmeeting;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sibdever.smartmeeting.data.ResponseStatus;
import com.sibdever.smartmeeting.data.request.LoginRequest;
import com.sibdever.smartmeeting.data.request.RegisterRequest;
import com.sibdever.smartmeeting.data.response.AuthResponse;
import com.sibdever.smartmeeting.data.response.LoginResponse;
import com.sibdever.smartmeeting.data.response.RegisterResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestController {

    private static final String COOKIES_HEADER = "Set-Cookie";
    private final CookieManager cookieManager = new CookieManager();

    private static RestController INSTANCE;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private RestController() {
    }

    public static RestController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RestController();
        return INSTANCE;
    }

    private HttpURLConnection openConnection(String url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(method);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }

    public void login(LoginRequest request, MutableLiveData<LoginResponse> liveDataForResult) {
        executorService.execute(() -> {
            try {

                HttpURLConnection connection = openConnection(
                        "http://smart-meetings.herokuapp.com/auth/login",
                        "POST");

                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write("email=" + request.getEmail() + "&password=" + request.getPassword());
                writer.flush();

                String response =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()))
                                .readLine();
                Log.d("Sibdever", "login: response " + response);

                Map<String, List<String>> headerFields = connection.getHeaderFields();
                List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

                HttpCookie httpCookie = null;

                if (cookiesHeader != null) {
                    for (String cookie : cookiesHeader) {
                        Log.d("Sibdever", "login: " + cookie);
                        httpCookie = HttpCookie.parse(cookie).get(0);
                        cookieManager.getCookieStore().add(null, httpCookie);
                    }
                }

                connection.disconnect();

                liveDataForResult.postValue(new LoginResponse(ResponseStatus.valueOf(response), httpCookie));
            } catch (IOException e) {
                e.printStackTrace();
                liveDataForResult.postValue(new LoginResponse(ResponseStatus.ERROR, null));
            }
        });
    }

    public void register(RegisterRequest request, MutableLiveData<RegisterResponse> liveDataForResult) {
        executorService.execute(() -> {
            try {
                HttpURLConnection connection = openConnection(
                        "http://smart-meetings.herokuapp.com/auth/register",
                        "POST");

                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write("email=" + request.getEmail()
                        + "&password=" + request.getPassword()
                        + "&name=" + request.getUsername());
                writer.flush();

                String response =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()))
                                .readLine();
                Log.d("Sibdever", "register: response " + response);

                Map<String, List<String>> headerFields = connection.getHeaderFields();
                List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

                HttpCookie httpCookie = null;

                if (cookiesHeader != null) {
                    for (String cookie : cookiesHeader) {
                        Log.d("Sibdever", "register cookie: " + cookie);
                        httpCookie = HttpCookie.parse(cookie).get(0);
                        cookieManager.getCookieStore().add(null, httpCookie);
                    }
                }

                connection.disconnect();

                liveDataForResult.postValue(new RegisterResponse(ResponseStatus.valueOf(response), httpCookie));

            } catch (IOException exception) {
                exception.printStackTrace();
                liveDataForResult.postValue(new RegisterResponse(ResponseStatus.ERROR, null));
            }
        });
    }

    public void tryAuth(MutableLiveData<AuthResponse> liveDataForResult, String token) {
        executorService.execute(() -> {
            try {
                HttpURLConnection connection = openConnection(
                        "http://smart-meetings.herokuapp.com/auth/authenticate",
                        "POST");

                connection.setRequestProperty("Cookie", "token=" + token);
                Log.d("Sibdever", "tryAuth: " + connection.getRequestProperty("Cookie"));

                String response =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()))
                                .readLine();
                Log.d("Sibdever", "auth: response " + response);

                AuthResponse authResponse = new AuthResponse(ResponseStatus.valueOf(response));

                liveDataForResult.postValue(authResponse);

            } catch (IOException e) {
                e.printStackTrace();
                liveDataForResult.postValue(new AuthResponse(ResponseStatus.ERROR));
            }
        });
    }
}