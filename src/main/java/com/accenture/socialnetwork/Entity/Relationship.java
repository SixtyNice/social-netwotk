package com.accenture.socialnetwork.Entity;

import com.accenture.socialnetwork.Enum.RelationshipStatus;

import javax.persistence.*;

@Entity
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private RelationshipStatus status = RelationshipStatus.PENDING;

    @ManyToOne()
    @JoinColumn(name = "user_one_id")
    private UserEntity userOneId;

    @ManyToOne()
    @JoinColumn(name = "user_two_id")
    private UserEntity userTwoId;

    public Relationship(UserEntity userOneId, UserEntity userTwoId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
    }

    protected Relationship() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }

    public UserEntity getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(UserEntity userOneId) {
        this.userOneId = userOneId;
    }

    public UserEntity getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(UserEntity userTwoId) {
        this.userTwoId = userTwoId;
    }

}
