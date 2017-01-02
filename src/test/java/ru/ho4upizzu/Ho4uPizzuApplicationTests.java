package ru.ho4upizzu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ho4uPizzuApplicationTests {

	@Autowired
	List<CrudRepository> reps;

	@Test
	public void contextLoads() {
		for (CrudRepository rep : reps) {
			System.out.println(rep.findAll());
		}
	}

}
