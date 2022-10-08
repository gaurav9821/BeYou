package com.beyou.review.vote;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import com.beyou.common.entity.Customer;
import com.beyou.common.entity.Review;
import com.beyou.common.entity.ReviewVote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReviewVoteRepositoryTests {
	
	@Autowired private ReviewVoteRepository repo;
	
	@Test
	public void testSaveVote() {
		Integer customerId = 3;
		Integer reviewId = 5;
		
		ReviewVote vote = new ReviewVote();
		vote.setCustomer(new Customer(customerId));
		vote.setReview(new Review(reviewId));
		vote.voteUp();
		
		ReviewVote savedVote = repo.save(vote);
		assertThat(savedVote.getId()).isGreaterThan(0);		
	}
	
	@Test
	public void testFindByReviewAndCustomer() {
		Integer customerId = 1;
		Integer reviewId = 4;	
		
		ReviewVote vote = repo.findByReviewAndCustomer(reviewId, customerId);
		assertThat(vote).isNotNull();
		
		System.out.println(vote);
	}
	
	@Test
	public void testFindByProductAndCustomer() {
		Integer customerId = 1;
		Integer productId = 1;
		
		List<ReviewVote> listVotes = repo.findByProductAndCustomer(productId, customerId);
		assertThat(listVotes.size()).isGreaterThan(0);
		
		listVotes.forEach(System.out::println);
	}
}
