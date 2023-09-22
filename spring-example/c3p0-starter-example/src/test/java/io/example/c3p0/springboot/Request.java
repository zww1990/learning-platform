package io.example.c3p0.springboot;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request {
	private Long id1;
	private String name1;
	private Character gender1;
	private LocalDate birthday1;
}
