package com.xerocry.mondrian_maker.domain.services.interfaces;

import com.xerocry.mondrian_maker.domain.model.entities.interfaces.IUser;

public interface IUserService {
  Iterable<IUser> getUsers();
  IUser getUser(String userName);
}
