package ru.beniocoder.kitstart.join;

import java.util.concurrent.CompletableFuture;

public interface JoinRepository {

    CompletableFuture<Boolean> isJoinedBefore(String name);

    CompletableFuture<Void> setJoinedBefore(String name);

}