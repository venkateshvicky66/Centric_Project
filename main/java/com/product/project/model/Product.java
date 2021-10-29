package com.product.project.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.lang.String;
@Entity
public class Product
{
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type="uuid-char")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String brand;
	@Column
	@ElementCollection(fetch= FetchType.EAGER)
	@CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "id"))
	private List<String> tags;
	@Column
	private String category;
	@Column
	private String created_at;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
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

	public String getCreated_at()
	{
		return created_at;
	}

	public void setCreated_at(String created_at)
	{
		this.created_at = created_at;
	}

	@Override
	public String toString()
	{
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", brand='" + brand + '\'' + ", tags=" + tags + ", category='" + category + '\'' + ", created_at='" + created_at + '\'' + '}';
	}
}
