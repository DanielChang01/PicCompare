package com.yuan.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by danielchang on 2018/2/9.
 */
public class HumanUtils {

    private static final String NONE_RETURN_ATTRS = "none";
    //以下三个改成配置文件，将取消
    private static final String URL = "";
    private static final String API_KEY = "";
    private static final String API_SERCET = "";

    public String uploadPic(File imageFile) throws IOException {
        return this.uploadPic(imageFile,NONE_RETURN_ATTRS);
    }

    public String uploadPic(File imageFile, String returnAttrs) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(URL);
        HttpEntity streamEntity = null;

        streamEntity = getMultiStreamEntity(imageFile, returnAttrs);
        httpPost.setEntity(streamEntity);
        response = httpClient.execute(httpPost);
        if(200 != response.getStatusLine().getStatusCode()) {
            throw new RuntimeException("http response code : " + response.getStatusLine().getStatusCode());
        }
        commonUtils.closeHttpClient(httpClient);
        commonUtils.closeResponse(response);
        HttpEntity entity = response.getEntity();

        return null;
    }


    protected HttpEntity getMultiStreamEntity(File imageFile, String returnAttrs) throws FileNotFoundException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("image_file", imageFile);
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);
        builder.addTextBody("return_attributes",returnAttrs);
        builder.addTextBody("api_key", API_KEY);
        builder.addTextBody("api_secret", API_SERCET);
        return builder.build();
    }

    protected static class commonUtils {
        public static void closeResponse(CloseableHttpResponse response){
            if (response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void closeHttpClient(CloseableHttpClient httpClient) {
            if (httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
