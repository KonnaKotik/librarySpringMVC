package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateForm {

    @NotNull
    private String name;

    @NotNull
    private List<BookUpdateForm> books;
}
