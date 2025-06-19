package com.htetoakkar.socialmedia.user.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String profilePictureUrl;
    private String bio;
    private boolean isActive;
    private String createdAt;
    private String updatedAt;
}
