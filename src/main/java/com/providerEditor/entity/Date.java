package com.providerEditor.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "date")
public class Date {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull
	@Pattern(regexp = "([01][0-9]|2[0-3])\\.[0-5][0-9]")
	@Column(name = "time")
	private String time;

	@NotNull
	@Pattern(regexp = "(monday|tuesday|wednesday|thursday|friday|saturday|sunday)")
	@Column(name = "day")
	private String day;

	public Date() {
	}

	public Date(String time, String day) {
		this.time = time;
		this.day = day;
	}

	public Date(String day) {
		this.day = day;
	}

	@Valid
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "provider_date", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "code"))
	private List<Provider> providers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	@Override
	public String toString() {
		return "Date [id=" + id + ", time=" + time + ", day=" + day + ", providers=" + providers + "]";
	}

	public void addProvider(Provider provider) {
		if (providers == null) {
			providers = new ArrayList<>();
		}
		providers.add(provider);
	}

	public void removeProvider(Provider provider) {
		if (providers != null) {
			providers.remove(provider);
		}
	}

}
