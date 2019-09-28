package ru.itis.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateForm {

    @NotNull
    private String title;

    @NotNull
    private String authorName;

    @NotNull
    private String description;
}
