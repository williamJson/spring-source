/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.reactive.function.server.support;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpMethod;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Arjen Poutsma
 */
public class ServerRequestWrapperTests {

	private final ServerRequest mockRequest = mock(ServerRequest.class);

	private final ServerRequestWrapper wrapper = new ServerRequestWrapper(mockRequest);


	@Test
	public void request() {
		assertThat(wrapper.request()).isSameAs(mockRequest);
	}

	@Test
	public void method() {
		HttpMethod method = HttpMethod.POST;
		given(mockRequest.method()).willReturn(method);

		assertThat(wrapper.method()).isSameAs(method);
	}

	@Test
	public void uri() {
		URI uri = URI.create("https://example.com");
		given(mockRequest.uri()).willReturn(uri);

		assertThat(wrapper.uri()).isSameAs(uri);
	}

	@Test
	public void path() {
		String path = "/foo/bar";
		given(mockRequest.path()).willReturn(path);

		assertThat(wrapper.path()).isSameAs(path);
	}

	@Test
	public void headers() {
		ServerRequest.Headers headers = mock(ServerRequest.Headers.class);
		given(mockRequest.headers()).willReturn(headers);

		assertThat(wrapper.headers()).isSameAs(headers);
	}

	@Test
	public void attribute() {
		String name = "foo";
		String value = "bar";
		given(mockRequest.attribute(name)).willReturn(Optional.of(value));

		assertThat(wrapper.attribute(name)).isEqualTo(Optional.of(value));
	}

	@Test
	public void queryParam() {
		String name = "foo";
		String value = "bar";
		given(mockRequest.queryParam(name)).willReturn(Optional.of(value));

		assertThat(wrapper.queryParam(name)).isEqualTo(Optional.of(value));
	}

	@Test
	public void queryParams() {
		MultiValueMap<String, String> value = new LinkedMultiValueMap<>();
		value.add("foo", "bar");
		given(mockRequest.queryParams()).willReturn(value);

		assertThat(wrapper.queryParams()).isSameAs(value);
	}

	@Test
	public void pathVariable() {
		String name = "foo";
		String value = "bar";
		given(mockRequest.pathVariable(name)).willReturn(value);

		assertThat(wrapper.pathVariable(name)).isEqualTo(value);
	}

	@Test
	public void pathVariables() {
		Map<String, String> pathVariables = Collections.singletonMap("foo", "bar");
		given(mockRequest.pathVariables()).willReturn(pathVariables);

		assertThat(wrapper.pathVariables()).isSameAs(pathVariables);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void cookies() {
		MultiValueMap<String, HttpCookie> cookies = mock(MultiValueMap.class);
		given(mockRequest.cookies()).willReturn(cookies);

		assertThat(wrapper.cookies()).isSameAs(cookies);
	}

	@Test
	public void bodyExtractor() {
		Mono<String> result = Mono.just("foo");
		BodyExtractor<Mono<String>, ReactiveHttpInputMessage> extractor = BodyExtractors.toMono(String.class);
		given(mockRequest.body(extractor)).willReturn(result);

		assertThat(wrapper.body(extractor)).isSameAs(result);
	}

	@Test
	public void bodyToMonoClass() {
		Mono<String> result = Mono.just("foo");
		given(mockRequest.bodyToMono(String.class)).willReturn(result);

		assertThat(wrapper.bodyToMono(String.class)).isSameAs(result);
	}

	@Test
	public void bodyToMonoParameterizedTypeReference() {
		Mono<String> result = Mono.just("foo");
		ParameterizedTypeReference<String> reference = new ParameterizedTypeReference<String>() {};
		given(mockRequest.bodyToMono(reference)).willReturn(result);

		assertThat(wrapper.bodyToMono(reference)).isSameAs(result);
	}

	@Test
	public void bodyToFluxClass() {
		Flux<String> result = Flux.just("foo");
		given(mockRequest.bodyToFlux(String.class)).willReturn(result);

		assertThat(wrapper.bodyToFlux(String.class)).isSameAs(result);
	}

	@Test
	public void bodyToFluxParameterizedTypeReference() {
		Flux<String> result = Flux.just("foo");
		ParameterizedTypeReference<String> reference = new ParameterizedTypeReference<String>() {};
		given(mockRequest.bodyToFlux(reference)).willReturn(result);

		assertThat(wrapper.bodyToFlux(reference)).isSameAs(result);
	}

}
