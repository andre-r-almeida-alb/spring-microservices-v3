package com.in28minutes.microservices.limitsservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Limits {
	private int minimum;
	private int maximum;
}
