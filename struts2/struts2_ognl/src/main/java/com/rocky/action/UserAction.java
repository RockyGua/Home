package com.rocky.action;

import com.opensymphony.xwork2.Action;
import com.rocky.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserAction {

    private List<User> userList;

    public String list()
    {
        userList = new ArrayList<User>();
        User user = new User();
        user.setName("name1");
        user.setAge("age1");
        user.setGender("gender1");
        userList.add(user);

        return Action.SUCCESS;
    }

    public List getUserList() {
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }
}
