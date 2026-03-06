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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiHttpService extends Service {

    public static final MediaType JSON_MEDIA_TYPE =
            MediaType.parse("application/json; charset=utf-8");

    private static final Logger log = LoggerFactory.getLogger(HttpService.class);

    private OkHttpClient httpClient;

    private List<String> urls = new ArrayList<>();

    private HashMap<String, String> headers = new HashMap<>();

    public MultiHttpService(List<String> urls, OkHttpClient httpClient) {
        super();
        if (urls == null || urls.isEmpty()) {
            throw new IllegalArgumentException("endpoints cannot be empty");
        }
        this.urls.addAll(urls);
        this.httpClient = httpClient;
    }

    public MultiHttpService(List<String> urls) {
        this(urls, createOkHttpClient());
    }

    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        final OkHttpClient.Builder builder =
                new OkHttpClient.Builder()
                        .connectTimeout(Duration.ofSeconds(10))
                        .readTimeout(Duration.ofSeconds(60));
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
        IOException lastEx = null;
        for (String url : urls) {
            try {
                return doRequest(url, request);
            } catch (IOException e) {
                lastEx = e;
                log.warn("[FailoverHttpService] Request failed on {}: {}", url, e.getMessage());
            }
        }
        throw new ClientConnectionException(
                "All RPC endpoints failed: " + urls, lastEx);
    }

    private InputStream doRequest(String url, String request) throws IOException {
        RequestBody requestBody = RequestBody.create(request, JSON_MEDIA_TYPE);
        Headers headers = buildHeaders();

        Request httpRequest =
                new Request.Builder().url(url).headers(headers).post(requestBody).build();
        try (Response response = httpClient.newCall(httpRequest).execute()) {
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

    public List<String> getUrls() {
        return urls;
    }

    @Override
    public void close() throws IOException {}

}
