package com.accenture.socialnetwork.Services.RelationshipService;

public interface RelationshipService {

    String addToFriend(int fromUserId, int toUserId);

    boolean deleteFriend(int fromUserId, int toUserId);

    boolean acceptUser(int fromUserId, int toUserId);

    boolean declineUser(int fromUserId, int toUserId);

    boolean blockUser(int fromUserId, int toUserId);

}
