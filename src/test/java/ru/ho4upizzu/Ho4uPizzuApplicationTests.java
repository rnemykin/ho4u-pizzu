package ru.ho4upizzu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ho4upizzu.repository.CafeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ho4uPizzuApplicationTests {

	@Autowired
	CafeRepository cafeRepository;

	@Test
	public void contextLoads() {
		Sort srt = new Sort(Sort.Direction.ASC, "name");
		cafeRepository.findByHasDeliveryTrue(srt);
	}

}
