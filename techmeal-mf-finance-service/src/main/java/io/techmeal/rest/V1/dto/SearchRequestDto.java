package io.techmeal.rest.V1.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchRequestDto implements Serializable {

	private static final long serialVersionUID = 7487153996460851208L;

	@JsonProperty("query")
	private String query;

	@JsonProperty("per_page")
	private Long perPage = 15L;
	
	@JsonProperty("current_page")
	private Long currentPage = 1L;

	public SearchRequestDto() {
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Long getPerPage() {
		return perPage;
	}

	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public static class Builder {
		private String query;
		private Long perPage;
		private Long currentPage;

		public Builder query(String query) {
			this.query = query;
			return this;
		}

		public Builder perPage(Long perPage) {
			this.perPage = perPage;
			return this;
		}

		public Builder currentPage(Long currentPage) {
			this.currentPage = currentPage;
			return this;
		}

		public SearchRequestDto build() {
			return new SearchRequestDto(this);
		}
	}

	private SearchRequestDto(Builder builder) {
		this.query = builder.query;
		this.perPage = builder.perPage;
		this.currentPage = builder.currentPage;
	}

	@Override
	public String toString() {
		return "SearchRequestDto [query=" + query + ", perPage=" + perPage + ", currentPage=" + currentPage + "]";
	}
	
	
}
