package org.rean.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.rean.crud.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_role")
    private String role;

    @OneToMany(mappedBy = "users")
    private List<BookMark> bookmarks;

    public Users(UUID id, String name, String role){
        this.id=id;
        this.username=name;
        this.role=role;
    }

    public UserDto toDto(){
        return new UserDto(this.id, this.username, this.role );
    }


}
