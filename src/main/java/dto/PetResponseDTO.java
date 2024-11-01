package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDTO {
        public int id;
        public String name;
        public ArrayList<String> photoUrls;
        public ArrayList<Tag> tags;

    public class Tag{

    }

}
