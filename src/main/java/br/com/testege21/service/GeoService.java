package br.com.testege21.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.testege21.model.Geo;
import br.com.testege21.repository.GeoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GeoService {

	@Autowired
	private GeoRepository geoRepository;

	// Salva os dados consultados no banco de dados h2
	public void salvar(Geo geo) {
		geo.setDataHoraCriacao(LocalDateTime.now());
		geoRepository.saveAndFlush(geo);
	}

	@SuppressWarnings({ "deprecation", "resource" })
	public JsonNode buscarDadosGeograficos(String latitude, String longitude) {

		try {
			// URL para acesso ao serviço do Google
			String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude
					+ "&key=AIzaSyAtxmZ7Ce74W5V0tysKOqDBQ0h6ETSOFBw";

			HttpClient httpclient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			InputStream in = httpclient.execute(request).getEntity().getContent();

			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();

			br = new BufferedReader(new InputStreamReader(in));

			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}

			// Criando um ObjectMapper (do Jackson) para manipular JSON
			ObjectMapper objectMapper = new ObjectMapper();

			// Convertendo a string em um objeto JSON
			JsonNode jsonNode = objectMapper.readTree(sb.toString());

			Geo geo = new Geo();
			geo.setLatitude(latitude);
			geo.setLongitude(longitude);
			geo.setJsonRetornado(jsonNode.toString());

			// Salva os dados no banco H2
			this.salvar(geo);

			return jsonNode;

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

}
