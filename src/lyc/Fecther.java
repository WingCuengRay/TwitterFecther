package lyc;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Fecther {

    public static void main(String[] args) throws Exception {
        String []auths = new String[]{
                "WUZeAUiJyJFySY2I5C7oTkaRB",
                "QcmbvjQrSxLscxtZP6PndCYVEXxgBOoZ5g8ryvJLBYAmDTtrPx",
                "2965074672-HcndnMSZkdDKNqF1vqoERR1nynKLnKKqhMovkw4",
                "mue5UQ1QWSwGWDgf1lDTnrSeLJFVJLZltQxdyL34u0C0a"
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

