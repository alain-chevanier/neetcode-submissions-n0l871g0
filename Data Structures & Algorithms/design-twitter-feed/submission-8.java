class Twitter {

    static final int MAX_NEWS_FEED_SIZE = 10;

    Map<Integer,Set<Integer>> userFollowers;
    Map<Integer,Set<Integer>> userFollowees;
    Map<Integer,List<Tweet>> userTweets;
    Map<Integer,PriorityQueue<Tweet>> userFeed;
    int timestamp;

    public Twitter() {
        this.userFollowers = new HashMap<>();
        this.userFollowees = new HashMap<>();
        this.userTweets = new HashMap<>();
        this.userFeed = new HashMap<>();
        this.timestamp = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId, userId, this.timestamp++);

        getUserTweets(userId).add(newTweet);

        // keep only 10 most recent tweets for this user
        /*while (this.userTweets.get(userId).size() > 10) {
            this.userTweets.get(userId).remove(0);
        }*/

        // add the tweet to the followers' news feeds
        for (int followerId : getUserFollowers(userId)) {
            addTweetToUserFeed(newTweet, followerId);
        }
    }
    
    public List<Integer> getNewsFeed(int userId) {
        var copy = new PriorityQueue<>(getUserNewsFeed(userId));
        var tweets = new ArrayList<Integer>(copy.size());
        while (copy.size() > 0) {
            var tweet = copy.poll();
            tweets.add(0, tweet.tweetId);
        }
        return tweets;
    }
    
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId
            || getUserFollowees(followerId).contains(followeeId)) {
            return;
        }

        getUserFollowers(followeeId).add(followerId);
        getUserFollowees(followerId).add(followeeId);
        
        // add the tweets from the followee to the follower news feed
        for (Tweet tweet : getUserTweets(followeeId)) {
            addTweetToUserFeed(tweet, followerId);
        }
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId
            || !getUserFollowees(followerId).contains(followeeId)) {
            return;
        }

        getUserFollowers(followeeId).remove(followerId);
        getUserFollowees(followerId).remove(followeeId);

        getUserNewsFeed(followerId).clear();
        for (int followedUserId : getUserFollowees(followerId)) {
            for (Tweet tweet : getUserTweets(followedUserId)) {
                addTweetToUserFeed(tweet, followerId);
            }
        }
    }

    List<Tweet> getUserTweets(int userId) {
        this.userTweets.computeIfAbsent(userId, _k -> new ArrayList<>());
        return this.userTweets.get(userId);
    }

    Set<Integer> getUserFollowers(int userId) {
        this.userFollowers.computeIfAbsent(userId, _k -> createFollowersList(userId));
        return this.userFollowers.get(userId);
    }

    Set<Integer> getUserFollowees(int userId) {
        this.userFollowees.computeIfAbsent(userId, _k -> createFollowersList(userId));
        return this.userFollowees.get(userId);
    }

    void addTweetToUserFeed(Tweet tweet, int userId) {
        getUserNewsFeed(userId).offer(tweet);
        while (getUserNewsFeed(userId).size() > MAX_NEWS_FEED_SIZE) {
            this.userFeed.get(userId).poll();
        }
    }

    PriorityQueue<Tweet> getUserNewsFeed(int userId) {
        this.userFeed.computeIfAbsent(userId, _k -> createEmptyFeed());
        return this.userFeed.get(userId);
    }

    Set<Integer> createFollowersList(int userId) {
        var followers = new HashSet<Integer>();
        followers.add(userId);
        return followers;
    }

    PriorityQueue<Tweet> createEmptyFeed() {
        return new PriorityQueue<>((t1, t2) -> t1.timestamp - t2.timestamp);
    }
}

class Tweet {
    int tweetId;
    int userId;
    int timestamp;

    Tweet(int tweetId, int userId, int timestamp) {
        this.tweetId = tweetId;
        this.userId = userId;
        this.timestamp = timestamp;
    }
}
