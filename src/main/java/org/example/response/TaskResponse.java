package org.example.response;

import lombok.*;
import org.example.model.Task;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private String comment;
    private String description;
    private Integer mark;
    private Task.Type type;
}
