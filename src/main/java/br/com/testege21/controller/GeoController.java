package br.com.testege21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.testege21.service.GeoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Geo")
@CrossOrigin
@RestController
@RequestMapping(value = "/geo")
public class GeoController {

	@Autowired
	private GeoService geoService;

	@ApiOperation("Buscar dados geográficos por coordenadas")
	@GetMapping("/coordenadas")
	public ResponseEntity<JsonNode> buscar(@RequestParam String latitude, @RequestParam String longitude) {
		JsonNode jsonNode = geoService.buscarDadosGeograficos(latitude, longitude);

		if (jsonNode != null && !jsonNode.isEmpty()) {
			return ResponseEntity.ok(jsonNode);
		}

		return ResponseEntity.notFound().build();
	}
}
