package lyc;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Fecther {

    public static void main(String[] args) throws Exception {
        String []auths = new String[]{
                "xxx",
                "xxx",
                "xxx",
                "xxx"
        };

        TwitterAccountFactory t_factory = new TwitterAccountFactory();
        AsynConnection twitConnection =  t_factory.createAccount(auths);

        CompletableFuture<List<Result>> future_tweet = twitConnection.SearchTweet("hello", 10);
        List<Result> tweets = future_tweet.get();

        System.out.println("Count: " + tweets.size());
        for(Result tweet : tweets){
            System.out.println("name: " + tweet.getUser_name());
            System.out.println("screen name: " + tweet.getScreen_name());
            System.out.println("Id: " + tweet.getUser_id());
            System.out.println("text: " + tweet.getText());
            System.out.println("main page: " + tweet.getHyperlink());

            System.out.println("\n\n--------------------------------------------------------");
        }


    }
}

