package com.accenture.socialnetwork.Enum;

public enum RelationshipResponse {

    SENT("You sent a request"),
    NO_USER("No such user"),
    WENT_WRONG("Something went wrong"),
    ALREADY_FRIENDS("You are already friends :)");
    private String response;

    RelationshipResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
