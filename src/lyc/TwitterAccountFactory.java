package lyc;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAccountFactory implements AccountFactory {
    @Override
    public AsynConnection createAccount(String []auths){
        if(auths.length != 4)
            return null;

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(auths[0])
                .setOAuthConsumerSecret(auths[1])
                .setOAuthAccessToken(auths[2])
                .setOAuthAccessTokenSecret(auths[3]);

        TwitterFactory t_factory = new TwitterFactory(cb.build());
        Twitter twitter = t_factory.getInstance();

        if(twitter == null)
            return null;
        else
            return new TwitAsynConnection(twitter);
    }
}
