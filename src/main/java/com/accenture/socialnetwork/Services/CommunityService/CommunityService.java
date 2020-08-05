package com.accenture.socialnetwork.Services.CommunityService;

import com.accenture.socialnetwork.Entity.Community;
import com.accenture.socialnetwork.Entity.PostEntity;

public interface CommunityService {

    Community createCommunity(Community community);

    boolean createCommunityPost(PostEntity post, int communityId);

    boolean deleteCommunity(int communityId);

}
