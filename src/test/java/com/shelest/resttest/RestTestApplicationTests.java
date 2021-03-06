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

	}

}
