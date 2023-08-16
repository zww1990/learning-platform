package com.example.oauth2client.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DeviceControllerTests {

	@Test
	public void test() {
		ParameterizedTypeReference<Map<String, Object>> a = new ParameterizedTypeReference<>() {
		};
		ParameterizedTypeReference<Map<String, Object>> b = ParameterizedTypeReference.forType(Map.class);
		ResolvableType aa = ResolvableType.forType(a.getType());
		ResolvableType bb = ResolvableType.forType(b.getType());
		BodyExtractor<Mono<Object>, ReactiveHttpInputMessage> aaa = toMono(aa);
		BodyExtractor<Mono<Object>, ReactiveHttpInputMessage> bbb = toMono(bb);
		System.err.println(a);
		System.err.println(b);
		System.err.println("--------------------------------");
		System.err.println(aa);
		System.err.println(bb);
		System.err.println("--------------------------------");
		System.err.println(aaa);
		System.err.println(bbb);
	}

	private static final ResolvableType VOID_TYPE = ResolvableType.forClass(Void.class);

	private static <T> BodyExtractor<Mono<T>, ReactiveHttpInputMessage> toMono(ResolvableType elementType) {
		return (inputMessage, context) -> readWithMessageReaders(inputMessage, context, elementType,
				(HttpMessageReader<T> reader) -> readToMono(inputMessage, context, elementType, reader),
				ex -> Mono.from(unsupportedErrorHandler(inputMessage, ex)), skipBodyAsMono(inputMessage));
	}

	private static <T> Supplier<Mono<T>> skipBodyAsMono(ReactiveHttpInputMessage message) {
		return message instanceof ClientHttpResponse ? () -> consumeAndCancel(message).then(Mono.empty()) : Mono::empty;
	}

	private static Flux<DataBuffer> consumeAndCancel(ReactiveHttpInputMessage message) {
		return message.getBody().takeWhile(buffer -> {
			DataBufferUtils.release(buffer);
			return false;
		});
	}

	private static <T, S extends Publisher<T>> S readWithMessageReaders(ReactiveHttpInputMessage message,
			BodyExtractor.Context context, ResolvableType elementType, Function<HttpMessageReader<T>, S> readerFunction,
			Function<UnsupportedMediaTypeException, S> errorFunction, Supplier<S> emptySupplier) {

		if (VOID_TYPE.equals(elementType)) {
			return emptySupplier.get();
		}
		MediaType contentType = Optional.ofNullable(message.getHeaders().getContentType())
				.orElse(MediaType.APPLICATION_OCTET_STREAM);

		for (HttpMessageReader<?> messageReader : context.messageReaders()) {
			if (messageReader.canRead(elementType, contentType)) {
				return readerFunction.apply(cast(messageReader));
			}
		}
		List<MediaType> mediaTypes = context.messageReaders().stream()
				.flatMap(reader -> reader.getReadableMediaTypes(elementType).stream()).toList();
		return errorFunction.apply(new UnsupportedMediaTypeException(contentType, mediaTypes, elementType));
	}

	private static <T> Mono<T> readToMono(ReactiveHttpInputMessage message, BodyExtractor.Context context,
			ResolvableType type, HttpMessageReader<T> reader) {

		return context.serverResponse()
				.map(response -> reader.readMono(type, type, (ServerHttpRequest) message, response, context.hints()))
				.orElseGet(() -> reader.readMono(type, message, context.hints()));
	}

	private static <T> Flux<T> unsupportedErrorHandler(ReactiveHttpInputMessage message,
			UnsupportedMediaTypeException ex) {

		Flux<T> result;
		if (message.getHeaders().getContentType() == null) {
			result = message.getBody().map(buffer -> {
				DataBufferUtils.release(buffer);
				throw ex;
			});
		} else {
			result = message instanceof ClientHttpResponse ? consumeAndCancel(message).thenMany(Flux.error(ex))
					: Flux.error(ex);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private static <T> HttpMessageReader<T> cast(HttpMessageReader<?> reader) {
		return (HttpMessageReader<T>) reader;
	}

}
