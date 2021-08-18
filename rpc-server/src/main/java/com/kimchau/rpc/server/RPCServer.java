package com.kimchau.rpc.server;

import com.kimchau.rpc.codec.Decoder;
import com.kimchau.rpc.codec.Encoder;
import com.kimchau.rpc.common.ReflectionUtils;
import com.kimchau.rpc.proto.RPCRequest;
import com.kimchau.rpc.proto.RPCResponse;
import com.kimchau.rpc.transport.RequestHandler;
import com.kimchau.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class RPCServer {

    private RPCServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RPCServer() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this(new RPCServerConfig());
    }

    public RPCServer(RPCServerConfig config) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.config = config;
        // 网络模块
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), handler);
        // 序列化模块
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        // rpc service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        this.serviceManager.register(interfaceClass, bean);
    }

    public void start() throws Exception {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = (in, out) -> {
        RPCResponse response = new RPCResponse();
        try {
            byte[] inBytes = IOUtils.readFully(in, in.available());
            RPCRequest request = decoder.decoder(inBytes, RPCRequest.class);
            log.info("get rpc request: {}", request);
            ServiceInstance instance = serviceManager.lookup(request);
            Object res = serviceInvoker.invoke(instance, request);
            response.setData(res);
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
            response.setCode(500);
            response.setMessage("RPCServer got error: " + e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            response.setCode(500);
            response.setMessage("RPCServer got error: " + e.getMessage());
        } finally {
            byte[] outBytes = encoder.encoder(response);
            try {
                out.write(outBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
