package mossad.java.features.base.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public final class CompletableFutureAPIImprovements {

    public void check(){
        Executor executor = CompletableFuture.delayedExecutor(50L, TimeUnit.SECONDS);
    }




}
