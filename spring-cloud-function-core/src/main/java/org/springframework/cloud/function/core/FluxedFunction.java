/*
 * Copyright 2019-2019 the original author or authors.
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

package org.springframework.cloud.function.core;

import java.util.function.Function;

import reactor.core.publisher.Flux;

/**
 * {@link Function} implementation that wraps a target Function so that the target's
 * simple input and output types will be wrapped as {@link Flux} instances.
 *
 * @param <I> input type of target function
 * @param <O> output type of target function
 * @author Oleg Zhurakousky
 * @since 2.0.1
 */
public class FluxedFunction<I, O>
		extends WrappedFunction<I, O, Flux<I>, Flux<O>, Function<Flux<I>, Flux<O>>> {

	public FluxedFunction(Function<Flux<I>, Flux<O>> target) {
		super(target);
	}

	@Override
	public Flux<O> apply(Flux<I> input) {
		return input.transform(this.getTarget());
	}

}
