package org.rean.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.rean.crud.model.dto.UserDto;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_role")
    private String role;

    public Users(int id, String name, String role){
        this.id=id;
        this.username=name;
        this.role=role;
    }

    public UserDto toDto(){
        return new UserDto(this.id, this.username, this.role );
    }


}
