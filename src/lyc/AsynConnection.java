package lyc;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AsynConnection {
    public CompletableFuture<List<Result>> SearchTweet(String keyword);
    public CompletableFuture<List<Result>> SearchTweet(String keyword, int max_cnt);
    public CompletableFuture<List<Result>> getHomeLine();
}

