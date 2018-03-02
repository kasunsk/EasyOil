package com.oilseller.oilbrocker.user.adaptor;

import com.oilseller.oilbrocker.platform.adaptor.AbstractModelAdaptor;
import com.oilseller.oilbrocker.user.dto.User;
import com.oilseller.oilbrocker.user.entity.UserModel;
import org.modelmapper.PropertyMap;

public class UserModelAdaptor extends AbstractModelAdaptor<UserModel, User> {
    public UserModelAdaptor() {
        super(UserModel.class, User.class);
    }

    @Override
    protected PropertyMap<UserModel, User> fromModelMappings() {
        return new PropertyMap<UserModel, User>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<User, UserModel> fromDtoMappings() {
        return new PropertyMap<User, UserModel>() {
            @Override
            protected void configure() {
            }
        };
    }
}
