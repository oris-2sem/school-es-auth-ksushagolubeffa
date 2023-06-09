package org.example.response;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentResponse {
    private String name;
    private String surname;
}
