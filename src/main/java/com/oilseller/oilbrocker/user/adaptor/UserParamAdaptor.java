package com.oilseller.oilbrocker.user.adaptor;

import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import com.oilseller.oilbrocker.user.dto.User;
import com.oilseller.oilbrocker.user.param.UserParam;
import org.modelmapper.PropertyMap;

public class UserParamAdaptor extends AbstractParamAdaptor<UserParam, User> {

    public UserParamAdaptor() {
        super(UserParam.class, User.class);
    }

    @Override
    protected PropertyMap<UserParam, User> fromParamMappings() {
        return new PropertyMap<UserParam, User>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<User, UserParam> fromDtoMappings() {
        return new PropertyMap<User, UserParam>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    public UserParam fromDto(User user) {
        UserParam userParam = super.fromDto(user);
        userParam.setCreatedDate(DateUtil.toSimpleDate(user.getCreatedDate()));
        userParam.setLastModifiedDate(DateUtil.toSimpleDate(user.getLastModifiedDate()));
        return userParam;
    }
}
