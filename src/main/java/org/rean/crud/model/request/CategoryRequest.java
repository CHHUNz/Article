package org.rean.crud.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.Categories;
import org.rean.crud.model.dto.CategoriesDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String name;
    public Categories toEntity(){
        return new Categories(null, this.name);
    }
    public Categories toEntity(UUID id){
        return new Categories(id, this.name);
    }

}
