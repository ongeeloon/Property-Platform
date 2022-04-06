package com.example.team5ad.helper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Prediction {

	@NotNull(message="must not be empty")
	@Min(200)
	@Max(2700)
	private Integer area;
	@NotNull(message="must not be empty")
	@Min(1)
	@Max(99)
	private Integer year;
	@NotNull(message="must not be empty")
	@Min(1)
	@Max(54)
	private Integer storey;
	@NotEmpty
	private String town;
	@NotEmpty
	private String street;
	
}
