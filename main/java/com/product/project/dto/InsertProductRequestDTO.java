package com.product.project.dto;

import java.util.List;

public class InsertProductRequestDTO
{
	private String name;
	private String description;
	private String brand;
	private List<String> tags;
	private String category;

	public InsertProductRequestDTO(String name, String description, String brand, List<String> tags, String category)
	{
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.tags = tags;
		this.category = category;
	}

	public InsertProductRequestDTO()
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public List<String> getTags()
	{
		return tags;
	}

	public void setTags(List<String> tags)
	{
		this.tags = tags;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	@Override
	public String toString()
	{
		return "InsertProductRequestDTO{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", brand='" + brand + '\'' + ", tags=" + tags + ", category='" + category + '\'' + '}';
	}
}
