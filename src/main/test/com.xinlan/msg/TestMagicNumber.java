package com.xinlan.msg;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xinlan.model.IMMsgWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

public class TestMagicNumber {
    @Test
    public void magicNumber(){
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.put((byte)'l');
        buf.put((byte)'u');
        buf.put((byte)'l');
        buf.put((byte)'u');

        buf.flip();
        int magicNumber = buf.getInt();
        System.out.println("magic number = " + magicNumber);

        ByteBuffer readBuf = ByteBuffer.allocate(4);
        readBuf.putInt(magicNumber);
        readBuf.flip();
        byte b0 = readBuf.get();
        byte b1 = readBuf.get();
        byte b2 = readBuf.get();
        byte b3 = readBuf.get();

        Assert.assertEquals(b0,'l');
        Assert.assertEquals(b1,'u');
        Assert.assertEquals(b2,'l');
        Assert.assertEquals(b3,'u');
    }

    @Test
    public void testProtobufModel() throws InvalidProtocolBufferException {
        IMMsgWrapper.IMMsg.Builder msgBuilder = IMMsgWrapper.IMMsg.newBuilder();
        IMMsgWrapper.IMMsg msg = msgBuilder.setMagicNumber(101).setVersion(1).setFrom("mycount").setMsgId("001")
                .setType(IMMsgWrapper.IMMsg.MsgType.CTL).build();
        byte[] msgRaw = msg.toByteArray();

        IMMsgWrapper.IMMsg newMsg = IMMsgWrapper.IMMsg.parseFrom(msgRaw);

        Assert.assertEquals(newMsg.getType() , IMMsgWrapper.IMMsg.MsgType.CTL);
        Assert.assertEquals(newMsg.getMsgId() , "001");
        Assert.assertEquals(newMsg.getMagicNumber() , 101);
        Assert.assertEquals(newMsg.getVersion() , 1);
        Assert.assertEquals(newMsg.getFrom() , "mycount");
    }
}
