package com.barabam.ormispring;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchData {
	private String title;
	private String author;
	private int stock;
}
