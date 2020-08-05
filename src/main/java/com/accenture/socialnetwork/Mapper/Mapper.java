package com.accenture.socialnetwork.Mapper;

import com.accenture.socialnetwork.DTO.UserDTO;
import com.accenture.socialnetwork.Entity.Relationship;
import com.accenture.socialnetwork.Entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<UserDTO> usersMap(List<UserEntity> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(userEntity -> usersDTO.add(new UserDTO(userEntity)));
        return usersDTO;
    }


    public static List<UserDTO> friendsMap(List<Relationship> relationships,int userId) {
       List<UserEntity> friends = new ArrayList<>();
        relationships.forEach(relationship -> {
            if (relationship.getUserOneId().getId()!=userId){
                friends.add(relationship.getUserOneId());
            } else if(relationship.getUserTwoId().getId()!=userId) {
                friends.add(relationship.getUserTwoId());
            }
        });
        return Mapper.usersMap(friends);
    }


}
