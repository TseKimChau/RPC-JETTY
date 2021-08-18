package com.kimchau.rpc.server;

import com.kimchau.rpc.codec.Decoder;
import com.kimchau.rpc.codec.Encoder;
import com.kimchau.rpc.codec.impl.JsonDecoder;
import com.kimchau.rpc.codec.impl.JsonEncoder;
import com.kimchau.rpc.transport.TransportServer;
import com.kimchau.rpc.transport.impl.HttpTransportServer;
import lombok.Data;

@Data
public class RPCServerConfig {

    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private int port = 3000;
}
