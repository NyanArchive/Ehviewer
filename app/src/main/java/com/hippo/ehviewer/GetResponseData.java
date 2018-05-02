package com.hippo.ehviewer;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetResponseData {
    public static String getResponseData(Response response) {
        String data = null;
        ResponseBody body = response.body();
        try {
            if (body != null) {
                data = body.string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
