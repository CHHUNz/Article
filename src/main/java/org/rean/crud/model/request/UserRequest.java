package org.rean.crud.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.Users;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String role;

    public Users toEntity(){
        return new Users(null, this.name, this.role);
    }

}
