package com.example.team5ad.helper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PredictionCondo {
	@NotNull(message="must not be empty")
	@Min(200)
	@Max(22000)
	private Integer area;
	@NotNull(message="must not be empty")
	@Min(1)
	@Max(999)
	private Integer tenure;
	@NotNull(message="must not be empty")
	@Min(1)
	@Max(80)
	private Integer floorRange;
	@NotEmpty
	private String district;
	@NotEmpty
	private String project;
	
}
