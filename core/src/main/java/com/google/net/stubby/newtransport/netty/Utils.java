package com.google.net.stubby.newtransport.netty;

import static com.google.net.stubby.newtransport.HttpUtil.CONTENT_TYPE_HEADER;
import static com.google.net.stubby.newtransport.HttpUtil.CONTENT_TYPE_PROTORPC;
import static com.google.net.stubby.newtransport.HttpUtil.HTTP_METHOD;
import static io.netty.util.CharsetUtil.UTF_8;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.net.stubby.Metadata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;

import java.nio.ByteBuffer;
import java.util.Map;

import javax.inject.Provider;

/**
 * Common utility methods.
 */
class Utils {

  /**
   * Copies the content of the given {@link ByteBuffer} to a new {@link ByteBuf} instance.
   */
  static ByteBuf toByteBuf(ByteBufAllocator alloc, ByteBuffer source) {
    ByteBuf buf = alloc.buffer(source.remaining());
    buf.writeBytes(source);
    return buf;
  }

  public static Http2Headers convertHeaders(Metadata.Headers headers,
      boolean ssl,
      String defaultPath,
      String defaultAuthority) {
    Preconditions.checkNotNull(headers, "headers");
    Preconditions.checkNotNull(defaultPath, "defaultPath");
    Preconditions.checkNotNull(defaultAuthority, "defaultAuthority");

    DefaultHttp2Headers.Builder headersBuilder = DefaultHttp2Headers.newBuilder();

    // Add any application-provided headers first.
    byte[][] serializedHeaders = headers.serialize();
    for (int i = 0; i < serializedHeaders.length; i++) {
      String key = new String(serializedHeaders[i], UTF_8);
      String value = new String(serializedHeaders[++i], UTF_8);
      headersBuilder.add(key, value);
    }

    // Now set GRPC-specific default headers.
    headersBuilder
        .authority(defaultAuthority)
        .path(defaultPath)
        .method(HTTP_METHOD)
        .scheme(ssl? "https" : "http")
        .add(CONTENT_TYPE_HEADER, CONTENT_TYPE_PROTORPC);

    // Override the default authority and path if provided by the headers.
    if (headers.getAuthority() != null) {
      headersBuilder.authority(headers.getAuthority());
    }
    if (headers.getPath() != null) {
      headersBuilder.path(headers.getPath());
    }

    return headersBuilder.build();
  }

  public static ImmutableMap<String, Provider<String>> convertHeaders(Http2Headers headers) {
    ImmutableMap.Builder<String, Provider<String>> grpcHeaders =
        new ImmutableMap.Builder<String, Provider<String>>();
    for (Map.Entry<String, String> header : headers) {
      if (!header.getKey().startsWith(":")) {
        final String value = header.getValue();
        // headers starting with ":" are reserved for HTTP/2 built-in headers
        grpcHeaders.put(header.getKey(), new Provider<String>() {
          @Override
          public String get() {
            return value;
          }
        });
      }
    }
    return grpcHeaders.build();
  }

  private Utils() {
    // Prevents instantiation
  }
}
