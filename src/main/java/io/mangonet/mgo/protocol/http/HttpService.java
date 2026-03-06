package io.mangonet.mgo.protocol.http;

import io.mangonet.mgo.protocol.Service;
import io.mangonet.mgo.protocol.exceptions.ClientConnectionException;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HttpService extends Service {

    public static final MediaType JSON_MEDIA_TYPE =
            MediaType.parse("application/json; charset=utf-8");

    public static final String DEFAULT_URL = "https://fullnode.mainnet.mgo.io:443";

    private static final Logger log = LoggerFactory.getLogger(HttpService.class);

    private OkHttpClient httpClient;

    private final String url;

    private HashMap<String, String> headers = new HashMap<>();

    public HttpService(String url, OkHttpClient httpClient) {
        super();
        this.url = url;
        this.httpClient = httpClient;
    }

    public HttpService(OkHttpClient httpClient) {
        this(DEFAULT_URL, httpClient);
    }

    public HttpService(String url) {
        this(url, createOkHttpClient());
    }

    public HttpService() {
        this(DEFAULT_URL);
    }

    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        final OkHttpClient.Builder builder =
                new OkHttpClient.Builder()
                        .connectTimeout(Duration.ofSeconds(30))
                        .readTimeout(Duration.ofSeconds(300));
        configureLogging(builder);
        return builder;
    }

    private static OkHttpClient createOkHttpClient() {
        return getOkHttpClientBuilder().build();
    }

    private static void configureLogging(OkHttpClient.Builder builder) {
        if (log.isDebugEnabled()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
    }

    @Override
    protected InputStream performIO(String request) throws IOException {

        RequestBody requestBody = RequestBody.create(request, JSON_MEDIA_TYPE);
        Headers headers = buildHeaders();

        okhttp3.Request httpRequest =
                new okhttp3.Request.Builder().url(url).headers(headers).post(requestBody).build();

        try (okhttp3.Response response = httpClient.newCall(httpRequest).execute()) {
            processHeaders(response.headers());
            ResponseBody responseBody = response.body();
            if (response.isSuccessful()) {
                if (responseBody != null) {
                    return buildInputStream(responseBody);
                } else {
                    return null;
                }
            } else {
                int code = response.code();
                String text = responseBody == null ? "N/A" : responseBody.string();

                throw new ClientConnectionException(
                        "Invalid response received: " + code + "; " + text);
            }
        }
    }

    protected void processHeaders(Headers headers) {
        // Default implementation is empty
    }

    private InputStream buildInputStream(ResponseBody responseBody) throws IOException {
        return new ByteArrayInputStream(responseBody.bytes());
    }

    private Headers buildHeaders() {
        return Headers.of(headers);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void addHeaders(Map<String, String> headersToAdd) {
        headers.putAll(headersToAdd);
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void close() throws IOException {}

}
