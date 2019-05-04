package hello.api;

import hello.utils.CommonUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


public class BaseAPI {

    private static String API_HOSTS = "https://gw.api.alibaba.com/openapi/";

    // TODO need to change to your own digital signature
    private static String digital_signature = "3OyKsi7yCBSr";
    // TODO need to change it
    private static String APP_KEY = "63414";

    private String protocol;
    private int apiVersion;
    private String apiNamespace;
    private String apiName;

    private Map<String, String> paramMap;

    // is need Aop Signature
    public boolean needAopSignature;

    // is post method request
    public boolean isPostRequest;

    public void setIsPostRequest(boolean isPostRequest) {
        this.isPostRequest = isPostRequest;
    }

    public BaseAPI() {

    }

    public BaseAPI(String[] args) {
        this.protocol = args[0];
        this.apiVersion = Integer.parseInt(args[1]);
        this.apiNamespace = args[2];
        this.apiName = args[3];
    }

    public String buildInvokeUrlPath() {
        return protocol + "/" + apiVersion + "/" + apiNamespace + "/" + apiName + "/" + APP_KEY;
    }

    public String getRequestUrl() {
        return API_HOSTS + buildInvokeUrlPath();
    }

    public void setParamMap(Map<String, String> map) {
        this.paramMap = map;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setNeedAopSignature() {
        this.needAopSignature = true;
    }

    public String getRequestData() {
        StringBuffer sb = new StringBuffer();
        if (paramMap != null && paramMap.keySet() != null) {
            for (String key : paramMap.keySet()) {
                sb.append(key).append("=").append(paramMap.get(key)).append("&");
            }
        } else {
            sb.append("?");
        }

        if (needAopSignature) {
            sb.append("_aop_signature").append("=").append(CommonUtil.signatureWithParamsAndUrlPath(buildInvokeUrlPath(),
                    paramMap,
                    digital_signature));
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
        org.apache.log4j.Logger.getInstance(BaseAPI.class).debug(getRequestUrl() + "?" + getRequestData());
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
        org.apache.log4j.Logger.getInstance(BaseAPI.class).debug(getRequestUrl() + "?" + getRequestData());
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
