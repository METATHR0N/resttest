package com.shelest.resttest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelest.resttest.db.Gender;
import com.shelest.resttest.db.User;
import com.shelest.resttest.db.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
class RestTestApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserRepository repository;

	@Test
	public void mainControllerTest() throws Exception {
//		mvc.perform(post("/user").params("First", "05/08/1999", "MALE"));
		User test = new User("Alex", new Date(), Gender.FEMALE);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		String json = mapper.writeValueAsString(test);
		System.out.println(json);
		mvc.perform(post("/user")
//				.accept(MediaTypes.HAL_JSON_VALUE)
//				.contentType(MediaTypes.HAL_JSON_VALUE)
				.content(json)).andDo(print());

//		Date date1 = new GregorianCalendar(1999, Calendar.AUGUST, 5).getTime();
//		Date date2 = new GregorianCalendar(1999, Calendar.JANUARY, 21).getTime();
//
//		given(repository.findAll()).willReturn(Arrays.asList(
//						new User("Serj", DateUtils.truncate(date1, Calendar.DATE), Gender.MALE),
//						new User("Kate", new Date("21/01/1999"), Gender.FEMALE)));

//		mvc.perform(get("/user").accept(MediaTypes.HAL_JSON_VALUE))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$._embedded.userList[0].name", is("Serj")))
//				.andExpect(jsonPath("$._embedded.userList[0].birthday", is(DateUtils.truncate(date1, Calendar.DATE))))
//				.andReturn();
	}

}
