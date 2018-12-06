package com.tourong.app.base;

import com.uber.autodispose.AutoDisposeConverter;

public interface BaseView {
    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();
}
