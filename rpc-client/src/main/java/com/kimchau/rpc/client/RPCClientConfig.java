package com.kimchau.rpc.client;

import com.kimchau.rpc.client.impl.RandomTransportSelector;
import com.kimchau.rpc.codec.Decoder;
import com.kimchau.rpc.codec.Encoder;
import com.kimchau.rpc.codec.impl.JsonDecoder;
import com.kimchau.rpc.codec.impl.JsonEncoder;
import com.kimchau.rpc.proto.Peer;
import com.kimchau.rpc.transport.TransportClient;
import com.kimchau.rpc.transport.impl.HttpTransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RPCClientConfig {

    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> severs = Arrays.asList(new Peer("127.0.0.1", 3000));

}
