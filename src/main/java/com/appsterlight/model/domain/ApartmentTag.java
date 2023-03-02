package com.appsterlight.model.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ApartmentTag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String tagId;
    private Long apartmentId;

}
