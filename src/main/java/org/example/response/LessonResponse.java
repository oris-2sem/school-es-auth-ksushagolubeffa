package org.example.response;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponse {
    private String subject;
    private String theme;
}
