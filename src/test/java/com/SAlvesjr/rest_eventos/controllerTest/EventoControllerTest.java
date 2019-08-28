package com.SAlvesjr.rest_eventos.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.SAlvesjr.rest_eventos.controller.EventoController;
import com.SAlvesjr.rest_eventos.model.Evento;
import com.SAlvesjr.rest_eventos.repository.EventoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(EventoController.class)
public class EventoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private EventoRepository eventRepository;

	@Test
	public void findByNomeEvento() throws Exception {
		Evento event = new Evento(1L, "Show Music", 100);

	}
}
