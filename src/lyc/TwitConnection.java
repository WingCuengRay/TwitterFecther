package lyc;

import twitter4j.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TwitConnection implements Connection {
    private Twitter twitter;

    protected TwitConnection(Twitter t) {
        twitter = t;
    }


    @Override
    public List<Item> SearchPost(String keyword, int max_cnt) {
        final int finalMax_cnt;

        ArrayList<Item> results = new ArrayList<>();
        Query query = new Query(keyword);
        try {
            QueryResult q_result = twitter.search(query);
            List<Status> statuses = q_result.getTweets();

            int i = 0;
            for (Status status : statuses) {
                Item result = new Item(status.getUser().getName(), status.getUser().getId());
                result.setScreen_name(status.getUser().getScreenName());
                result.setHyperlink(status.getUser().getURL());
                result.setText(status.getText());

                results.add(result);
                if (++i == max_cnt)
                    break;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            return null;
        }


        return results;
    }

    @Override
    public List<Item> getSelfHomeLine() {
        CompletableFuture<List<Item>> ret = new CompletableFuture<>();

        ArrayList<Item> results = new ArrayList<>();

        try {
            List<Status> statues = twitter.getHomeTimeline();
            for (Status status : statues) {
                Item result = new Item(status.getUser().getName(), status.getUser().getId());
                result.setScreen_name(status.getUser().getScreenName());
                result.setHyperlink(status.getUser().getURL());
                result.setText(status.getText());
            }

        } catch (TwitterException e) {
            e.printStackTrace();
            return null;
        }

        return results;
    }

    @Override
    public List<Item> SearchPost(String keyword) {
        return SearchPost(keyword, -1);
    }

    @Override
    public List<Item> getHomeLineById(long user_id) {

        ArrayList<Item> results = new ArrayList<>();

        try {
            List<Status> statues = twitter.getUserTimeline(user_id);
            for (Status status : statues) {
                Item result = new Item(status.getUser().getName(), status.getUser().getId());
                result.setScreen_name(status.getUser().getScreenName());
                result.setHyperlink(status.getUser().getURL());
                result.setText(status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            return null;
        }

        return results;
    }


}
