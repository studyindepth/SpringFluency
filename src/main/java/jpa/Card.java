package jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "card")
public class Card {
	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 10)
	private String name;

	public Card() {
	}

	public Card(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
