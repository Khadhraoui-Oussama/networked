package com.fsb.networked.dto;

import org.json.JSONObject;

public class ProjectDTO
{
	private String title;
	private String technology;
	private String link;
	private String description;

	public ProjectDTO(String title, String technology, String link, String description)
	{
		super();
		this.title = title;
		this.technology = technology;
		this.description = description;
		this.link = link;
	}

	public ProjectDTO() {

	}

	public String getTitle() {
		return this.title;
	}

	public String getTechnology() {
		return this.technology;
	}

	public String getLink() {
		return this.link;
	}

	public String getDescription() {
		return this.description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public JSONObject toJSON() {
		JSONObject jobJSON = new JSONObject();
		jobJSON.put("title", title);
		jobJSON.put("technology", technology);
		jobJSON.put("link", link);
		jobJSON.put("description", description);
		return jobJSON;
	}
}
