package com.appsterlight.model.domain;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Tag implements Serializable {
    private Long id;
    private String name;
    private String description;

}
