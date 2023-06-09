package org.example.response;

import lombok.*;

import java.util.Date;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimetableResponse {
    private Date date;
    private Integer cabinet;
    private String subject; //get from lesson
}
