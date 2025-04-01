package com.baiji.spi;

import com.baiji.common.AuthInfo;

/**
 * 进行登录态验证的SPI扩展点
 */
public interface Verification {
    <T extends AuthInfo> void verify(T t) throws Exception;
}
