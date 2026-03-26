package com.SocialPlat.SocialPlat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class FollowId implements Serializable {
    private static final long serialVersionUID = 1045367147702677613L;
    @NotNull
    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @NotNull
    @Column(name = "following_id", nullable = false)
    private Long followingId;


}