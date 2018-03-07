package lyc;

import twitter4j.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TwitAsynConnection implements AsynConnection{
    private Twitter twitter;

    protected  TwitAsynConnection(Twitter t){
        twitter = t;
    }



    @Override
    public CompletableFuture<List<Result>> SearchTweet(String keyword, int max_cnt) {
        CompletableFuture<List<Result>> ret = new CompletableFuture<>();
        final int finalMax_cnt;
        if (max_cnt < 0)
            finalMax_cnt = Integer.MAX_VALUE;
        else
            finalMax_cnt = max_cnt;

        new Thread(() -> {
            ArrayList<Result> results = new ArrayList<>();
            Query query = new Query(keyword);
            try {
                QueryResult q_result = twitter.search(query);
                List<Status> statuses = q_result.getTweets();

                int i = 0;
                for (Status status : statuses) {
                    Result result = new Result(status.getUser().getName(), status.getUser().getId());
                    result.setScreen_name(status.getUser().getScreenName());
                    result.setHyperlink(status.getUser().getURL());
                    result.setText(status.getText());

                    results.add(result);
                    if (++i == finalMax_cnt)
                        break;
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }

            ret.complete(results);
        }).start();

        return ret;
    }

    @Override
    public CompletableFuture<List<Result>> getHomeLine() {
        CompletableFuture<List<Result>> ret = new CompletableFuture<>();

        new Thread( () -> {
            ArrayList<Result> results = new ArrayList<>();

            try {
                List<Status> statues = twitter.getHomeTimeline();
                for(Status status : statues){
                    Result result = new Result(status.getUser().getName(), status.getUser().getId());
                    result.setScreen_name(status.getUser().getScreenName());
                    result.setHyperlink(status.getUser().getURL());
                    result.setText(status.getText());
                }

            } catch (TwitterException e) {
                e.printStackTrace();
            }

            ret.complete(results);
        }).start();

        return ret;
    }

    @Override
    public CompletableFuture<List<Result>> SearchTweet(String keyword) {
        return SearchTweet(keyword, -1);
    }

    public CompletableFuture<List<Result>> getUserLatestTweet(long user_id){
        CompletableFuture<List<Result>> ret = new CompletableFuture<>();

        new Thread( () -> {
            ArrayList<Result> results = new ArrayList<>();

            try {
                List<Status> statues = twitter.getUserTimeline(user_id);
                for(Status status : statues){
                    Result result = new Result(status.getUser().getName(), status.getUser().getId());
                    result.setScreen_name(status.getUser().getScreenName());
                    result.setHyperlink(status.getUser().getURL());
                    result.setText(status.getText());
                }
            } catch (TwitterException e) {
                e.printStackTrace();
            }

            ret.complete(results);
        }).start();

        return ret;
    }


}
