package com.assessment.library.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public CustomDateDeserializer() {
		super();
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String value = p.getText().trim();

		if(value.isEmpty()) {
			return null;
		}

		try {
			return sdf.parse(p.getText());
		} catch (ParseException | IOException e) {
			throw new IOException(e.getMessage());
		}

	}

}
