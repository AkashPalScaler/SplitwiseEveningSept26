package com.scaler.splitwiseeveningsept2026.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="splitwise_user")
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
//    @ManyToMany(mappedBy = "members")
//    private List<Group> groups;
}

