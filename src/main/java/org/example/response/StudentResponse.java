package org.example.response;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private String name;
    private String surname;
}
