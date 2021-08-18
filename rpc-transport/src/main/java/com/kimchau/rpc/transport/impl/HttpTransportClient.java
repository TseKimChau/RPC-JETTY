package com.kimchau.rpc.transport.impl;

import com.kimchau.rpc.proto.Peer;
import com.kimchau.rpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTransportClient implements TransportClient {

    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        // 设置是否从httpUrlConnection读入，默认情况下是true
        httpURLConnection.setDoInput(true);
        // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true, 默认情况下是false;
        httpURLConnection.setDoOutput(true);
        // Post 请求不能使用缓存
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.connect();
        IOUtils.copy(data, httpURLConnection.getOutputStream());

        int result = httpURLConnection.getResponseCode();
        if(result == HttpURLConnection.HTTP_OK) {
            return httpURLConnection.getInputStream();
        } else {
            return httpURLConnection.getErrorStream();
        }
    }

    @Override
    public void close() {

    }
}
