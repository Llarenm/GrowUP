// data/repository/RepositoryCallback.java
package com.LlarenMorales.growupmobile.data.repository;

public interface RepositoryCallback<T> {
    void onSuccess(T result);
    void onError(String message);
}