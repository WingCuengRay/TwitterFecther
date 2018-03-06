import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class TwitterFecther {
    public TwitterFecther(){



    }


    public static void main(String[] args) throws Exception{
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("your key")
                .setOAuthConsumerSecret("your secret")
                .setOAuthAccessToken("your key")
                .setOAuthAccessTokenSecret("your secret");

        TwitterFactory t_facotry = new TwitterFactory(cb.build());
        Twitter twitter = t_facotry.getInstance();

        System.out.println("Here is the result !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Query query = new Query("hello");
       showTweetSearch(twitter, query);
    }

    private static QueryResult showTweetSearch(Twitter twitter, Query query) throws Exception{
        QueryResult result = twitter.search(query);
        System.out.println("Tweet count: " + result.getCount());
        List<Status> statues = result.getTweets();
        for(Status status : statues){
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }

        return result;
    }

    private List<Status> showHomeLine(Twitter twitter) throws Exception{
        List<Status> statues = twitter.getHomeTimeline();

        System.out.println("Showing home timeline.");
        for(Status status: statues){
            System.out.println(status.getUser().getName() + ": " + status.getText());
        }
        return statues;
    }
}
