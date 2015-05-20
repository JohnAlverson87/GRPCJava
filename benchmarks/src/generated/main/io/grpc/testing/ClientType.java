// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: qpstest.proto

package io.grpc.testing;

/**
 * Protobuf enum {@code grpc.testing.ClientType}
 */
public enum ClientType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>SYNCHRONOUS_CLIENT = 0;</code>
   */
  SYNCHRONOUS_CLIENT(0, 0),
  /**
   * <code>ASYNC_CLIENT = 1;</code>
   */
  ASYNC_CLIENT(1, 1),
  UNRECOGNIZED(-1, -1),
  ;

  /**
   * <code>SYNCHRONOUS_CLIENT = 0;</code>
   */
  public static final int SYNCHRONOUS_CLIENT_VALUE = 0;
  /**
   * <code>ASYNC_CLIENT = 1;</code>
   */
  public static final int ASYNC_CLIENT_VALUE = 1;


  public final int getNumber() {
    if (index == -1) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  public static ClientType valueOf(int value) {
    switch (value) {
      case 0: return SYNCHRONOUS_CLIENT;
      case 1: return ASYNC_CLIENT;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ClientType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static com.google.protobuf.Internal.EnumLiteMap<ClientType>
      internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ClientType>() {
          public ClientType findValueByNumber(int number) {
            return ClientType.valueOf(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(index);
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return io.grpc.testing.QpsTestProto.getDescriptor()
        .getEnumTypes().get(1);
  }

  private static final ClientType[] VALUES = values();

  public static ClientType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int index;
  private final int value;

  private ClientType(int index, int value) {
    this.index = index;
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:grpc.testing.ClientType)
}

