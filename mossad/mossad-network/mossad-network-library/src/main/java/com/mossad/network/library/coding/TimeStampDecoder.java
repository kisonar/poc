/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mossad.network.library.coding;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import com.mossad.network.library.LoopBackTimeStamp;

import java.util.List;

/**
 *
 * @author mmigdal
 */
public class TimeStampDecoder extends ByteToMessageDecoder {
 
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    final int messageLength = Long.SIZE/Byte.SIZE *2;
    if (in.readableBytes() < messageLength) {
      return;
    }
     
    byte [] ba = new byte[messageLength];
    in.readBytes(ba, 0, messageLength);  // block until read 16 bytes from sockets
    LoopBackTimeStamp loopBackTimeStamp = new LoopBackTimeStamp();
    loopBackTimeStamp.fromByteArray(ba);
    out.add(loopBackTimeStamp);
  }
}
