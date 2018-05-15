package com.mossad.network.library.coding;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.mossad.network.library.LoopBackTimeStamp;

/**
 *
 * @author mmigdal
 */
public class TimeStampEncoder extends MessageToByteEncoder<LoopBackTimeStamp> {

    @Override
    protected void encode(ChannelHandlerContext ctx, LoopBackTimeStamp msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toByteArray());

    }
}
