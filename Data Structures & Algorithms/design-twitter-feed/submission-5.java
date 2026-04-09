/*
 - "Twitter", 
 - "postTweet" [1, 10]
 - "postTweet" [2, 20], 
 - "getNewsFeed" [1], 
 - "getNewsFeed" [2], 
 - "follow" [1, 2], 
 - "getNewsFeed" [1], 
 - "getNewsFeed" [2], 
 - "unfollow" [1, 2], 
 - "getNewsFeed" [1]
*/

class Twitter {

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

        this.userTweets.computeIfAbsent(userId, _k -> new ArrayList<>());
        this.userTweets.get(userId).add(newTweet);

        // keep only 10 most recent tweets for this user
        /*while (this.userTweets.get(userId).size() > 10) {
            this.userTweets.get(userId).remove(0);
        }*/

        // add the tweet to the followers' news feeds
        this.userFollowers.computeIfAbsent(userId, _k -> createFollowersList(userId));
        for (int followerId : this.userFollowers.get(userId)) {
            this.userFeed.computeIfAbsent(followerId, _k -> createEmptyFeed());
            this.userFeed.get(followerId).offer(newTweet);
            while (this.userFeed.get(followerId).size() > 10) {
                this.userFeed.get(followerId).poll();
            }
        }
    }
    
    public List<Integer> getNewsFeed(int userId) {
        this.userFeed.computeIfAbsent(userId, _k -> createEmptyFeed());
        var tweets = new ArrayList<Integer>();
        var copy = new PriorityQueue<>(this.userFeed.get(userId));
        while (copy.size() > 0) {
            var tweet = copy.poll();
            tweets.add(0, tweet.tweetId);
        }
        return tweets;
    }
    
    public void follow(int followerId, int followeeId) {
        this.userFollowees.computeIfAbsent(followerId, _k -> createFollowersList(followerId));

        if (followerId == followeeId
            || this.userFollowees.get(followerId).contains(followeeId)) {
            return;
        }

        this.userFollowers.computeIfAbsent(followeeId, _k -> createFollowersList(followeeId));

        this.userFollowers.get(followeeId).add(followerId);
        this.userFollowees.get(followerId).add(followeeId);
        
        // add the tweers from the followee to the follower fee
        this.userTweets.computeIfAbsent(followeeId, _k -> new ArrayList<>());
        this.userFeed.computeIfAbsent(followerId, _k -> createEmptyFeed());
        for (Tweet tweet : this.userTweets.get(followeeId)) {
            this.userFeed.get(followerId).offer(tweet);
            while (this.userFeed.get(followerId).size() > 10) {
                this.userFeed.get(followerId).poll();
            }
        }
    }
    
    public void unfollow(int followerId, int followeeId) {
        this.userFollowers.computeIfAbsent(followeeId, _k -> createFollowersList(followeeId));
        this.userFollowees.computeIfAbsent(followerId, _k -> createFollowersList(followerId));

        if (followerId == followeeId
            || !this.userFollowees.get(followerId).contains(followeeId)) {
            return;
        }

        this.userFollowers.get(followeeId).remove(followerId);
        this.userFollowees.get(followerId).remove(followeeId);

        this.userFeed.computeIfAbsent(followerId, _k -> createEmptyFeed());
        this.userFeed.get(followerId).clear();
        for (int userFolloweeId : this.userFollowees.get(followerId)) {
            this.userTweets.computeIfAbsent(userFolloweeId, _k -> new ArrayList<>());
            for (Tweet tweet : this.userTweets.get(userFolloweeId)) {
                this.userFeed.get(followerId).offer(tweet);
                while (this.userFeed.get(followerId).size() > 10) {
                    this.userFeed.get(followerId).poll();
                }
            }
        }
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
