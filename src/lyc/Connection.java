package lyc;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Connection{
    public List<Item> SearchPost(String keyword);
    public List<Item> SearchPost(String keyword, int max_cnt);
    public List<Item> getSelfHomeLine();
    public List<Item> getHomeLineById(long id);
}

