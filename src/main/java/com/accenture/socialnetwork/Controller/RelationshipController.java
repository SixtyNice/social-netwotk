package com.accenture.socialnetwork.Controller;

import com.accenture.socialnetwork.Entity.Relationship;
import com.accenture.socialnetwork.Enum.RelationshipResponse;
import com.accenture.socialnetwork.Services.RelationshipService.RelationshipServiceImpl;
import com.accenture.socialnetwork.Services.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/relationship")
public class RelationshipController {

    @Autowired
    private RelationshipServiceImpl relationshipService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(path = "/addToFriend", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addToFriend(@PathParam(value = "fromUserId") int fromUserId,
                                              @PathParam(value = "toUserId") int toUserId) {
        String response = relationshipService.addToFriend(fromUserId, toUserId);
        if (!response.equals(RelationshipResponse.WENT_WRONG.getResponse())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/deleteFromFriends", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> deleteFromFriends(@PathParam(value = "fromUserId") int fromUserId,
                                                     @PathParam(value = "toUserId") int toUserId) {
        if (relationshipService.deleteFriend(fromUserId, toUserId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/acceptRequest", produces = "application/json")
    public ResponseEntity<Boolean> acceptRequest(@PathParam(value = "fromUserId") int fromUserId,
                                                 @PathParam(value = "toUserId") int toUserId) {

        if (relationshipService.acceptUser(fromUserId, toUserId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/declineRequest", produces = "application/json")
    public ResponseEntity<Boolean> declineRequest(@PathParam(value = "fromUserId") int fromUserId,
                                                  @PathParam(value = "toUserId") int toUserId) {
        if (relationshipService.declineUser(fromUserId, toUserId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/blockUser", produces = "application/json")
    public ResponseEntity<Boolean> blockUser(@PathParam(value = "fromUserId") int fromUserId,
                                             @PathParam(value = "toUserId") int toUserId) {
        if (relationshipService.blockUser(fromUserId, toUserId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }


}
