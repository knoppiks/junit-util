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

import java.io.IOException;

/**
 * @author Alexander Kiel
 * @version $Id$
 */
class ReadonlyOngoingMocking extends BaseOngoingMocking {

	public void handle(HttpExchange httpExchange) throws IOException {
		setResponseHeaders(httpExchange.getResponseHeaders());
		sendResponseHeaders(httpExchange);
		sendResponseBody(httpExchange.getResponseBody());
		httpExchange.close();
	}

	public void verify() {
	}

	@Override
	public String toString() {
		return "ReadonlyOngoingMocking[" + super.toString() + "]";
	}
}
