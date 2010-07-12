/******************************************************************************
 * Copyright 2010 Alexander Kiel                                              *
 *                                                                            *
 * Licensed under the Apache License, Version 2.0 (the "License");            *
 * you may not use this file except in compliance with the License.           *
 * You may obtain a copy of the License at                                    *
 *                                                                            *
 *     http://www.apache.org/licenses/LICENSE-2.0                             *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 ******************************************************************************/

package net.alexanderkiel.junit.http;

import com.sun.net.httpserver.HttpExchange;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Kiel
 * @version $Id$
 */
class WritableOngoingMocking extends BaseOngoingMocking {

	private static final Charset UTF_8 = Charset.forName("UTF-8");

	private final String payload;
	private final char[] charBuffer;

	WritableOngoingMocking(@NotNull String payload) {
		this.payload = payload;
		charBuffer = new char[BUFFER_SIZE];
	}

	public void handle(HttpExchange httpExchange) throws IOException {
		verifyPayload(httpExchange);
		setResponseHeaders(httpExchange.getResponseHeaders());
		sendResponseHeaders(httpExchange);
		sendResponseBody(httpExchange.getResponseBody());
		httpExchange.close();
	}

	private void verifyPayload(HttpExchange httpExchange) throws IOException {
		String message = String.format("request %s payload", httpExchange.getRequestURI());
		String body = readBody(httpExchange.getRequestBody());
		assertEquals(message, payload, body);
	}

	private String readBody(InputStream requestBodyInputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(requestBodyInputStream, UTF_8));
		try {
			StringBuilder buffer = new StringBuilder();
			int length = 0;
			do {
				buffer.append(charBuffer, 0, length);
				length = reader.read(charBuffer);
			} while (length >= 0);
			return buffer.toString();
		} finally {
			reader.close();
		}
	}

	@Override
	public String toString() {
		return "WritableOngoingMocking[" + super.toString() + ", payload = '" + payload + "']";
	}
}