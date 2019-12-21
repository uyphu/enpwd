/*
 * Copyright 2017 ltu.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://ltu.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.ltu.secret.action.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.tz.FixedDateTimeZone;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ltu.secret.RequestRouter;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.InternalErrorException;

/**
 * Helper utilities for testing Lambda functions.
 */
public class TestUtils {

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.setPropertyNamingStrategy(new UpperCaseRecordsPropertyNamingStrategy());
        mapper.registerModule(new TestJacksonMapperModule());
    }

    private static final DateTimeFormatter dateTimeFormatter = 
            ISODateTimeFormat.dateTime()
                        .withZone(new FixedDateTimeZone("GMT", "GMT", 0, 0));
    
    /**
     * Call API.
     *
     * @param context the context
     * @param input the input
     * @param output the output
     */
    public static void callAPI(Context context, String input, String output) {
    	//FIXME comment dev bucket, config
    	
    	DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		System.out.println(iso8601Format.format(Calendar.getInstance().getTime()));
		long start = Calendar.getInstance().getTime().getTime();
		try {
			InputStream request = TestUtils.class.getResourceAsStream(input);
			OutputStream response = new FileOutputStream(output);
			RequestRouter.lambdaHandler(request, response, context);
			
			response.flush();
			response.close();
			request.close();
		} catch (BadRequestException e) {
			e.printStackTrace();
		} catch (InternalErrorException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(iso8601Format.format(Calendar.getInstance().getTime()));
		long end = Calendar.getInstance().getTime().getTime();
		double result = ((end - start)/1000.0)/60.0;
		System.out.println("Duration:" + String.valueOf(result));

	}
    
    /**
     * Helper method that parses a JSON object from a resource on the classpath
     * as an instance of the provided type.
     *
     * @param resource the path to the resource (relative to this class)
     * @param clazz the type to parse the JSON into
     */
    public static <T> T parse(String resource, Class<T> clazz)
            throws IOException {

        InputStream stream = TestUtils.class.getResourceAsStream(resource);
        try {
            if (clazz == S3Event.class) {
                String json = IOUtils.toString(stream);
                S3EventNotification event = S3EventNotification.parseJson(json);

                @SuppressWarnings("unchecked")
                T result = (T) new S3Event(event.getRecords());
                return result;

            } else {
                return mapper.readValue(stream, clazz);
            }
        } finally {
            stream.close();
        } 
    }

    private static class TestJacksonMapperModule extends SimpleModule {

        private static final long serialVersionUID = 1L;

        public TestJacksonMapperModule() {
            super("TestJacksonMapperModule");

            super.addSerializer(DateTime.class, new DateTimeSerializer());
            super.addDeserializer(DateTime.class, new DateTimeDeserializer());
        }
    }

    private static class DateTimeSerializer extends JsonSerializer<DateTime> {

        @Override
        public void serialize(
                DateTime value,
                JsonGenerator gen,
                SerializerProvider provider) throws IOException {

            gen.writeString(dateTimeFormatter.print(value));
        }
    }

    private static class DateTimeDeserializer
            extends JsonDeserializer<DateTime> {

        @Override
        public DateTime deserialize(
                JsonParser parser,
                DeserializationContext context) throws IOException {

            return dateTimeFormatter.parseDateTime(parser.getText());
        }
    }

    private static class UpperCaseRecordsPropertyNamingStrategy
            extends PropertyNamingStrategy.PropertyNamingStrategyBase {

        private static final long serialVersionUID = 1L;

        @Override
        public String translate(String propertyName) {
            if (propertyName.equals("records")) {
                return "Records";
            }
            return propertyName;
        }
    }
    
}
