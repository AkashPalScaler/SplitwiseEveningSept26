package com.scaler.splitwiseeveningsept2026.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserGroup extends BaseModel {
    // UserGroup M:1 User
    @ManyToOne
    private User user;
    @ManyToOne
    private Group group;
    @Enumerated(EnumType.STRING)
    private UserType type;
}
