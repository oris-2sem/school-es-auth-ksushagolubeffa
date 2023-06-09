package org.example.response;

import lombok.*;

import java.util.Date;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassResponse {
    private Date date;
    private String letter;
}
