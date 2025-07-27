package rest.assured.sample.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemMessageResponse {
    private int code;
    private String type;
    private String message;
}