package com.oilseller.oilbrocker.user.entity;

import com.oilseller.oilbrocker.platform.entity.AbstractTrackableEntity;
import com.oilseller.oilbrocker.user.dto.TokenStatus;

import javax.persistence.*;

@Entity
@Table(name = "USER_TOKEN")
public class UserTokenModel extends AbstractTrackableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID", nullable = false)
    private Long tokenId;

    @Column(name = "NO_OF_REQUEST", nullable = false)
    private Long numOfRequest;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Column(name = "USER_TOKEN", nullable = false)
    private String userToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_STATUS", nullable = false)
    private TokenStatus tokenStatus;

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public Long getNumOfRequest() {
        return numOfRequest;
    }

    public void setNumOfRequest(Long numOfRequest) {
        this.numOfRequest = numOfRequest;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public TokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(TokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }
}
