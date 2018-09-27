package com.rich.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {

	@NonNull
	private String name;

	@NonNull
	private int age;
}
