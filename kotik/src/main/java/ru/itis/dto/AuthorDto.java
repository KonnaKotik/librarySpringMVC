package ru.itis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AuthorDto {

    private String name;

    private List<BookDto> books;

}
