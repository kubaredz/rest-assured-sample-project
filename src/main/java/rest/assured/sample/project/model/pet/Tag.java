package rest.assured.sample.project.model.pet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private long id;
    private String name;
}
