package com.appsterlight.db.entity;

import lombok.*;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ApartmentClass {
    @Serial
    private static final long serialVersionUID = 1L;

    private static Long id;
    private static String name;
    private static String description;

}
