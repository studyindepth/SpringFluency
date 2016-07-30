package jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CardRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional(rollbackFor = Exception.class)
	public Card create(Card card) {
		em.persist(card);
		return card;
	}

	public Card findOne(Long id) {
		return em.find(Card.class, id);
	}
	

}
