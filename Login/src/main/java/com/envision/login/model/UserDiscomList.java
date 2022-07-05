package com.envision.login.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDiscomList {
	String discomName;
	String state;
	boolean userDefault;
////	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.")
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
//	@DateTimeFormat(iso = ISO.)

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	LocalDateTime start;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	LocalDateTime end;
}
