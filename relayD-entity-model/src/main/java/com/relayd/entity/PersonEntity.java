package com.relayd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class PersonEntity {

	@Id
	@Column
	private String id;

	@Column
	private String surename;

	@Column
	private String forename;

	@Column
	private Integer birthyear;

	@Column
	private Integer shirtsize;

	@Column
	private String nationality;

	@Column
	private String email;

	@Column
	private String info;

	@Column
	private String relayname;

	@Column
	private Integer pos;

	private PersonEntity(Builder aBuilder) {
		id = aBuilder.id;
		forename = aBuilder.forename;
		surename = aBuilder.surename;
		email = aBuilder.email;
		birthyear = aBuilder.birthyear;
		relayname = aBuilder.relayname;
		pos = aBuilder.pos;
		info = aBuilder.info;
		nationality = aBuilder.nationality;
		shirtsize = aBuilder.shirtsize;
	}

	/**
	 * Provided empty constructor to conform to the JPA spec. For that reason the entity property fields
	 * are not declared 'final'.
	 *
	 */
	PersonEntity() {
		super();
	}

	public String getSurename() {
		return surename;
	}

	public String getForename() {
		return forename;
	}

	public Integer getShirtsize() {
		return shirtsize;
	}

	public String getNationality() {
		return nationality;
	}

	public String getEmail() {
		return email;
	}

	public Integer getBirthyear() {
		return birthyear;
	}

	public String getId() {
		return id;
	}

	public String getInfo() {
		return info;
	}

	public String getRelayname() {
		return relayname;
	}

	public Integer getPos() {
		return pos;
	}

	public static class Builder {
		private final String id = UUID.randomUUID().toString();
		private String forename;
		private String surename;
		private String email;
		private Integer birthyear;
		private Integer shirtsize;
		private String nationality;
		private String info;
		private String relayname;
		private Integer pos;

		public Builder() {
			super();
		}

		public Builder withForename(String aForename) {
			forename = aForename;
			return this;
		}

		public Builder withSurename(String aSurename) {
			surename = aSurename;
			return this;
		}

		public Builder withEmail(String anEmail) {
			email = anEmail;
			return this;
		}

		public Builder withBirthyear(Integer aBirthyear) {
			birthyear = aBirthyear;
			return this;
		}

		public Builder withShirtsize(Integer aShirtsize) {
			shirtsize = aShirtsize;
			return this;
		}

		public Builder withNationality(String aNationality) {
			nationality = aNationality;
			return this;
		}

		public Builder withRelayname(String aRelayname) {
			relayname = aRelayname;
			return this;
		}

		public Builder withPos(Integer aPos) {
			pos = aPos;
			return this;
		}

		public Builder withInfo(String anInfo) {
			info = anInfo;
			return this;
		}

		public PersonEntity build() {
			return new PersonEntity(this);
		}
	}
}