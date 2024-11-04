package com.poll.pojo;

import lombok.Data;


import java.util.Date;

@Data
public class User {
    private Integer userId;

    private String username;

    private String password;

    private Boolean gender;

    private Date birth;

    private String email;

    private String avatar;

    private String profile;

    private Boolean isAdmin;

    private Boolean enabled;
}
