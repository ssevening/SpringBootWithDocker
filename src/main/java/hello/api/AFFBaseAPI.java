package hello.api;


import hello.utils.SignUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;


public class AFFBaseAPI {

    public static String API_HOSTS = "https://api.taobao.com/router/rest";

    // TODO need to change to your own digital signature
    public static String AFF_SIGNATURE = "d53d412b17e29737404b681c6af0fe8c";
    // TODO need to change it
    public static String AFF_APP_KEY = "31349226";
    public static String TRACKING_ID = "API_DEMO";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private Map<String, String> paramMap;

    // is need Aop Signature
    public boolean needAopSignature = true;

    // is post method request
    public boolean isPostRequest = true;

    public void setIsPostRequest(boolean isPostRequest) {
        this.isPostRequest = isPostRequest;
    }

    public AFFBaseAPI() {

    }


    public String getTopMethodName() {
        return "";
    }

    public String getRequestUrl() {
        return API_HOSTS;
    }


    public void setParamMap(Map<String, String> map) {
        this.paramMap = map;
        this.paramMap.put("partner_id", "top-apitools");
        this.paramMap.put("format", "json");
        this.paramMap.put("app_key", AFF_APP_KEY);
        this.paramMap.put("sign_method", "hmac");
        this.paramMap.put("timestamp", sdf.format(Calendar.getInstance().getTime()));
        this.paramMap.put("v", "2.0");
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setNeedAopSignature() {
        this.needAopSignature = true;
    }


    public String getRequestData() throws IOException {
        StringBuffer sb = new StringBuffer(SignUtils.buildQuery(paramMap, "UTF-8"));
        if (needAopSignature) {
            sb.append("&" + Constants.SIGN).append("=").append(SignUtils.signTopRequest(paramMap, AFF_SIGNATURE, Constants.SIGN_METHOD_HMAC));
        }
        return sb.toString();
    }

    public String request() throws Exception {
        if (isPostRequest) {
            return doPost();
        } else {
            return doGet();
        }
    }

    /**
     * Post Request
     *
     * @return
     * @throws Exception
     */
    private String doPost() throws Exception {
        String parameterData = getRequestData();
        URL localURL = new URL(getRequestUrl());
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterData.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(parameterData.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is "
                        + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

    private String doGet() throws Exception {
        URL localURL = new URL(getRequestUrl() + "?" + getRequestData());
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

}
