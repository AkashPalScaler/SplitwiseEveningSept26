package com.scaler.splitwiseeveningsept2026.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="splitwise_group")
public class Group extends BaseModel {
    private String name;
    private String description;
    // Group M:1 Admin
//    @ManyToOne
//    private User admin;
//    // Group M:M members
//    @ManyToMany
//    private List<User> members;
    // Group 1:M Expense
    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
    @Enumerated(EnumType.STRING)
    private GroupType type;
}
// user_id | group_id | member_type
// 1 | 1 | admin
// 2 | 1 | member
// 3 | 1 | admin
// 4 | 1 | member
// 4 | 1 | member