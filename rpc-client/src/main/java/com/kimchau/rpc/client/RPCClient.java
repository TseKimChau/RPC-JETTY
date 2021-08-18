package com.kimchau.rpc.client;

import com.kimchau.rpc.codec.Decoder;
import com.kimchau.rpc.codec.Encoder;
import com.kimchau.rpc.common.ReflectionUtils;
import com.kimchau.rpc.proto.RPCRequest;
import com.kimchau.rpc.proto.RPCResponse;
import com.kimchau.rpc.proto.ServiceDescription;
import com.kimchau.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@Slf4j
public class RPCClient {

    private RPCClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RPCClient() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this(new RPCClientConfig());
    }

    public RPCClient(RPCClientConfig config) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());

        this.selector.init(this.config.getSevers(), this.config.getConnectCount(), this.config.getTransportClass());
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args) ->{
                    RPCResponse response = new RPCResponse();
                    RPCRequest request = new RPCRequest();
                    request.setServiceDescription(ServiceDescription.from(clazz, method));
                    request.setParameters(args);
                    // 通过网络传输获得response
                    TransportClient client = selector.select();
                    byte[] outBytes = encoder.encoder(request);
                    InputStream recive = client.write(new ByteArrayInputStream(outBytes));
                    byte[] inBytes = IOUtils.readFully(recive, recive.available());
                    response = decoder.decoder(inBytes, RPCResponse.class);
                    log.info("获得返回值成功：{}", response);
                    // 释放连接
                    selector.release(client);
                    return response.getData();
                });
    }

}
