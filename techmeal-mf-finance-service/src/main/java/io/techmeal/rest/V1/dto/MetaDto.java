package io.techmeal.rest.V1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaDto {

	@JsonProperty("query")
	private String query;
	
	@JsonProperty("per_page")
	private Long perPage;
	
	@JsonProperty("current_page")
	private Long currentPage;
	
	@JsonProperty("prev_page")
	private Long prevPage;
	
	@JsonProperty("total_pages")
	private Long totalPages;
	
	@JsonProperty("total_count")
	private Long totalCount;
	
	@JsonProperty("next_page")
	private Long nextPage;
	
	@JsonProperty("current_first_item")
	private Long currentFirstItem;
	
	@JsonProperty("current_last_item")
	private Long currentLastItem;

	public MetaDto() {
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

	public Long getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(Long prevPage) {
		this.prevPage = prevPage;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getNextPage() {
		return nextPage;
	}

	public void setNextPage(Long nextPage) {
		this.nextPage = nextPage;
	}

	public Long getCurrentFirstItem() {
		return currentFirstItem;
	}

	public void setCurrentFirstItem(Long currentFirstItem) {
		this.currentFirstItem = currentFirstItem;
	}

	public Long getCurrentLastItem() {
		return currentLastItem;
	}

	public void setCurrentLastItem(Long currentLastItem) {
		this.currentLastItem = currentLastItem;
	}

	public static class Builder {
		private String query;
		private Long perPage;
		private Long currentPage;
		private Long prevPage;
		private Long totalPages;
		private Long totalCount;
		private Long nextPage;
		private Long currentFirstItem;
		private Long currentLastItem;

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

		public Builder prevPage(Long prevPage) {
			this.prevPage = prevPage;
			return this;
		}

		public Builder totalPages(Long totalPages) {
			this.totalPages = totalPages;
			return this;
		}

		public Builder totalCount(Long totalCount) {
			this.totalCount = totalCount;
			return this;
		}

		public Builder nextPage(Long nextPage) {
			this.nextPage = nextPage;
			return this;
		}

		public Builder currentFirstItem(Long currentFirstItem) {
			this.currentFirstItem = currentFirstItem;
			return this;
		}

		public Builder currentLastItem(Long currentLastItem) {
			this.currentLastItem = currentLastItem;
			return this;
		}

		public MetaDto build() {
			return new MetaDto(this);
		}
	}

	private MetaDto(Builder builder) {
		this.query = builder.query;
		this.perPage = builder.perPage;
		this.currentPage = builder.currentPage;
		this.prevPage = builder.prevPage;
		this.totalPages = builder.totalPages;
		this.totalCount = builder.totalCount;
		this.nextPage = builder.nextPage;
		this.currentFirstItem = builder.currentFirstItem;
		this.currentLastItem = builder.currentLastItem;
	}

	@Override
	public String toString() {
		return "MetaDto [query=" + query + ", perPage=" + perPage + ", currentPage=" + currentPage + ", prevPage="
				+ prevPage + ", totalPages=" + totalPages + ", totalCount=" + totalCount + ", nextPage=" + nextPage
				+ ", currentFirstItem=" + currentFirstItem + ", currentLastItem=" + currentLastItem + "]";
	}
	
}
