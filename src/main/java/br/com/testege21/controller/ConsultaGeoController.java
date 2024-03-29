package br.com.testege21.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.testege21.model.Geo;
import br.com.testege21.service.GeoService;

@Controller
@RequestMapping("/consultaGeo")
public class ConsultaGeoController {

	// Injeção de dependencia do serviço para consultados dados api do Google
	@Autowired
	private GeoService geoService;

	// Função/endPoint usado para renderizar o template da tela de consulta do
	// usuário
	@GetMapping("/consultar")
	public ModelAndView novo(Geo geo) {
		ModelAndView mv = new ModelAndView("ConsultaCoordenadas/ConsultaGeo");
		return mv;
	}

	// Função/endPoint usado para exibir o retorno da consulta da api do Google
	@PostMapping(value = { "/consultar" })
	public ModelAndView consultaDadosGoogle(@Valid Geo geo, BindingResult result) {

		ModelAndView mv = new ModelAndView("ConsultaCoordenadas/ConsultaGeo");

		if (result.hasErrors()) {
			return novo(geo);
		}
		try {
			JsonNode retornoGoogle = geoService.buscarDadosGeograficos(geo.getLatitude(), geo.getLongitude());
			mv.addObject("retornoGoogle", retornoGoogle);
		}

		catch (Exception e) {
			result.rejectValue(e.getMessage(), e.getMessage());
			return novo(geo);
		}

		return mv;
	}

}
