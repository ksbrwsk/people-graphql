package de.ksbrwsk.people;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInput {
    private Long id;
    private String name;
    private String vorname;
}
