package com.forum.ForumAPI.model;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends CustomResponse {
	
	private List<String> errors;
}
