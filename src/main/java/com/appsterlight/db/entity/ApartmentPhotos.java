package com.appsterlight.db.entity;

import java.io.Serial;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ApartmentPhotos {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String path;

    private Apartment apartment;

}
