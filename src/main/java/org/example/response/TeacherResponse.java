package org.example.response;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponse {
    private String name;
    private String surname;
    private String subject;
}
